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
//    private List<MemberVM> memberList = new ArrayList();
//    private final long SERVER_MAX_HEAP_BYTES = 30*1024*1024;
//    private final float DESIRED_REGION_LOAD = 0.8f;

    @Rule
    public GfshCommandRule gfsh = new GfshCommandRule();

    @Rule
    public MemberVM locator1;

    @Rule
    public ClusterStartupRule cluster = new ClusterStartupRule();


    @Before
    public void before() throws Exception {
        locator1 = cluster.startLocatorVM(0, 0);

        Properties serverProps = new Properties();
//        serverProps.setProperty(CliStrings.START_SERVER__INITIAL_HEAP, "30m");
//        serverProps.setProperty(CliStrings.START_SERVER__MAXHEAP, "30m");

//        memberList.add(cluster.startServerVM(1, serverProps, locator1.getPort()));
//        memberList.add(cluster.startServerVM(2, serverProps, locator1.getPort()));
//        memberList.add(cluster.startServerVM(3, serverProps, locator1.getPort()));
//        memberList.add(cluster.startServerVM(4, serverProps, locator1.getPort()));
//        memberList.add(cluster.startServerVM(5, serverProps, locator1.getPort()));
//        memberList.add(cluster.startServerVM(6, serverProps, locator1.getPort()));

        gfsh.connectAndVerify(locator1);

        gfsh.executeAndAssertThat("list members").statusIsSuccess();

        for (int i = 0; i < 1; ++i) {
            gfsh.executeAndAssertThat("start server --name=server" + i + " --server-port=" + (40404 + i) + " --J=-Xmx30m --J=-Xms30m").statusIsSuccess();
        }

        gfsh.executeAndAssertThat("create region --name=" + REGION_NAME
                + " --type=PARTITION --redundant-copies=2 --enable-statistics=true --recovery-delay=-1 --startup-recovery-delay=-1")
                .statusIsSuccess();

//        memberList.get(0).invoke(() -> {
//            Cache cache = ClusterStartupRule.getCache();
//            assertThat(cache).isNotNull();
//            Region<String, String> region = cache.getRegion(REGION_NAME);
//            int counter = 0;
//            while (getAverageLoad() < DESIRED_REGION_LOAD) {
//                region.put("key" + counter, "value" + counter);
//                ++counter;
//            }
//        });
    }

    @Test
    public void testRebalance() {

//        final ManagementService service = ManagementService.getManagementService(locator1.getCache());

//        // Stop servers
//        cluster.stop(2);
//        server2 = cluster.startServerVM(2, locator1.getPort());

//        await().until(() -> {
//            final DistributedRegionMXBean regionMxBean =
//                    service.getDistributedRegionMXBean("/" + REGION_NAME);
//            return regionMxBean != null && regionMxBean.getMemberCount() == 6;
//        });
//        final DistributedRegionMXBean regionMxBeanBeforeRebalance =
//                service.getDistributedRegionMXBean("/" + REGION_NAME);
//        assertThat(regionMxBeanBeforeRebalance).isNotNull();
//        assertThat(regionMxBeanBeforeRebalance.getNumBucketsWithoutRedundancy()).isNotEqualTo(0);
//
//        // First rebalance
//        server1.invoke(() -> {
//            Cache cache = ClusterStartupRule.getCache();
//            assertThat(cache).isNotNull();
//            RebalanceFactory rebalanceFactory = cache.getResourceManager().createRebalanceFactory();
//            RebalanceOperation firstRebalanceOperation = rebalanceFactory.start();
//            RebalanceResults firstRebalanceResults = firstRebalanceOperation.getResults();
//
//            System.out.println("[FIRST REBALANCE RESULTS]:" + firstRebalanceResults);
//            assertThat(firstRebalanceResults.getTotalBucketCreateBytes()).isGreaterThan(0);
//            assertThat(firstRebalanceResults.getTotalBucketCreatesCompleted()).isGreaterThan(0);
//            assertThat(firstRebalanceResults.getTotalPrimaryTransfersCompleted()).isGreaterThan(0);
//        });
//
//        // Assert there are no buckets without redundancy
//        Thread.sleep(5000);
//        final DistributedRegionMXBean regionMxBeanAfterRebalance =
//                service.getDistributedRegionMXBean("/" + REGION_NAME);
//        assertThat(regionMxBeanAfterRebalance).isNotNull();
//        assertThat(regionMxBeanAfterRebalance.getNumBucketsWithoutRedundancy()).isEqualTo(0);
////        assertRegionBalanced();
//
//        // Second Rebalance
//        server1.invoke(() -> {
//            Cache cache = ClusterStartupRule.getCache();
//            assertThat(cache).isNotNull();
//            RebalanceFactory rebalanceFactory = cache.getResourceManager().createRebalanceFactory();
//            RebalanceOperation firstRebalanceOperation = rebalanceFactory.start();
//            RebalanceResults firstRebalanceResults = firstRebalanceOperation.getResults();
//
//            System.out.println("[SECOND REBALANCE RESULTS]:" + firstRebalanceResults);
//            assertThat(firstRebalanceResults.getTotalBucketCreateBytes()).isEqualTo(0);
//            assertThat(firstRebalanceResults.getTotalBucketCreatesCompleted()).isEqualTo(0);
//            assertThat(firstRebalanceResults.getTotalPrimaryTransfersCompleted()).isEqualTo(0);
//        });
//    }

//    private void assertRegionBalanced() {
//        Integer size1 = server1.invoke(RebalanceExperimentTest::getLocalDataSizeForRegion);
//        Integer size2 = server2.invoke(RebalanceExperimentTest::getLocalDataSizeForRegion);
//        Integer size3 = server3.invoke(RebalanceExperimentTest::getLocalDataSizeForRegion);
//
//        assertThat(abs(size1 - size2)).isLessThanOrEqualTo(1);
//        assertThat(abs(size1 - size3)).isLessThanOrEqualTo(1);
//        assertThat(abs(size2 - size3)).isLessThanOrEqualTo(1);
    }

    //Calculates the difference in bytes between the amount of data stored on the most full server and the least full
//    private long getDataDistributionDifference() {
//        long least = Long.MAX_VALUE;
//        long most = Long.MIN_VALUE;
//        for (MemberVM member : memberList) {
//            long dataSize = member.invoke(RebalanceExperimentDUnitTest::getLocalAllocatedMemory);
//            if (dataSize < least) {
//                least = dataSize;
//            }
//            if (dataSize > most) {
//                most = dataSize;
//            }
//        }
//        return (most - least);
//    }
//
//    private float getAverageLoad() {
//        long totalLoad = 0;
//        long totalWeight = 0;
//        for (MemberVM member : memberList) {
//            totalLoad += member.invoke(RebalanceExperimentDUnitTest::getLocalAllocatedMemory);
//            totalWeight += SERVER_MAX_HEAP_BYTES;
//        }
//        float averageLoad = totalLoad/totalWeight;
//        return averageLoad;
//
//    }

    private static long getLocalAllocatedMemory() {
        InternalCache cache = ClusterStartupRule.getCache();
        assertThat(cache).isNotNull();
        Region<String, String> region = cache.getRegion(REGION_NAME);
        PartitionedRegion localRegion = (PartitionedRegion) PartitionRegionHelper.getLocalData(region);
        PartitionedRegionDataStore dataStore = localRegion.getDataStore();

        return dataStore.currentAllocatedMemory();
    }
}