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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.control.RebalanceFactory;
import org.apache.geode.cache.control.RebalanceOperation;
import org.apache.geode.cache.control.RebalanceResults;
import org.apache.geode.cache.partition.PartitionRegionHelper;
import org.apache.geode.internal.cache.InternalCache;
import org.apache.geode.management.DistributedRegionMXBean;
import org.apache.geode.management.ManagementService;
import org.apache.geode.test.dunit.rules.ClusterStartupRule;
import org.apache.geode.test.dunit.rules.MemberVM;
import org.apache.geode.test.junit.rules.GfshCommandRule;
import org.apache.geode.test.junit.rules.LocatorStarterRule;

import java.util.ArrayList;
import java.util.List;

public class RebalanceExperimentTest {
    private static final String REGION_NAME = "GemfireDataCommandsDUnitTestRegion";

    @Rule
    public GfshCommandRule gfsh = new GfshCommandRule();

    @Rule
    public LocatorStarterRule locator1 = new LocatorStarterRule();

    @Rule
    public ClusterStartupRule cluster = new ClusterStartupRule();

    private List<MemberVM> memberList = new ArrayList();

    @Before
    public void before() throws Exception {
        locator1.withJMXManager().startLocator();
        memberList.add(cluster.startServerVM(1, locator1.getPort()));
        memberList.add(cluster.startServerVM(2, locator1.getPort()));
        memberList.add(cluster.startServerVM(3, locator1.getPort()));
        memberList.add(cluster.startServerVM(4, locator1.getPort()));
        memberList.add(cluster.startServerVM(5, locator1.getPort()));
        memberList.add(cluster.startServerVM(6, locator1.getPort()));

        gfsh.connectAndVerify(locator1);
        gfsh.executeAndAssertThat("create region --name=" + REGION_NAME
                + " --type=PARTITION --redundant-copies=2 --enable-statistics=true --recovery-delay=-1 --startup-recovery-delay=-1")
                .statusIsSuccess();

        memberList.get(0).invoke(() -> {
            Cache cache = ClusterStartupRule.getCache();
            assertThat(cache).isNotNull();
            Region<String, String> region = cache.getRegion(REGION_NAME);

            region.put("key", "value");
        });
    }

    @Test
    public void testRebalance() throws InterruptedException {
        final ManagementService service = ManagementService.getManagementService(locator1.getCache());

        // Stop servers
        cluster.stop(2);
        server2 = cluster.startServerVM(2, locator1.getPort());

        await().until(() -> {
            final DistributedRegionMXBean regionMxBean =
                    service.getDistributedRegionMXBean("/" + REGION_NAME);
            return regionMxBean != null && regionMxBean.getMemberCount() == 3;
        });
        final DistributedRegionMXBean regionMxBeanBeforeRebalance =
                service.getDistributedRegionMXBean("/" + REGION_NAME);
        assertThat(regionMxBeanBeforeRebalance).isNotNull();
        assertThat(regionMxBeanBeforeRebalance.getNumBucketsWithoutRedundancy()).isNotEqualTo(0);

        // First rebalance
        server1.invoke(() -> {
            Cache cache = ClusterStartupRule.getCache();
            assertThat(cache).isNotNull();
            RebalanceFactory rebalanceFactory = cache.getResourceManager().createRebalanceFactory();
            RebalanceOperation firstRebalanceOperation = rebalanceFactory.start();
            RebalanceResults firstRebalanceResults = firstRebalanceOperation.getResults();

            System.out.println("[FIRST REBALANCE RESULTS]:" + firstRebalanceResults);
            assertThat(firstRebalanceResults.getTotalBucketCreateBytes()).isGreaterThan(0);
            assertThat(firstRebalanceResults.getTotalBucketCreatesCompleted()).isGreaterThan(0);
            assertThat(firstRebalanceResults.getTotalPrimaryTransfersCompleted()).isGreaterThan(0);
        });

        // Assert there are no buckets without redundancy
        Thread.sleep(5000);
        final DistributedRegionMXBean regionMxBeanAfterRebalance =
                service.getDistributedRegionMXBean("/" + REGION_NAME);
        assertThat(regionMxBeanAfterRebalance).isNotNull();
        assertThat(regionMxBeanAfterRebalance.getNumBucketsWithoutRedundancy()).isEqualTo(0);
        assertRegionBalanced();

        // Second Rebalance
        server1.invoke(() -> {
            Cache cache = ClusterStartupRule.getCache();
            assertThat(cache).isNotNull();
            RebalanceFactory rebalanceFactory = cache.getResourceManager().createRebalanceFactory();
            RebalanceOperation firstRebalanceOperation = rebalanceFactory.start();
            RebalanceResults firstRebalanceResults = firstRebalanceOperation.getResults();

            System.out.println("[SECOND REBALANCE RESULTS]:" + firstRebalanceResults);
            assertThat(firstRebalanceResults.getTotalBucketCreateBytes()).isEqualTo(0);
            assertThat(firstRebalanceResults.getTotalBucketCreatesCompleted()).isEqualTo(0);
            assertThat(firstRebalanceResults.getTotalPrimaryTransfersCompleted()).isEqualTo(0);
        });
    }

    private void assertRegionBalanced() {
        Integer size1 = server1.invoke(RebalanceExperimentTest::getLocalDataSizeForRegion);
        Integer size2 = server2.invoke(RebalanceExperimentTest::getLocalDataSizeForRegion);
        Integer size3 = server3.invoke(RebalanceExperimentTest::getLocalDataSizeForRegion);

        assertThat(abs(size1 - size2)).isLessThanOrEqualTo(1);
        assertThat(abs(size1 - size3)).isLessThanOrEqualTo(1);
        assertThat(abs(size2 - size3)).isLessThanOrEqualTo(1);
    }

    private static Integer getLocalDataSizeForRegion() {
        InternalCache cache = ClusterStartupRule.getCache();
        assertThat(cache).isNotNull();
        Region<String, String> region = cache.getRegion(REGION_NAME);

        return PartitionRegionHelper.getLocalData(region).size();
    }
}