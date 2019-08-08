package org.apache.geode.internal.cache.wan.asyncqueue;


import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.junit.ClassRule;
import org.junit.Test;

import org.apache.geode.cache.PartitionAttributes;
import org.apache.geode.cache.PartitionAttributesFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionShortcut;
import org.apache.geode.cache.asyncqueue.AsyncEvent;
import org.apache.geode.cache.asyncqueue.AsyncEventListener;
import org.apache.geode.internal.cache.InternalCache;
import org.apache.geode.internal.logging.LogService;
import org.apache.geode.test.dunit.rules.ClusterStartupRule;
import org.apache.geode.test.dunit.rules.MemberVM;

public class AsyncAdhocTest implements Serializable {

  @ClassRule
  public static ClusterStartupRule cluster = new ClusterStartupRule(3);

  private static MemberVM locator, server;


  private Properties testProperties() {
    Properties properties = new Properties();
    properties.setProperty("log-level", "info");
    return properties;
  }

  public void startCluster() {
    locator = cluster.startLocatorVM(0, testProperties());
    server = cluster.startServerVM(1, testProperties(), locator.getPort());
  }

  @Test
  public void test() throws Exception {
    startCluster();

    server.invoke(() -> {
      createAEQAndRegions();

      final InternalCache cache = ClusterStartupRule.getCache();
      Region childRegion = cache.getRegion("childRegion");
      childRegion.put("A", "A");
      childRegion.put("A", "B");
      childRegion.put("A", "C");

    });

    server.stop(false);
    LogService.getLogger().info("JASON STOPPED SERVER");

    cluster.startServerVM(1, testProperties(), locator.getPort());
    LogService.getLogger().info("JASON RESTARTED SERVER");



    server.invoke(() -> {
      final InternalCache cache = ClusterStartupRule.getCache();
      cache.createAsyncEventQueueFactory().setParallel(true).setPersistent(true).setBatchConflationEnabled(true).create("childAEQ",
          new AsyncEventListener() {
            @Override
            public boolean processEvents(List<AsyncEvent> events) {
              LogService.getLogger().info("JASON Child AEQ processing events returning false" + events.size());

              return false;
            }
          });

      Thread.sleep(5000);

      PartitionAttributes parentPA =
          new PartitionAttributesFactory().setTotalNumBuckets(3).create();
        cache.createRegionFactory(RegionShortcut.PARTITION_PERSISTENT).setPartitionAttributes(parentPA).create("parentRegion");


      PartitionAttributes pa = new PartitionAttributesFactory().setTotalNumBuckets(3)
          .setColocatedWith("parentRegion").create();

      Region childRegion = cache.createRegionFactory(RegionShortcut.PARTITION_PERSISTENT)
          .addAsyncEventQueueId("childAEQ").setPartitionAttributes(pa).create("childRegion");



      // final InternalCache cache = ClusterStartupRule.getCache();
      // Region childRegion = cache.getRegion("childRegion");
      LogService.getLogger().info("JASON GET RETURNED:" + childRegion.get("A"));
    });


    try {
      LogService.getLogger().info("JASON SLEEP");
      Thread.sleep(8000);
    } catch (InterruptedException e) {

    }
  }

  private void createAEQAndRegions() {
    final InternalCache cache = ClusterStartupRule.getCache();
    cache.createAsyncEventQueueFactory().setParallel(true).setPersistent(true).create("childAEQ",
        new AsyncEventListener() {
          @Override
          public boolean processEvents(List<AsyncEvent> events) {
            LogService.getLogger().info("JASON Child AEQ processing events returning false" + events.size());
//            LogService.getLogger().info("JASON do we have this relationship" + events.get(0).getRegion().getParentRegion());

            return false;
          }
        });

    PartitionAttributes parentPA = new PartitionAttributesFactory().setTotalNumBuckets(3).create();
    cache.createRegionFactory(RegionShortcut.PARTITION_PERSISTENT).setPartitionAttributes(parentPA)
        .create("parentRegion");

    PartitionAttributes pa = new PartitionAttributesFactory().setTotalNumBuckets(3)
        .setColocatedWith("parentRegion").create();
    Region childRegion = cache.createRegionFactory(RegionShortcut.PARTITION_PERSISTENT)
        .addAsyncEventQueueId("childAEQ").setPartitionAttributes(pa).create("childRegion");
  }

}
