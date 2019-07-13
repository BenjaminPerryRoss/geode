package org.apache.geode.pdx;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionFactory;
import org.apache.geode.cache.RegionShortcut;
import org.apache.geode.test.dunit.rules.ClusterStartupRule;
import org.apache.geode.test.dunit.rules.MemberVM;
import org.apache.geode.test.junit.rules.GfshCommandRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class JSONPdxTypeGenerationIssuesDUnitTest {

    private static final String REGION_NAME = "testRegion";
    private final String START_JSON = "{\"@type\": \"org.apache.geode.pdx.TestObject\",";
    private final String END_JSON = "}";
    private final int ENTRIES = 1000000;
    private Region region;

    private MemberVM locator, server1, server2;

    @Rule
    public ClusterStartupRule cluster = new ClusterStartupRule();

    @Rule
    public GfshCommandRule gfsh = new GfshCommandRule();

    @Before
    public void before() throws Exception {
        locator = cluster.startLocatorVM(0);
        server1 = cluster.startServerVM(1, locator.getPort());
        server2 = cluster.startServerVM(2, locator.getPort());

        gfsh.connectAndVerify(locator);
    }

    @Test
    public void detectPdxTypeIdCollision(){
        server1.invoke(() -> {
            Cache cache = ClusterStartupRule.getCache();

            RegionFactory<Integer, Integer> dataRegionFactory =
                    cache.createRegionFactory(RegionShortcut.REPLICATE);
            region = dataRegionFactory.create(REGION_NAME);
            String field = "";
            String jsonString = "";
            PdxInstance instance = null;
            for (int i = 0; i < ENTRIES; ++i) {
                field = "\"counter" + i + "\": " + i;
                jsonString = START_JSON + field + END_JSON;
                instance = JSONFormatter.fromJSON(jsonString);
                region.put(i,instance);
            }
        });
    }
}
