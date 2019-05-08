/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.geode.connectors.jdbc.internal.cli;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.File;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.geode.cache.configuration.CacheConfig;
import org.apache.geode.cache.configuration.JndiBindingsType.JndiBinding;
import org.apache.geode.distributed.internal.InternalConfigurationPersistenceService;
import org.apache.geode.internal.jndi.JNDIInvoker;
import org.apache.geode.internal.logging.LogService;
import org.apache.geode.test.dunit.IgnoredException;
import org.apache.geode.test.dunit.SerializableRunnableIF;
import org.apache.geode.test.dunit.rules.ClusterStartupRule;
import org.apache.geode.test.dunit.rules.MemberVM;
import org.apache.geode.test.junit.rules.GfshCommandRule;
import org.apache.geode.test.junit.rules.VMProvider;
import org.apache.geode.util.test.TestUtil;

public class CreateDataSourceCommandDUnitTest {

  private MemberVM locator, server1, server2;

  @Rule
  public ClusterStartupRule cluster = new ClusterStartupRule();

  @Rule
  public GfshCommandRule gfsh = new GfshCommandRule();

  @Before
  public void before() throws Exception {
    locator = cluster.startLocatorVM(0);
    server1 = cluster.startServerVM(1, "group1", locator.getPort());
    server2 = cluster.startServerVM(2, "group1", locator.getPort());

    gfsh.connectAndVerify(locator);
  }

  @Test
  public void testCreateDataSourceWithJarOptionDoesNotThrowDriverError() {
    String URL = "jdbc:mysql://localhost/";
    IgnoredException.addIgnoredException(
        "No suitable driver");
    IgnoredException.addIgnoredException(
        "create data-source failed");
    IgnoredException.addIgnoredException(
        "com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure");
    IgnoredException.addIgnoredException(
        "Access denied for user 'mySqlUser'@'localhost'");

    // create the data-source
    gfsh.executeAndAssertThat(
        "create data-source --name=mySqlDataSource --username=mySqlUser --password=mySqlPass --pooled=false --url=\""
            + URL + "\"")
        .statusIsError().containsOutput("No suitable driver");

    final String jdbcJarName = "mysql-connector-java-8.0.15.jar";
    File mySqlDriverFile = loadTestResource("/" + jdbcJarName);
    assertThat(mySqlDriverFile).exists();
    String jarFile = mySqlDriverFile.getAbsolutePath();

    IgnoredException.removeAllExpectedExceptions();

    gfsh.executeAndAssertThat("deploy --jar=" + jarFile + " --register-driver").statusIsSuccess();
    gfsh.executeAndAssertThat(
        "create data-source --name=mySqlDataSource --username=mySqlUser --password=mySqlPass --pooled=false --url=\""
            + URL + "\"")
        .containsOutput("Failed to connect to \"mySqlDataSource\". See log for details");

    gfsh.executeAndAssertThat("undeploy --jar=" + jdbcJarName).statusIsSuccess();

    gfsh.executeAndAssertThat(
        "create data-source --name=mySqlDataSource --username=mySqlUser --password=mySqlPass --pooled=false --url=\""
            + URL + "\"")
        .statusIsError().containsOutput("No suitable driver");
  }

  private File loadTestResource(String fileName) {
    String filePath = TestUtil.getResourcePath(this.getClass(), fileName);
    Assertions.assertThat(filePath).isNotNull();

    return new File(filePath);
  }

  @Test
  public void testCreateDataSource() throws Exception {
    VMProvider.invokeInEveryMember(
        () -> assertThat(JNDIInvoker.getNoOfAvailableDataSources()).isEqualTo(0), server1, server2);

    String URL = "jdbc:derby:memory:newDB;create=true";
    // create the data-source
    gfsh.executeAndAssertThat(
        "create data-source --name=jndi1 --username=myuser --password=mypass --pooled=false --url=\""
            + URL + "\"")
        .statusIsSuccess().tableHasColumnOnlyWithValues("Member", "server-1", "server-2");

    // verify cluster config is updated
    locator.invoke(() -> {
      InternalConfigurationPersistenceService ccService =
          ClusterStartupRule.getLocator().getConfigurationPersistenceService();
      CacheConfig cacheConfig = ccService.getCacheConfig("cluster");
      List<JndiBinding> jndiBindings = cacheConfig.getJndiBindings();
      assertThat(jndiBindings.size()).isEqualTo(1);
      JndiBinding jndiBinding = jndiBindings.get(0);
      assertThat(jndiBinding.getJndiName()).isEqualTo("jndi1");
      assertThat(jndiBinding.getUserName()).isEqualTo("myuser");
      assertThat(jndiBinding.getPassword()).isEqualTo("mypass");
      assertThat(jndiBinding.getConnectionUrl()).isEqualTo(URL);
      assertThat(jndiBinding.getType()).isEqualTo("SimpleDataSource");
    });

    // verify datasource exists
    VMProvider.invokeInEveryMember(
        () -> assertThat(JNDIInvoker.getNoOfAvailableDataSources()).isEqualTo(1), server1, server2);

    // bounce server1
    server1.stop(false);
    server1 = cluster.startServerVM(1, locator.getPort());

    // verify it has recreated the datasource from cluster config
    server1.invoke(() -> {
      assertThat(JNDIInvoker.getNoOfAvailableDataSources()).isEqualTo(1);
      assertThat(JNDIInvoker.getDataSource("jndi1")).isNotNull();
    });

    verifyThatNonExistentClassCausesGfshToError();
  }

  private void verifyThatNonExistentClassCausesGfshToError() {
    SerializableRunnableIF IgnoreClassNotFound = () -> {
      IgnoredException ex =
          new IgnoredException("non_existent_class_name");
      LogService.getLogger().info(ex.getAddMessage());
    };

    server1.invoke(IgnoreClassNotFound);
    server2.invoke(IgnoreClassNotFound);

    // create the binding
    gfsh.executeAndAssertThat(
        "create data-source --name=jndiBad --username=myuser --password=mypass --pooled --pooled-data-source-factory-class=non_existent_class_name --url=\"jdbc:derby:memory:newDB;create=true\"")
        .statusIsError();
  }
}
