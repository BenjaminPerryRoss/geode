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
package org.apache.geode.management.internal.cli.commands;

import static java.lang.Math.abs;
import static org.apache.geode.test.awaitility.GeodeAwaitility.await;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.distributed.internal.InternalConfigurationPersistenceService;
import org.apache.geode.internal.cache.InternalRegion;
import org.apache.geode.internal.cache.PartitionedRegion;
import org.apache.geode.internal.cache.PartitionedRegionDataStore;
import org.apache.geode.management.internal.cli.i18n.CliStrings;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.partition.PartitionRegionHelper;
import org.apache.geode.internal.cache.InternalCache;
import org.apache.geode.management.ManagementService;
import org.apache.geode.test.dunit.rules.ClusterStartupRule;
import org.apache.geode.test.dunit.rules.MemberVM;
import org.apache.geode.test.junit.rules.GfshCommandRule;
import org.apache.geode.test.junit.rules.LocatorStarterRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RebalanceExperimentDUnitTest {
    private static final String REGION_NAME = "GemfireRebalanceExperiementTestRegion";
    private static final String COLOCATED_REGION_ONE = "ColocatedRegionOne";
    private static final String COLOCATED_REGION_TWO = "ColocatedRegionTwo";
    private static final String COLOCATED_REGION_THREE = "ColocatedRegionThree";
    private static final String REGION_TWO = "GemfireRebalanceExperiementTestRegionTwo";
    private List<MemberVM> memberList = new ArrayList();
    private final int INITIAL_SERVERS = 6;
    private final int ADDITIONAL_SERVERS = 2;

    private RebalancePartitionResolver resolver = new RebalancePartitionResolver();

    @Rule
    public GfshCommandRule gfsh = new GfshCommandRule();

    @Rule
    public LocatorStarterRule locator1 = new LocatorStarterRule();

    @Rule
    public ClusterStartupRule cluster = new ClusterStartupRule();

    Region<String, String> region;
    InternalCache cache;


    @Before
    public void before() throws Exception {
        locator1.withJMXManager().startLocator();
        for (int i = 0; i < INITIAL_SERVERS; ++i) {
            memberList.add(cluster.startServerVM(i, locator1.getPort()));
        }

        gfsh.connectAndVerify(locator1);

        gfsh.executeAndAssertThat("create region --name=" + REGION_NAME
                + " --type=PARTITION --redundant-copies=2 --enable-statistics=true "
            + "--recovery-delay=-1 --startup-recovery-delay=-1 --total-num-buckets=48 "
            + "--partition-resolver=org.apache.geode.management.internal.cli.commands.RebalancePartitionResolver")
                .statusIsSuccess();

        gfsh.executeAndAssertThat("create region --name=" + COLOCATED_REGION_ONE
                + " --type=PARTITION --redundant-copies=2 --enable-statistics=true "
            + "--recovery-delay=-1 --startup-recovery-delay=-1 --total-num-buckets=48 "
            + "--partition-resolver=org.apache.geode.management.internal.cli.commands.RebalancePartitionResolver"
            + " --colocated-with=" + REGION_NAME)
                .statusIsSuccess();

        gfsh.executeAndAssertThat("create region --name=" + COLOCATED_REGION_TWO
                + " --type=PARTITION --redundant-copies=2 --enable-statistics=true "
            + "--recovery-delay=-1 --startup-recovery-delay=-1 --total-num-buckets=48 "
            + "--partition-resolver=org.apache.geode.management.internal.cli.commands.RebalancePartitionResolver"
            + " --colocated-with=" + REGION_NAME)
                .statusIsSuccess();

        gfsh.executeAndAssertThat("create region --name=" + COLOCATED_REGION_THREE
                + " --type=PARTITION --redundant-copies=2 --enable-statistics=true "
            + "--recovery-delay=-1 --startup-recovery-delay=-1 --total-num-buckets=48 "
            + "--partition-resolver=org.apache.geode.management.internal.cli.commands.RebalancePartitionResolver"
            + " --colocated-with=" + COLOCATED_REGION_TWO)
                .statusIsSuccess();

        gfsh.executeAndAssertThat("create region --name=" + REGION_TWO
            + " --type=PARTITION --redundant-copies=2 --enable-statistics=true "
            + "--recovery-delay=-1 --startup-recovery-delay=-1")
            .statusIsSuccess();

        cache = locator1.getCache();
    }

    @Test
    public void testSecondRebalanceIsNotNecessary() throws InterruptedException {

        int entries = 100000;
                memberList.get(0).invoke(() -> {
            Cache cache = ClusterStartupRule.getCache();
            assertThat(cache).isNotNull();
            Region<String, String> region = cache.getRegion(REGION_NAME);
            Region<String, String> colocatedRegionOne = cache.getRegion(COLOCATED_REGION_ONE);
            Region<String, String> colocatedRegionTwo = cache.getRegion(COLOCATED_REGION_TWO);
            Region<String, String> colocatedRegionThree = cache.getRegion(COLOCATED_REGION_THREE);
            Region<String, String> regionTwo = cache.getRegion(REGION_TWO);
            for(int i = 0; i < entries; i++) {
                region.put("key" + i, "value" + i);
                colocatedRegionOne.put("key" + i, "value" + i);
                colocatedRegionTwo.put("key" + i, "value" + i);
                colocatedRegionThree.put("key" + i, "value" + i);
                regionTwo.put("key" + i, "value" + i);
            }
        });

        for (int i = 0; i < ADDITIONAL_SERVERS; ++i) {
            memberList.add(cluster.startServerVM(INITIAL_SERVERS + i, locator1.getPort()));
        }

        gfsh.executeAndAssertThat("rebalance");

        Thread.sleep(2000);

        gfsh.executeAndAssertThat("rebalance").containsOutput("Total bytes in buckets moved during this rebalance                                              | 0")
            .containsOutput("Total number of redundant copies created during this rebalance                                  | 0")
            .containsOutput("Total primaries transferred during this rebalance                                               | 0")
            .containsOutput("Total number of buckets moved during this rebalance                                             | 0");

        for (int i = 0; i < memberList.size(); ++i) {
            gfsh.executeAndAssertThat(
                "show metrics --member=" + memberList.get(i).getName() + " --region=" + REGION_NAME
                    + " --categories=partition");
        }

        for (int i = 0; i < memberList.size(); ++i) {
            gfsh.executeAndAssertThat(
                "show metrics --member=" + memberList.get(i).getName() + " --region=" + REGION_TWO
                    + " --categories=partition");
        }
    }
}