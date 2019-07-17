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
package org.apache.geode.pdx;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.geode.cache.Region;
import org.apache.geode.internal.cache.InternalCache;
import org.apache.geode.internal.logging.LogService;
import org.apache.geode.pdx.internal.PeerTypeRegistration;
import org.apache.geode.test.dunit.rules.ClusterStartupRule;
import org.apache.geode.test.dunit.rules.MemberVM;
import org.apache.geode.test.junit.rules.GfshCommandRule;

public class JSONPdxTypeGenerationIssuesDUnitTest {

  private static final String REGION_NAME = "testRegion";
  private static final String START_JSON = "{";
  private static final String END_JSON = "}";
  private static final boolean COLLISION_FORCED = false;
  private static final boolean USE_ONE_TYPE = false;
  private static final int ENTRIES = 100000;

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

    gfsh.executeAndAssertThat("create region --type=REPLICATE --name=" + REGION_NAME)
        .statusIsSuccess();
  }

  @Test
  public void detectPdxTypeIdCollision() {

    server1.invoke(() -> {
      InternalCache cache = ClusterStartupRule.getCache();
      Region region = cache.getRegion(REGION_NAME);
      PeerTypeRegistration registration =
          (PeerTypeRegistration) cache.getPdxRegistry().getTypeRegistration();

      String field;
      String jsonString;
      PdxInstance instance;

      List<String> collidingStrings = generateCollidingStrings(ENTRIES);

      Long elapsedTime = 0L;
      Long startTime = System.currentTimeMillis();

      for (int i = 0; i < ENTRIES; ++i) {
        if (COLLISION_FORCED) {
          field = "\"" + collidingStrings.get(i) + "\": " + i;
        } else if (USE_ONE_TYPE) {
          field = "\"counter\": " + i;
        } else {
          field = "\"counter" + i + "\": " + i;
        }
        String filePath =
            "/Users/doevans/workspace/geode/geode-core/src/distributedTest/resources/org/apache/geode/pdx/jsonStrings/json4.txt";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        jsonString = content.replace("\"taglib-location\": \"/WEB-INF/tlds/cofax.tld\"", field);
        // jsonString = START_JSON + field + END_JSON;

        instance = JSONFormatter.fromJSON(jsonString);
        region.put(i, instance);

        if (i % 10000 == 0 && i != 0) {
          elapsedTime = System.currentTimeMillis() - startTime;
          LogService.getLogger().info("Last 10000 puts took " + elapsedTime + " ms.\n" +
              "Average time per put was " + elapsedTime / 10000 + "ms.\n" +
              "DEE Total collisions so far = " + registration.collisions() + "\n" +
              "i = " + i + ", counter = " + registration.getCounter());
          startTime = System.currentTimeMillis();
        }
      }

      elapsedTime = System.currentTimeMillis() - startTime;
      LogService.getLogger().info("Last 10000 puts took " + elapsedTime + " ms. \n" +
          "Average time per put was " + elapsedTime / 10000 + "ms.\n" +
          "DEE Total collisions = " + registration.collisions());

    });
  }

  private static List<String> generateCollidingStrings(int numberOfStrings) {
    final List<String> baseStrings = new ArrayList<>();
    baseStrings.add("Aa");
    baseStrings.add("BB");
    baseStrings.add("C#");
    if (numberOfStrings <= 0) {
      return null;
    }

    if (numberOfStrings <= 3 && numberOfStrings > 0) {
      return baseStrings.subList(0, numberOfStrings - 1);
    }
    int stringElements = (int) Math.ceil(Math.log(numberOfStrings) / Math.log(baseStrings.size()));

    List<String> result = new ArrayList(baseStrings);
    for (int i = 0; i < stringElements - 1; ++i) {
      List<String> partialResult = new ArrayList();
      for (String element : baseStrings) {
        for (String string : result) {
          partialResult.add(string + element);
        }
      }
      result = partialResult;
    }
    return result.subList(0, numberOfStrings);
  }
}
