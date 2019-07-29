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


import static org.apache.geode.test.util.ResourceUtils.createTempFileFromResource;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.geode.cache.RegionShortcut;
import org.jetbrains.annotations.NotNull;
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
  private static boolean COLLISION_FORCED = false;
  private static boolean USE_COUNTER_IN_FIELDNAME = false;
  private static boolean USE_RANDOM_JSON_FIELD_ORDER = true;
  private static boolean USE_SINGLE_JSON_FIELD = false;
  private static String USE_SORTED_JSON_HELPER = "true";
  private static final int ENTRIES = 10000;

  private MemberVM locator, server1, server2;

  @Rule
  public ClusterStartupRule cluster = new ClusterStartupRule();

  @Rule
  public GfshCommandRule gfsh = new GfshCommandRule();

  @Before
  public void before() throws Exception {
    locator = cluster.startLocatorVM(0);

    server1 = cluster.startServerVM(1, locator.getPort());
    server1.invoke(() -> {
      ClusterStartupRule.getCache().createRegionFactory(RegionShortcut.REPLICATE).create(REGION_NAME);
      System.setProperty(JSONFormatter.SORT_JSON_FIELD_NAMES_PROPERTY, USE_SORTED_JSON_HELPER);
    });
    server2 = cluster.startServerVM(2, locator.getPort());
    server2.invoke(() -> {
      System.setProperty(JSONFormatter.SORT_JSON_FIELD_NAMES_PROPERTY, USE_SORTED_JSON_HELPER);
    });

//
//    gfsh.connectAndVerify(locator);
//
//    gfsh.executeAndAssertThat("create region --type=REPLICATE --name=" + REGION_NAME)
//        .statusIsSuccess();
  }

  @Test
  public void detectPdxTypeIdCollision() {

    File source = loadTestResource("/org/apache/geode/pdx/jsonStrings/json4.txt");
    assertThat(source.exists());
    String filePath = source.getAbsolutePath();

    server1.invoke(() -> {
      InternalCache cache = ClusterStartupRule.getCache();
      Region region = cache.getRegion(REGION_NAME);
      PeerTypeRegistration registration =
          (PeerTypeRegistration) cache.getPdxRegistry().getTypeRegistration();

      String field;
      String jsonString;
      PdxInstance instance;
      List<String> collidingStrings = null;
      if (COLLISION_FORCED) {
        collidingStrings = generateCollidingStrings(ENTRIES);
      }

      List<String> jsonLines = null;
      if (!USE_SINGLE_JSON_FIELD) {
        jsonLines = getJSONLines(filePath);
      }

      Long elapsedTime = 0L;
      Long startTime = System.currentTimeMillis();

      for (int i = 0; i < ENTRIES; ++i) {
        if (COLLISION_FORCED) {
          field = "\"" + collidingStrings.get(i) + "\": " + i;
        } else if (USE_COUNTER_IN_FIELDNAME) {
          field = "\"counter" + i + "\": " + i;
        } else {
          field = "\"counter\": " + i;
        }

        if (USE_SINGLE_JSON_FIELD || COLLISION_FORCED) {
          jsonString = START_JSON + field + END_JSON;
        } else {
          if (USE_RANDOM_JSON_FIELD_ORDER) {
            Collections.shuffle(jsonLines.subList(6, 47));
          }
          jsonString = buildJSONString(jsonLines)
              .replace("\"taglib-location\": \"/WEB-INF/tlds/cofax.tld\"", field);
        }

        instance = JSONFormatter.fromJSON(jsonString);

        region.put(i, instance);

        if (i % 10000 == 0 && i != 0) {
          elapsedTime = System.currentTimeMillis() - startTime;
          LogService.getLogger().info("Last 10000 puts took " + elapsedTime + "ms.\n" +
              "Average time per put was " + elapsedTime / 10000 + "ms.\n" +
              "Average time for getExistingIdForType() was "
              + registration.calculateGetExistingIdDuration() + "ms.\n" +
              "Total collisions so far = " + registration.collisions() + "\n" +
              "Number of PDXTypes = " + cache.getPdxRegistry().typeMap().size());
          startTime = System.currentTimeMillis();
        }
      }

      elapsedTime = System.currentTimeMillis() - startTime;
      LogService.getLogger().info("Last 10000 puts took " + elapsedTime + "ms.\n" +
          "Average time per put was " + elapsedTime / 10000 + "ms.\n" +
          "Average time for getExistingIdForType() was "
          + registration.calculateGetExistingIdDuration() + "ms.\n" +
          "Total collisions so far = " + registration.collisions() + "\n" +
          "Number of PDXTypes = " + cache.getPdxRegistry().typeMap().size());

    });
  }

  @NotNull
  private static List<String> getJSONLines(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(filePath));
    List<String> jsonLines = new ArrayList<>();
    while (scanner.hasNext()) {
      jsonLines.add(scanner.nextLine());
    }
    return jsonLines;
  }

  @NotNull
  private static String buildJSONString(List<String> jsonLines) {
    StringBuilder jsonString = new StringBuilder();
    for (int i = 0; i < jsonLines.size(); ++i) {
      String line = jsonLines.get(i);
      jsonString.append(line + "\n");
    }
    return jsonString.toString();
  }

  private File loadTestResource(String fileName) {
    String filePath = createTempFileFromResource(getClass(), fileName).getAbsolutePath();
    assertThat(filePath).isNotNull();

    return new File(filePath);
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
