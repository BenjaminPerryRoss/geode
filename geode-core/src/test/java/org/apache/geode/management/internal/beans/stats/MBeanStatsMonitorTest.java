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
package org.apache.geode.management.internal.beans.stats;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import org.apache.geode.StatisticDescriptor;
import org.apache.geode.Statistics;
import org.apache.geode.StatisticsType;
import org.apache.geode.i18n.LogWriterI18n;
import org.apache.geode.internal.statistics.FakeValueMonitor;
import org.apache.geode.internal.statistics.ValueMonitor;
import org.apache.geode.management.internal.beans.stats.MBeanStatsMonitor.DefaultHashMap;

public class MBeanStatsMonitorTest {

  private ValueMonitor statsMonitor;

  private StatisticDescriptor[] descriptors;

  private Map<String, Number> expectedStatsMap;

  @Spy
  private DefaultHashMap statsMap;
  @Mock
  private LogWriterI18n logWriter;
  @Mock
  private Statistics stats;
  @Mock
  private StatisticsType statsType;

  @InjectMocks
  private MBeanStatsMonitor mbeanStatsMonitor;

  @Rule
  public TestName testName = new TestName();

  @Before
  public void setUp() throws Exception {
    this.statsMonitor = spy(new FakeValueMonitor());
    this.mbeanStatsMonitor =
        new MBeanStatsMonitor(this.testName.getMethodName(), this.statsMonitor);
    MockitoAnnotations.initMocks(this);

    this.expectedStatsMap = new HashMap<>();
    this.descriptors = new StatisticDescriptor[3];
    for (int i = 0; i < this.descriptors.length; i++) {
      String key = "stat-" + String.valueOf(i + 1);
      Number value = i + 1;

      this.expectedStatsMap.put(key, value);

      this.descriptors[i] = mock(StatisticDescriptor.class);
      when(this.descriptors[i].getName()).thenReturn(key);
      when(this.stats.get(this.descriptors[i])).thenReturn(value);
    }

    when(this.statsType.getStatistics()).thenReturn(this.descriptors);
    when(this.stats.getType()).thenReturn(this.statsType);
  }

  @Test
  public void addStatisticsToMonitorShouldAddToInternalMap() throws Exception {
    this.mbeanStatsMonitor.addStatisticsToMonitor(this.stats);

    assertThat(statsMap.getInternalMap()).containsAllEntriesOf(this.expectedStatsMap);
  }

  @Test
  public void addStatisticsToMonitorShouldAddListener() throws Exception {
    this.mbeanStatsMonitor.addStatisticsToMonitor(this.stats);

    verify(this.statsMonitor, times(1)).addListener(this.mbeanStatsMonitor);
  }

  @Test
  public void addStatisticsToMonitorShouldAddStatistics() throws Exception {
    this.mbeanStatsMonitor.addStatisticsToMonitor(this.stats);

    verify(this.statsMonitor, times(1)).addStatistics(this.stats);
  }

  @Test
  public void addNullStatisticsToMonitorShouldThrowNPE() throws Exception {
    assertThatThrownBy(() -> this.mbeanStatsMonitor.addStatisticsToMonitor(null))
        .isExactlyInstanceOf(NullPointerException.class);
  }

}
