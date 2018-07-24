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
package org.apache.geode.cache.client.internal;

import org.apache.geode.statistics.StatisticDescriptor;
import org.apache.geode.statistics.Statistics;
import org.apache.geode.statistics.StatisticsFactory;
import org.apache.geode.statistics.StatisticsType;
import org.apache.geode.statistics.StatisticsTypeFactory;
import org.apache.geode.distributed.internal.DistributionStats;
import org.apache.geode.internal.cache.PoolStats;
import org.apache.geode.internal.cache.tier.sockets.MessageStats;
import org.apache.geode.internal.statistics.StatisticsTypeFactoryImpl;

/**
 * Stats for a client to server {@link Connection}
 *
 * @since GemFire 5.7
 */
public class ConnectionStats implements MessageStats {
  // static fields
  private StatisticsType type;
  private StatisticsType sendType;

  ///////////////////////////////////////////////////////////////////////
  /*
   * private int opInProgressId; private int opSendInProgressId; private
   * static final int opSendFailedId; private int opSendId; private int
   * opSendDurationId; private int opTimedOutId; private int opFailedId;
   * private int opId; private int opDurationId;
   */
  ///////////////////////////////////////////////////////////////////////

  private int getInProgressId;
  private int getSendInProgressId;
  private int getSendFailedId;
  private int getSendId;
  private int getSendDurationId;
  private int getTimedOutId;
  private int getFailedId;
  private int getId;
  private int getDurationId;

  private int putInProgressId;
  private int putSendInProgressId;
  private int putSendFailedId;
  private int putSendId;
  private int putSendDurationId;
  private int putTimedOutId;
  private int putFailedId;
  private int putId;
  private int putDurationId;

  private int destroyInProgressId;
  private int destroySendInProgressId;
  private int destroySendFailedId;
  private int destroySendId;
  private int destroySendDurationId;
  private int destroyTimedOutId;
  private int destroyFailedId;
  private int destroyId;
  private int destroyDurationId;

  private int destroyRegionInProgressId;
  private int destroyRegionSendInProgressId;
  private int destroyRegionSendFailedId;
  private int destroyRegionSendId;
  private int destroyRegionSendDurationId;
  private int destroyRegionTimedOutId;
  private int destroyRegionFailedId;
  private int destroyRegionId;
  private int destroyRegionDurationId;

  private int clearInProgressId;
  private int clearSendInProgressId;
  private int clearSendFailedId;
  private int clearSendId;
  private int clearSendDurationId;
  private int clearTimedOutId;
  private int clearFailedId;
  private int clearId;
  private int clearDurationId;

  private int containsKeyInProgressId;
  private int containsKeySendInProgressId;
  private int containsKeySendFailedId;
  private int containsKeySendId;
  private int containsKeySendDurationId;
  private int containsKeyTimedOutId;
  private int containsKeyFailedId;
  private int containsKeyId;
  private int containsKeyDurationId;

  private int keySetInProgressId;
  private int keySetSendInProgressId;
  private int keySetSendFailedId;
  private int keySetSendId;
  private int keySetSendDurationId;
  private int keySetTimedOutId;
  private int keySetFailedId;
  private int keySetId;
  private int keySetDurationId;

  private int commitInProgressId;
  private int commitSendInProgressId;
  private int commitSendFailedId;
  private int commitSendId;
  private int commitSendDurationId;

  private int commitFailedId;
  private int commitTimedOutId;
  private int commitId;
  private int commitDurationId;

  private int rollbackInProgressId;
  private int rollbackSendInProgressId;
  private int rollbackSendFailedId;
  private int rollbackSendId;
  private int rollbackSendDurationId;

  private int rollbackFailedId;
  private int rollbackTimedOutId;
  private int rollbackId;
  private int rollbackDurationId;

  private int getEntryInProgressId;
  private int getEntrySendInProgressId;
  private int getEntrySendFailedId;
  private int getEntrySendId;
  private int getEntrySendDurationId;

  private int getEntryFailedId;
  private int getEntryTimedOutId;
  private int getEntryId;
  private int getEntryDurationId;

  private int txSynchronizationInProgressId;
  private int txSynchronizationSendInProgressId;
  private int txSynchronizationSendFailedId;
  private int txSynchronizationSendId;
  private int txSynchronizationSendDurationId;

  private int txSynchronizationFailedId;
  private int txSynchronizationTimedOutId;
  private int txSynchronizationId;
  private int txSynchronizationDurationId;

  private int txFailoverInProgressId;
  private int txFailoverSendInProgressId;
  private int txFailoverSendFailedId;
  private int txFailoverSendId;
  private int txFailoverSendDurationId;

  private int txFailoverFailedId;
  private int txFailoverTimedOutId;
  private int txFailoverId;
  private int txFailoverDurationId;

  private int sizeInProgressId;
  private int sizeSendInProgressId;
  private int sizeSendFailedId;
  private int sizeSendId;
  private int sizeSendDurationId;

  private int sizeFailedId;
  private int sizeTimedOutId;
  private int sizeId;
  private int sizeDurationId;

  private int invalidateInProgressId;
  private int invalidateSendInProgressId;
  private int invalidateSendFailedId;
  private int invalidateSendId;
  private int invalidateSendDurationId;

  private int invalidateFailedId;
  private int invalidateTimedOutId;
  private int invalidateId;
  private int invalidateDurationId;


  private int registerInterestInProgressId;
  private int registerInterestSendInProgressId;
  private int registerInterestSendFailedId;
  private int registerInterestSendId;
  private int registerInterestSendDurationId;
  private int registerInterestTimedOutId;
  private int registerInterestFailedId;
  private int registerInterestId;
  private int registerInterestDurationId;

  private int unregisterInterestInProgressId;
  private int unregisterInterestSendInProgressId;
  private int unregisterInterestSendFailedId;
  private int unregisterInterestSendId;
  private int unregisterInterestSendDurationId;
  private int unregisterInterestTimedOutId;
  private int unregisterInterestFailedId;
  private int unregisterInterestId;
  private int unregisterInterestDurationId;

  private int queryInProgressId;
  private int querySendInProgressId;
  private int querySendFailedId;
  private int querySendId;
  private int querySendDurationId;
  private int queryTimedOutId;
  private int queryFailedId;
  private int queryId;
  private int queryDurationId;

  private int createCQInProgressId;
  private int createCQSendInProgressId;
  private int createCQSendFailedId;
  private int createCQSendId;
  private int createCQSendDurationId;
  private int createCQTimedOutId;
  private int createCQFailedId;
  private int createCQId;
  private int createCQDurationId;
  private int stopCQInProgressId;
  private int stopCQSendInProgressId;
  private int stopCQSendFailedId;
  private int stopCQSendId;
  private int stopCQSendDurationId;
  private int stopCQTimedOutId;
  private int stopCQFailedId;
  private int stopCQId;
  private int stopCQDurationId;
  private int closeCQInProgressId;
  private int closeCQSendInProgressId;
  private int closeCQSendFailedId;
  private int closeCQSendId;
  private int closeCQSendDurationId;
  private int closeCQTimedOutId;
  private int closeCQFailedId;
  private int closeCQId;
  private int closeCQDurationId;
  private int gatewayBatchInProgressId;
  private int gatewayBatchSendInProgressId;
  private int gatewayBatchSendFailedId;
  private int gatewayBatchSendId;
  private int gatewayBatchSendDurationId;
  private int gatewayBatchTimedOutId;
  private int gatewayBatchFailedId;
  private int gatewayBatchId;
  private int gatewayBatchDurationId;
  private int getDurableCQsInProgressId;
  private int getDurableCQsSendsInProgressId;
  private int getDurableCQsSendFailedId;
  private int getDurableCQsSendId;
  private int getDurableCQsSendDurationId;
  private int getDurableCQsTimedOutId;
  private int getDurableCQsFailedId;
  private int getDurableCQsId;
  private int getDurableCQsDurationId;

  private int readyForEventsInProgressId;
  private int readyForEventsSendInProgressId;
  private int readyForEventsSendFailedId;
  private int readyForEventsSendId;
  private int readyForEventsSendDurationId;
  private int readyForEventsTimedOutId;
  private int readyForEventsFailedId;
  private int readyForEventsId;
  private int readyForEventsDurationId;

  private int makePrimaryInProgressId;
  private int makePrimarySendInProgressId;
  private int makePrimarySendFailedId;
  private int makePrimarySendId;
  private int makePrimarySendDurationId;
  private int makePrimaryTimedOutId;
  private int makePrimaryFailedId;
  private int makePrimaryId;
  private int makePrimaryDurationId;

  private int closeConInProgressId;
  private int closeConSendInProgressId;
  private int closeConSendFailedId;
  private int closeConSendId;
  private int closeConSendDurationId;
  private int closeConTimedOutId;
  private int closeConFailedId;
  private int closeConId;
  private int closeConDurationId;

  private int primaryAckInProgressId;
  private int primaryAckSendInProgressId;
  private int primaryAckSendFailedId;
  private int primaryAckSendId;
  private int primaryAckSendDurationId;
  private int primaryAckTimedOutId;
  private int primaryAckFailedId;
  private int primaryAckId;
  private int primaryAckDurationId;

  private int pingInProgressId;
  private int pingSendInProgressId;
  private int pingSendFailedId;
  private int pingSendId;
  private int pingSendDurationId;
  private int pingTimedOutId;
  private int pingFailedId;
  private int pingId;
  private int pingDurationId;

  private int registerInstantiatorsInProgressId;
  private int registerInstantiatorsSendInProgressId;
  private int registerInstantiatorsSendFailedId;
  private int registerInstantiatorsSendId;
  private int registerInstantiatorsSendDurationId;
  private int registerInstantiatorsTimedOutId;
  private int registerInstantiatorsFailedId;
  private int registerInstantiatorsId;
  private int registerInstantiatorsDurationId;

  private int registerDataSerializersInProgressId;
  private int registerDataSerializersSendInProgressId;
  private int registerDataSerializersSendFailedId;
  private int registerDataSerializersSendId;
  private int registerDataSerializersSendDurationId;
  private int registerDataSerializersTimedOutId;
  private int registerDataSerializersFailedId;
  private int registerDataSerializersId;
  private int registerDataSerializersDurationId;

  private int putAllInProgressId;
  private int putAllSendInProgressId;
  private int putAllSendFailedId;
  private int putAllSendId;
  private int putAllSendDurationId;
  private int putAllTimedOutId;
  private int putAllFailedId;
  private int putAllId;
  private int putAllDurationId;

  private int removeAllInProgressId;
  private int removeAllSendInProgressId;
  private int removeAllSendFailedId;
  private int removeAllSendId;
  private int removeAllSendDurationId;
  private int removeAllTimedOutId;
  private int removeAllFailedId;
  private int removeAllId;
  private int removeAllDurationId;

  private int getAllInProgressId;
  private int getAllSendInProgressId;
  private int getAllSendFailedId;
  private int getAllSendId;
  private int getAllSendDurationId;
  private int getAllTimedOutId;
  private int getAllFailedId;
  private int getAllId;
  private int getAllDurationId;

  private int connectionsId;
  private int connectsId;
  private int disconnectsId;
  private int messagesBeingReceivedId;
  private int messageBytesBeingReceivedId;
  private int receivedBytesId;
  private int sentBytesId;

  private int executeFunctionInProgressId;
  private int executeFunctionSendInProgressId;
  private int executeFunctionSendFailedId;
  private int executeFunctionSendId;
  private int executeFunctionSendDurationId;
  private int executeFunctionTimedOutId;
  private int executeFunctionFailedId;
  private int executeFunctionId;
  private int executeFunctionDurationId;

  private int getClientPRMetadataInProgressId;
  private int getClientPRMetadataSendInProgressId;
  private int getClientPRMetadataSendFailedId;
  private int getClientPRMetadataSendId;
  private int getClientPRMetadataSendDurationId;
  private int getClientPRMetadataTimedOutId;
  private int getClientPRMetadataFailedId;
  private int getClientPRMetadataId;
  private int getClientPRMetadataDurationId;

  private int getClientPartitionAttributesInProgressId;
  private int getClientPartitionAttributesSendInProgressId;
  private int getClientPartitionAttributesSendFailedId;
  private int getClientPartitionAttributesSendId;
  private int getClientPartitionAttributesSendDurationId;
  private int getClientPartitionAttributesTimedOutId;
  private int getClientPartitionAttributesFailedId;
  private int getClientPartitionAttributesId;
  private int getClientPartitionAttributesDurationId;

  private int getPDXIdForTypeInProgressId;
  private int getPDXIdForTypeSendInProgressId;
  private int getPDXIdForTypeSendFailedId;
  private int getPDXIdForTypeSendId;
  private int getPDXIdForTypeSendDurationId;
  private int getPDXIdForTypeTimedOutId;
  private int getPDXIdForTypeFailedId;
  private int getPDXIdForTypeId;
  private int getPDXIdForTypeDurationId;

  private int getPDXTypeByIdInProgressId;
  private int getPDXTypeByIdSendInProgressId;
  private int getPDXTypeByIdSendFailedId;
  private int getPDXTypeByIdSendId;
  private int getPDXTypeByIdSendDurationId;
  private int getPDXTypeByIdTimedOutId;
  private int getPDXTypeByIdFailedId;
  private int getPDXTypeByIdId;
  private int getPDXTypeByIdDurationId;

  private int addPdxTypeInProgressId;
  private int addPdxTypeSendInProgressId;
  private int addPdxTypeSendFailedId;
  private int addPdxTypeSendId;
  private int addPdxTypeSendDurationId;
  private int addPdxTypeTimedOutId;
  private int addPdxTypeFailedId;
  private int addPdxTypeId;
  private int addPdxTypeDurationId;


  // An array of all of the ids that represent operation statistics. This
  // is used by the getOps method to aggregate the individual stats
  // into a total value for all operations.
  private static int[] opIds;

  private void initializeStats(StatisticsFactory factory) {
    try {
      type = factory.createType("ClientStats", "Statistics about client to server communication",
          new StatisticDescriptor[] {
              factory.createIntGauge("getsInProgress", "Current number of gets being executed", "gets"),
              factory.createIntCounter("gets", "Total number of gets completed successfully", "gets"),
              factory.createIntCounter("getFailures", "Total number of get attempts that have failed",
                  "gets"),
              factory.createIntCounter("getTimeouts", "Total number of get attempts that have timed out",
                  "gets"),
              factory.createLongCounter("getTime",
                  "Total amount of time, in nanoseconds spent doing gets", "nanoseconds"),
              factory.createIntGauge("putsInProgress", "Current number of puts being executed", "puts"),
              factory.createIntCounter("puts", "Total number of puts completed successfully", "puts"),
              factory.createIntCounter("putFailures", "Total number of put attempts that have failed",
                  "puts"),
              factory.createIntCounter("putTimeouts", "Total number of put attempts that have timed out",
                  "puts"),
              factory.createLongCounter("putTime",
                  "Total amount of time, in nanoseconds spent doing puts", "nanoseconds"),
              factory.createIntGauge("destroysInProgress", "Current number of destroys being executed",
                  "destroys"),
              factory.createIntCounter("destroys", "Total number of destroys completed successfully",
                  "destroys"),
              factory.createIntCounter("destroyFailures",
                  "Total number of destroy attempts that have failed", "destroys"),
              factory.createIntCounter("destroyTimeouts",
                  "Total number of destroy attempts that have timed out", "destroys"),
              factory.createLongCounter("destroyTime",
                  "Total amount of time, in nanoseconds spent doing destroys", "nanoseconds"),
              factory.createIntGauge("destroyRegionsInProgress",
                  "Current number of destroyRegions being executed", "destroyRegions"),
              factory.createIntCounter("destroyRegions",
                  "Total number of destroyRegions completed successfully", "destroyRegions"),
              factory.createIntCounter("destroyRegionFailures",
                  "Total number of destroyRegion attempts that have failed", "destroyRegions"),
              factory.createIntCounter("destroyRegionTimeouts",
                  "Total number of destroyRegion attempts that have timed out", "destroyRegions"),
              factory.createLongCounter("destroyRegionTime",
                  "Total amount of time, in nanoseconds spent doing destroyRegions", "nanoseconds"),
              factory.createIntGauge("clearsInProgress", "Current number of clears being executed",
                  "clears"),
              factory.createIntCounter("clears", "Total number of clears completed successfully",
                  "clears"),
              factory.createIntCounter("clearFailures", "Total number of clear attempts that have failed",
                  "clears"),
              factory.createIntCounter("clearTimeouts",
                  "Total number of clear attempts that have timed out", "clears"),
              factory.createLongCounter("clearTime",
                  "Total amount of time, in nanoseconds spent doing clears", "nanoseconds"),
              factory.createIntGauge("containsKeysInProgress",
                  "Current number of containsKeys being executed", "containsKeys"),
              factory.createIntCounter("containsKeys",
                  "Total number of containsKeys completed successfully", "containsKeys"),
              factory.createIntCounter("containsKeyFailures",
                  "Total number of containsKey attempts that have failed", "containsKeys"),
              factory.createIntCounter("containsKeyTimeouts",
                  "Total number of containsKey attempts that have timed out", "containsKeys"),
              factory.createLongCounter("containsKeyTime",
                  "Total amount of time, in nanoseconds spent doing containsKeys", "nanoseconds"),
              factory.createIntGauge("keySetsInProgress", "Current number of keySets being executed",
                  "keySets"),
              factory.createIntCounter("keySets", "Total number of keySets completed successfully",
                  "keySets"),
              factory.createIntCounter("keySetFailures",
                  "Total number of keySet attempts that have failed", "keySets"),
              factory.createIntCounter("keySetTimeouts",
                  "Total number of keySet attempts that have timed out", "keySets"),
              factory.createLongCounter("keySetTime",
                  "Total amount of time, in nanoseconds spent doing keySets", "nanoseconds"),

              factory.createIntGauge("commitsInProgress", "Current number of commits being executed",
                  "commits"),
              factory.createIntCounter("commits", "Total number of commits completed successfully",
                  "commits"),
              factory.createIntCounter("commitFailures",
                  "Total number of commit attempts that have failed", "commits"),
              factory.createIntCounter("commitTimeouts",
                  "Total number of commit attempts that have timed out", "commits"),
              factory.createLongCounter("commitTime",
                  "Total amount of time, in nanoseconds spent doing commits", "nanoseconds"),

              factory.createIntGauge("rollbacksInProgress", "Current number of rollbacks being executed",
                  "rollbacks"),
              factory.createIntCounter("rollbacks", "Total number of rollbacks completed successfully",
                  "rollbacks"),
              factory.createIntCounter("rollbackFailures",
                  "Total number of rollback attempts that have failed", "rollbacks"),
              factory.createIntCounter("rollbackTimeouts",
                  "Total number of rollback attempts that have timed out", "rollbacks"),
              factory.createLongCounter("rollbackTime",
                  "Total amount of time, in nanoseconds spent doing rollbacks", "nanoseconds"),

              factory.createIntGauge("getEntrysInProgress",
                  "Current number of getEntry messages being executed", "messages"),
              factory.createIntCounter("getEntrys",
                  "Total number of getEntry messages completed successfully", "messages"),
              factory.createIntCounter("getEntryFailures",
                  "Total number of getEntry attempts that have failed", "attempts"),
              factory.createIntCounter("getEntryTimeouts",
                  "Total number of getEntry attempts that have timed out", "attempts"),
              factory.createLongCounter("getEntryTime",
                  "Total amount of time, in nanoseconds spent doing getEntry processings",
                  "nanoseconds"),

              factory.createIntGauge("jtaSynchronizationsInProgress",
                  "Current number of jtaSynchronizations being executed", "sizes"),
              factory.createIntCounter("jtaSynchronizations",
                  "Total number of jtaSynchronizations completed successfully",
                  "jtaSynchronizations"),
              factory.createIntCounter("jtaSynchronizationFailures",
                  "Total number of jtaSynchronization attempts that have failed",
                  "jtaSynchronizations"),
              factory.createIntCounter("jtaSynchronizationTimeouts",
                  "Total number of jtaSynchronization attempts that have timed out",
                  "jtaSynchronizations"),
              factory.createLongCounter("jtaSynchronizationTime",
                  "Total amount of time, in nanoseconds spent doing jtaSynchronizations",
                  "nanoseconds"),

              factory.createIntGauge("txFailoversInProgress",
                  "Current number of txFailovers being executed", "txFailovers"),
              factory.createIntCounter("txFailovers",
                  "Total number of txFailovers completed successfully", "txFailovers"),
              factory.createIntCounter("txFailoverFailures",
                  "Total number of txFailover attempts that have failed", "txFailovers"),
              factory.createIntCounter("txFailoverTimeouts",
                  "Total number of txFailover attempts that have timed out", "sizes"),
              factory.createLongCounter("txFailoverTime",
                  "Total amount of time, in nanoseconds spent doing txFailovers", "nanoseconds"),

              factory.createIntGauge("sizesInProgress", "Current number of sizes being executed",
                  "sizes"),
              factory.createIntCounter("sizes", "Total number of sizes completed successfully", "sizes"),
              factory.createIntCounter("sizeFailures", "Total number of size attempts that have failed",
                  "sizes"),
              factory.createIntCounter("sizeTimeouts",
                  "Total number of size attempts that have timed out", "sizes"),
              factory.createLongCounter("sizeTime",
                  "Total amount of time, in nanoseconds spent doing sizes", "nanoseconds"),

              factory.createIntGauge("invalidatesInProgress",
                  "Current number of invalidates being executed", "invalidates"),
              factory.createIntCounter("invalidates",
                  "Total number of invalidates completed successfully", "invalidates"),
              factory.createIntCounter("invalidateFailures",
                  "Total number of invalidate attempts that have failed", "invalidates"),
              factory.createIntCounter("invalidateTimeouts",
                  "Total number of invalidate attempts that have timed out", "invalidates"),
              factory.createLongCounter("invalidateTime",
                  "Total amount of time, in nanoseconds spent doing invalidates", "nanoseconds"),


              factory.createIntGauge("registerInterestsInProgress",
                  "Current number of registerInterests being executed", "registerInterests"),
              factory.createIntCounter("registerInterests",
                  "Total number of registerInterests completed successfully", "registerInterests"),
              factory.createIntCounter("registerInterestFailures",
                  "Total number of registerInterest attempts that have failed",
                  "registerInterests"),
              factory.createIntCounter("registerInterestTimeouts",
                  "Total number of registerInterest attempts that have timed out",
                  "registerInterests"),
              factory.createLongCounter("registerInterestTime",
                  "Total amount of time, in nanoseconds spent doing registerInterests",
                  "nanoseconds"),
              factory.createIntGauge("unregisterInterestsInProgress",
                  "Current number of unregisterInterests being executed", "unregisterInterests"),
              factory.createIntCounter("unregisterInterests",
                  "Total number of unregisterInterests completed successfully",
                  "unregisterInterests"),
              factory.createIntCounter("unregisterInterestFailures",
                  "Total number of unregisterInterest attempts that have failed",
                  "unregisterInterests"),
              factory.createIntCounter("unregisterInterestTimeouts",
                  "Total number of unregisterInterest attempts that have timed out",
                  "unregisterInterests"),
              factory.createLongCounter("unregisterInterestTime",
                  "Total amount of time, in nanoseconds spent doing unregisterInterests",
                  "nanoseconds"),
              factory.createIntGauge("querysInProgress", "Current number of querys being executed",
                  "querys"),
              factory.createIntCounter("querys", "Total number of querys completed successfully",
                  "querys"),
              factory.createIntCounter("queryFailures", "Total number of query attempts that have failed",
                  "querys"),
              factory.createIntCounter("queryTimeouts",
                  "Total number of query attempts that have timed out", "querys"),
              factory.createLongCounter("queryTime",
                  "Total amount of time, in nanoseconds spent doing querys", "nanoseconds"),
              factory.createIntGauge("createCQsInProgress", "Current number of createCQs being executed",
                  "createCQs"),
              factory.createIntCounter("createCQs", "Total number of createCQs completed successfully",
                  "createCQs"),
              factory.createIntCounter("createCQFailures",
                  "Total number of createCQ attempts that have failed", "createCQs"),
              factory.createIntCounter("createCQTimeouts",
                  "Total number of createCQ attempts that have timed out", "createCQs"),
              factory.createLongCounter("createCQTime",
                  "Total amount of time, in nanoseconds spent doing createCQs", "nanoseconds"),
              factory.createIntGauge("stopCQsInProgress", "Current number of stopCQs being executed",
                  "stopCQs"),
              factory.createIntCounter("stopCQs", "Total number of stopCQs completed successfully",
                  "stopCQs"),
              factory.createIntCounter("stopCQFailures",
                  "Total number of stopCQ attempts that have failed", "stopCQs"),
              factory.createIntCounter("stopCQTimeouts",
                  "Total number of stopCQ attempts that have timed out", "stopCQs"),
              factory.createLongCounter("stopCQTime",
                  "Total amount of time, in nanoseconds spent doing stopCQs", "nanoseconds"),
              factory.createIntGauge("closeCQsInProgress", "Current number of closeCQs being executed",
                  "closeCQs"),
              factory.createIntCounter("closeCQs", "Total number of closeCQs completed successfully",
                  "closeCQs"),
              factory.createIntCounter("closeCQFailures",
                  "Total number of closeCQ attempts that have failed", "closeCQs"),
              factory.createIntCounter("closeCQTimeouts",
                  "Total number of closeCQ attempts that have timed out", "closeCQs"),
              factory.createLongCounter("closeCQTime",
                  "Total amount of time, in nanoseconds spent doing closeCQs", "nanoseconds"),
              factory.createIntGauge("gatewayBatchsInProgress",
                  "Current number of gatewayBatchs being executed", "gatewayBatchs"),
              factory.createIntCounter("gatewayBatchs",
                  "Total number of gatewayBatchs completed successfully", "gatewayBatchs"),
              factory.createIntCounter("gatewayBatchFailures",
                  "Total number of gatewayBatch attempts that have failed", "gatewayBatchs"),
              factory.createIntCounter("gatewayBatchTimeouts",
                  "Total number of gatewayBatch attempts that have timed out", "gatewayBatchs"),
              factory.createLongCounter("gatewayBatchTime",
                  "Total amount of time, in nanoseconds spent doing gatewayBatchs", "nanoseconds"),
              factory.createIntGauge("getDurableCQsInProgress",
                  "Current number of getDurableCQs being executed", "getDurableCQs"),
              factory.createIntCounter("getDurableCQs",
                  "Total number of getDurableCQs completed successfully", "getDurableCQs"),
              factory.createIntCounter("getDurableCQsFailures",
                  "Total number of getDurableCQs attempts that have failed", "getDurableCQs"),
              factory.createIntCounter("getDurableCQsTimeouts",
                  "Total number of getDurableCQs attempts that have timed out", "getDurableCQs"),
              factory.createLongCounter("getDurableCQsTime",
                  "Total amount of time, in nanoseconds spent doing getDurableCQs", "nanoseconds"),
              factory.createIntGauge("readyForEventsInProgress",
                  "Current number of readyForEvents being executed", "readyForEvents"),
              factory.createIntCounter("readyForEvents",
                  "Total number of readyForEvents completed successfully", "readyForEvents"),
              factory.createIntCounter("readyForEventsFailures",
                  "Total number of readyForEvents attempts that have failed", "readyForEvents"),
              factory.createIntCounter("readyForEventsTimeouts",
                  "Total number of readyForEvents attempts that have timed out", "readyForEvents"),
              factory.createLongCounter("readyForEventsTime",
                  "Total amount of time, in nanoseconds spent doing readyForEvents", "nanoseconds"),
              factory.createIntGauge("makePrimarysInProgress",
                  "Current number of makePrimarys being executed", "makePrimarys"),
              factory.createIntCounter("makePrimarys",
                  "Total number of makePrimarys completed successfully", "makePrimarys"),
              factory.createIntCounter("makePrimaryFailures",
                  "Total number of makePrimary attempts that have failed", "makePrimarys"),
              factory.createIntCounter("makePrimaryTimeouts",
                  "Total number of makePrimary attempts that have timed out", "makePrimarys"),
              factory.createLongCounter("makePrimaryTime",
                  "Total amount of time, in nanoseconds spent doing makePrimarys", "nanoseconds"),

              factory.createIntGauge("closeConsInProgress", "Current number of closeCons being executed",
                  "closeCons"),
              factory.createIntCounter("closeCons", "Total number of closeCons completed successfully",
                  "closeCons"),
              factory.createIntCounter("closeConFailures",
                  "Total number of closeCon attempts that have failed", "closeCons"),
              factory.createIntCounter("closeConTimeouts",
                  "Total number of closeCon attempts that have timed out", "closeCons"),
              factory.createLongCounter("closeConTime",
                  "Total amount of time, in nanoseconds spent doing closeCons", "nanoseconds"),

              factory.createIntGauge("primaryAcksInProgress",
                  "Current number of primaryAcks being executed", "primaryAcks"),
              factory.createIntCounter("primaryAcks",
                  "Total number of primaryAcks completed successfully", "primaryAcks"),
              factory.createIntCounter("primaryAckFailures",
                  "Total number of primaryAck attempts that have failed", "primaryAcks"),
              factory.createIntCounter("primaryAckTimeouts",
                  "Total number of primaryAck attempts that have timed out", "primaryAcks"),
              factory.createLongCounter("primaryAckTime",
                  "Total amount of time, in nanoseconds spent doing primaryAcks", "nanoseconds"),

              factory.createIntGauge("pingsInProgress", "Current number of pings being executed",
                  "pings"),
              factory.createIntCounter("pings", "Total number of pings completed successfully", "pings"),
              factory.createIntCounter("pingFailures", "Total number of ping attempts that have failed",
                  "pings"),
              factory.createIntCounter("pingTimeouts",
                  "Total number of ping attempts that have timed out", "pings"),
              factory.createLongCounter("pingTime",
                  "Total amount of time, in nanoseconds spent doing pings", "nanoseconds"),

              factory.createIntGauge("registerInstantiatorsInProgress",
                  "Current number of registerInstantiators being executed",
                  "registerInstantiators"),
              factory.createIntCounter("registerInstantiators",
                  "Total number of registerInstantiators completed successfully",
                  "registerInstantiators"),
              factory.createIntCounter("registerInstantiatorsFailures",
                  "Total number of registerInstantiators attempts that have failed",
                  "registerInstantiators"),
              factory.createIntCounter("registerInstantiatorsTimeouts",
                  "Total number of registerInstantiators attempts that have timed out",
                  "registerInstantiators"),
              factory.createLongCounter("registerInstantiatorsTime",
                  "Total amount of time, in nanoseconds spent doing registerInstantiators",
                  "nanoseconds"),

              factory.createIntGauge("registerDataSerializersInProgress",
                  "Current number of registerDataSerializers being executed",
                  "registerDataSerializers"),
              factory.createIntCounter("registerDataSerializers",
                  "Total number of registerDataSerializers completed successfully",
                  "registerDataSerializers"),
              factory.createIntCounter("registerDataSerializersFailures",
                  "Total number of registerDataSerializers attempts that have failed",
                  "registerDataSerializers"),
              factory.createIntCounter("registerDataSerializersTimeouts",
                  "Total number of registerDataSerializers attempts that have timed out",
                  "registerDataSerializers"),
              factory.createLongCounter("registerDataSerializersTime",
                  "Total amount of time, in nanoseconds spent doing registerDataSerializers",
                  "nanoseconds"),

              factory.createIntGauge("connections", "Current number of connections", "connections"),
              factory.createIntCounter("connects", "Total number of times a connection has been created.",
                  "connects"),
              factory.createIntCounter("disconnects",
                  "Total number of times a connection has been destroyed.", "disconnects"),
              factory.createIntGauge("putAllsInProgress", "Current number of putAlls being executed",
                  "putAlls"),
              factory.createIntCounter("putAlls", "Total number of putAlls completed successfully",
                  "putAlls"),
              factory.createIntCounter("putAllFailures",
                  "Total number of putAll attempts that have failed", "putAlls"),
              factory.createIntCounter("putAllTimeouts",
                  "Total number of putAll attempts that have timed out", "putAlls"),
              factory.createLongCounter("putAllTime",
                  "Total amount of time, in nanoseconds spent doing putAlls", "nanoseconds"),
              factory.createIntGauge("removeAllsInProgress",
                  "Current number of removeAlls being executed", "removeAlls"),
              factory.createIntCounter("removeAlls", "Total number of removeAlls completed successfully",
                  "removeAlls"),
              factory.createIntCounter("removeAllFailures",
                  "Total number of removeAll attempts that have failed", "removeAlls"),
              factory.createIntCounter("removeAllTimeouts",
                  "Total number of removeAll attempts that have timed out", "removeAlls"),
              factory.createLongCounter("removeAllTime",
                  "Total amount of time, in nanoseconds spent doing removeAlls", "nanoseconds"),
              factory.createIntGauge("getAllsInProgress", "Current number of getAlls being executed",
                  "getAlls"),
              factory.createIntCounter("getAlls", "Total number of getAlls completed successfully",
                  "getAlls"),
              factory.createIntCounter("getAllFailures",
                  "Total number of getAll attempts that have failed", "getAlls"),
              factory.createIntCounter("getAllTimeouts",
                  "Total number of getAll attempts that have timed out", "getAlls"),
              factory.createLongCounter("getAllTime",
                  "Total amount of time, in nanoseconds spent doing getAlls", "nanoseconds"),
              factory.createLongCounter("receivedBytes",
                  "Total number of bytes received (as responses) from server over a client-to-server connection.",
                  "bytes"),
              factory.createLongCounter("sentBytes",
                  "Total number of bytes sent to server over a client-to-server connection.",
                  "bytes"),
              factory.createIntGauge("messagesBeingReceived",
                  "Current number of message being received off the network or being processed after reception over a client-to-server connection.",
                  "messages"),
              factory.createLongGauge("messageBytesBeingReceived",
                  "Current number of bytes consumed by messages being received or processed over a client-to-server connection.",
                  "bytes"),

              factory.createIntGauge("executeFunctionsInProgress",
                  "Current number of Functions being executed", "executeFunctions"),
              factory.createIntCounter("executeFunctions",
                  "Total number of Functions completed successfully", "executeFunctions"),
              factory.createIntCounter("executeFunctionFailures",
                  "Total number of Function attempts that have failed", "executeFunctions"),
              factory.createIntCounter("executeFunctionTimeouts",
                  "Total number of Function attempts that have timed out", "executeFunctions"),
              factory.createLongCounter("executeFunctionTime",
                  "Total amount of time, in nanoseconds spent doing Functions", "nanoseconds"),

              factory.createIntGauge("asyncExecuteFunctionsInProgress",
                  "Current number of Functions being executed asynchronously",
                  "asyncExecuteFunctions"),
              factory.createIntCounter("asyncExecuteFunctions",
                  "Total number of asynchronous Functions completed successfully",
                  "asyncExecuteFunctions"),
              factory.createIntCounter("asyncExecuteFunctionFailures",
                  "Total number of asynchronous Function attempts that have failed",
                  "asyncExecuteFunctions"),
              factory.createIntCounter("asyncExecuteFunctionTimeouts",
                  "Total number of asynchronous Function attempts that have timed out",
                  "asyncExecuteFunctions"),
              factory.createLongCounter("asyncExecuteFunctionTime",
                  "Total amount of time, in nanoseconds spent doing asynchronous Functions",
                  "nanoseconds"),

              factory.createIntGauge("getClientPRMetadataInProgress",
                  "Current number of getClientPRMetadata operations being executed",
                  "getClientPRMetadata"),
              factory.createIntCounter("getClientPRMetadataFailures",
                  "Total number of getClientPRMetadata operation attempts that have failed",
                  "getClientPRMetadata"),
              factory.createIntCounter("getClientPRMetadataSuccessful",
                  "Total number of getClientPRMetadata operations completed successfully",
                  "getClientPRMetadata"),
              factory.createIntCounter("getClientPRMetadataTimeouts",
                  "Total number of getClientPRMetadata operation attempts that have timed out",
                  "getClientPRMetadata"),
              factory.createLongCounter("getClientPRMetadataTime",
                  "Total amount of time, in nanoseconds spent doing getClientPRMetadata successfully/unsuccessfully",
                  "nanoseconds"),

              factory.createIntGauge("getClientPartitionAttributesInProgress",
                  "Current number of getClientPartitionAttributes operations being executed",
                  "getClientPartitionAttributes"),
              factory.createIntCounter("getClientPartitionAttributesFailures",
                  "Total number of getClientPartitionAttributes operation attempts that have failed",
                  "getClientPartitionAttributes"),
              factory.createIntCounter("getClientPartitionAttributesSuccessful",
                  "Total number of getClientPartitionAttributes operations completed successfully",
                  "getClientPartitionAttributes"),
              factory.createIntCounter("getClientPartitionAttributesTimeouts",
                  "Total number of getClientPartitionAttributes operation attempts that have timed out",
                  "getClientPartitionAttributes"),
              factory.createLongCounter("getClientPartitionAttributesTime",
                  "Total amount of time, in nanoseconds spent doing getClientPartitionAttributes successfully/unsuccessfully.",
                  "nanoseconds"),

              factory.createIntGauge("getPDXTypeByIdInProgress",
                  "Current number of getPDXTypeById operations being executed", "getPDXTypeById"),
              factory.createIntCounter("getPDXTypeByIdFailures",
                  "Total number of getPDXTypeById operation attempts that have failed",
                  "getPDXTypeById"),
              factory.createIntCounter("getPDXTypeByIdSuccessful",
                  "Total number of getPDXTypeById operations completed successfully",
                  "getPDXTypeById"),
              factory.createIntCounter("getPDXTypeByIdTimeouts",
                  "Total number of getPDXTypeById operation attempts that have timed out",
                  "getPDXTypeById"),
              factory.createLongCounter("getPDXTypeByIdTime",
                  "Total amount of time, in nanoseconds spent doing getPDXTypeById successfully/unsuccessfully.",
                  "nanoseconds"),

              factory.createIntGauge("getPDXIdForTypeInProgress",
                  "Current number of getPDXIdForType operations being executed", "getPDXIdForType"),
              factory.createIntCounter("getPDXIdForTypeFailures",
                  "Total number of getPDXIdForType operation attempts that have failed",
                  "getPDXIdForType"),
              factory.createIntCounter("getPDXIdForTypeSuccessful",
                  "Total number of getPDXIdForType operations completed successfully",
                  "getPDXIdForType"),
              factory.createIntCounter("getPDXIdForTypeTimeouts",
                  "Total number of getPDXIdForType operation attempts that have timed out",
                  "getPDXIdForType"),
              factory.createLongCounter("getPDXIdForTypeTime",
                  "Total amount of time, in nanoseconds spent doing getPDXIdForType successfully/unsuccessfully.",
                  "nanoseconds"),

              factory.createIntGauge("addPdxTypeInProgress",
                  "Current number of addPdxType operations being executed", "addPdxType"),
              factory.createIntCounter("addPdxTypeFailures",
                  "Total number of addPdxType operation attempts that have failed", "addPdxType"),
              factory.createIntCounter("addPdxTypeSuccessful",
                  "Total number of addPdxType operations completed successfully", "addPdxType"),
              factory.createIntCounter("addPdxTypeTimeouts",
                  "Total number of addPdxType operation attempts that have timed out",
                  "addPdxType"),
              factory.createLongCounter("addPdxTypeTime",
                  "Total amount of time, in nanoseconds spent doing addPdxType successfully/unsuccessfully.",
                  "nanoseconds"),});

      sendType = factory.createType("ClientSendStats", "Statistics about client to server communication",
          new StatisticDescriptor[] {
              ///////////////////////////////////////////////////////////////////////
              /*
               * factory.createIntGauge("opSendsInProgress", "Current number of op sends being executed",
               * "sends"), factory.createIntCounter("opSends",
               * "Total number of op sends that have completed successfully", "sends"),
               * factory.createIntCounter("opSendFailures", "Total number of op sends that have failed",
               * "sends"), factory.createLongCounter("opSendTime",
               * "Total amount of time, in nanoseconds spent doing op sends", "nanoseconds"),
               */
              ///////////////////////////////////////////////////////////////////////
              factory.createIntGauge("getSendsInProgress", "Current number of get sends being executed",
                  "sends"),
              factory.createIntCounter("getSends",
                  "Total number of get sends that have completed successfully", "sends"),
              factory.createIntCounter("getSendFailures", "Total number of get sends that have failed",
                  "sends"),
              factory.createLongCounter("getSendTime",
                  "Total amount of time, in nanoseconds spent doing get sends", "nanoseconds"),
              factory.createIntGauge("putSendsInProgress", "Current number of put sends being executed",
                  "sends"),
              factory.createIntCounter("putSends",
                  "Total number of put sends that have completed successfully", "sends"),
              factory.createIntCounter("putSendFailures", "Total number of put sends that have failed",
                  "sends"),
              factory.createLongCounter("putSendTime",
                  "Total amount of time, in nanoseconds spent doing put sends", "nanoseconds"),
              factory.createIntGauge("destroySendsInProgress",
                  "Current number of destroy sends being executed", "sends"),
              factory.createIntCounter("destroySends",
                  "Total number of destroy sends that have completed successfully", "sends"),
              factory.createIntCounter("destroySendFailures",
                  "Total number of destroy sends that have failed", "sends"),
              factory.createLongCounter("destroySendTime",
                  "Total amount of time, in nanoseconds spent doing destroy sends", "nanoseconds"),
              factory.createIntGauge("destroyRegionSendsInProgress",
                  "Current number of destroyRegion sends being executed", "sends"),
              factory.createIntCounter("destroyRegionSends",
                  "Total number of destroyRegion sends that have completed successfully", "sends"),
              factory.createIntCounter("destroyRegionSendFailures",
                  "Total number of destroyRegion sends that have failed", "sends"),
              factory.createLongCounter("destroyRegionSendTime",
                  "Total amount of time, in nanoseconds spent doing destroyRegion sends",
                  "nanoseconds"),
              factory.createIntGauge("clearSendsInProgress",
                  "Current number of clear sends being executed", "sends"),
              factory.createIntCounter("clearSends",
                  "Total number of clear sends that have completed successfully", "sends"),
              factory.createIntCounter("clearSendFailures",
                  "Total number of clear sends that have failed", "sends"),
              factory.createLongCounter("clearSendTime",
                  "Total amount of time, in nanoseconds spent doing clear sends", "nanoseconds"),
              factory.createIntGauge("containsKeySendsInProgress",
                  "Current number of containsKey sends being executed", "sends"),
              factory.createIntCounter("containsKeySends",
                  "Total number of containsKey sends that have completed successfully", "sends"),
              factory.createIntCounter("containsKeySendFailures",
                  "Total number of containsKey sends that have failed", "sends"),
              factory.createLongCounter("containsKeySendTime",
                  "Total amount of time, in nanoseconds spent doing containsKey sends",
                  "nanoseconds"),
              factory.createIntGauge("keySetSendsInProgress",
                  "Current number of keySet sends being executed", "sends"),
              factory.createIntCounter("keySetSends",
                  "Total number of keySet sends that have completed successfully", "sends"),
              factory.createIntCounter("keySetSendFailures",
                  "Total number of keySet sends that have failed", "sends"),
              factory.createLongCounter("keySetSendTime",
                  "Total amount of time, in nanoseconds spent doing keySet sends", "nanoseconds"),

              factory.createIntGauge("commitSendsInProgress",
                  "Current number of commit sends being executed", "sends"),
              factory.createIntCounter("commitSendFailures",
                  "Total number of commit sends that have failed", "sends"),
              factory.createIntCounter("commitSends", "Total number of commit sends that have failed",
                  "sends"),
              factory.createLongCounter("commitSendTime",
                  "Total amount of time, in nanoseconds spent doing commits", "nanoseconds"),
              factory.createIntGauge("rollbackSendsInProgress",
                  "Current number of rollback sends being executed", "sends"),
              factory.createIntCounter("rollbackSendFailures",
                  "Total number of rollback sends that have failed", "sends"),
              factory.createIntCounter("rollbackSends", "Total number of rollback sends that have failed",
                  "sends"),
              factory.createLongCounter("rollbackSendTime",
                  "Total amount of time, in nanoseconds spent doing rollbacks", "nanoseconds"),
              factory.createIntGauge("getEntrySendsInProgress",
                  "Current number of getEntry sends being executed", "sends"),
              factory.createIntCounter("getEntrySendFailures",
                  "Total number of getEntry sends that have failed", "sends"),
              factory.createIntCounter("getEntrySends", "Total number of getEntry sends that have failed",
                  "sends"),
              factory.createLongCounter("getEntrySendTime",
                  "Total amount of time, in nanoseconds spent sending getEntry messages",
                  "nanoseconds"),
              factory.createIntGauge("jtaSynchronizationSendsInProgress",
                  "Current number of jtaSynchronization sends being executed", "sends"),
              factory.createIntCounter("jtaSynchronizationSendFailures",
                  "Total number of jtaSynchronization sends that have failed", "sends"),
              factory.createIntCounter("jtaSynchronizationSends",
                  "Total number of jtaSynchronization sends that have failed", "sends"),
              factory.createLongCounter("jtaSynchronizationSendTime",
                  "Total amount of time, in nanoseconds spent doing jtaSynchronizations",
                  "nanoseconds"),
              factory.createIntGauge("txFailoverSendsInProgress",
                  "Current number of txFailover sends being executed", "sends"),
              factory.createIntCounter("txFailoverSendFailures",
                  "Total number of txFailover sends that have failed", "sends"),
              factory.createIntCounter("txFailoverSends",
                  "Total number of txFailover sends that have failed", "sends"),
              factory.createLongCounter("txFailoverSendTime",
                  "Total amount of time, in nanoseconds spent doing txFailovers", "nanoseconds"),
              factory.createIntGauge("sizeSendsInProgress", "Current number of size sends being executed",
                  "sends"),
              factory.createIntCounter("sizeSendFailures", "Total number of size sends that have failed",
                  "sends"),
              factory.createIntCounter("sizeSends", "Total number of size sends that have failed",
                  "sends"),
              factory.createLongCounter("sizeSendTime",
                  "Total amount of time, in nanoseconds spent doing sizes", "nanoseconds"),
              factory.createIntGauge("invalidateSendsInProgress",
                  "Current number of invalidate sends being executed", "sends"),
              factory.createIntCounter("invalidateSendFailures",
                  "Total number of invalidate sends that have failed", "sends"),
              factory.createIntCounter("invalidateSends",
                  "Total number of invalidate sends that have failed", "sends"),
              factory.createLongCounter("invalidateSendTime",
                  "Total amount of time, in nanoseconds spent doing invalidates", "nanoseconds"),
              factory.createIntGauge("registerInterestSendsInProgress",
                  "Current number of registerInterest sends being executed", "sends"),
              factory.createIntCounter("registerInterestSends",
                  "Total number of registerInterest sends that have completed successfully",
                  "sends"),
              factory.createIntCounter("registerInterestSendFailures",
                  "Total number of registerInterest sends that have failed", "sends"),
              factory.createLongCounter("registerInterestSendTime",
                  "Total amount of time, in nanoseconds spent doing registerInterest sends",
                  "nanoseconds"),
              factory.createIntGauge("unregisterInterestSendsInProgress",
                  "Current number of unregisterInterest sends being executed", "sends"),
              factory.createIntCounter("unregisterInterestSends",
                  "Total number of unregisterInterest sends that have completed successfully",
                  "sends"),
              factory.createIntCounter("unregisterInterestSendFailures",
                  "Total number of unregisterInterest sends that have failed", "sends"),
              factory.createLongCounter("unregisterInterestSendTime",
                  "Total amount of time, in nanoseconds spent doing unregisterInterest sends",
                  "nanoseconds"),
              factory.createIntGauge("querySendsInProgress",
                  "Current number of query sends being executed", "sends"),
              factory.createIntCounter("querySends",
                  "Total number of query sends that have completed successfully", "sends"),
              factory.createIntCounter("querySendFailures",
                  "Total number of query sends that have failed", "sends"),
              factory.createLongCounter("querySendTime",
                  "Total amount of time, in nanoseconds spent doing query sends", "nanoseconds"),
              factory.createIntGauge("createCQSendsInProgress",
                  "Current number of createCQ sends being executed", "sends"),
              factory.createIntCounter("createCQSends",
                  "Total number of createCQ sends that have completed successfully", "sends"),
              factory.createIntCounter("createCQSendFailures",
                  "Total number of createCQ sends that have failed", "sends"),
              factory.createLongCounter("createCQSendTime",
                  "Total amount of time, in nanoseconds spent doing createCQ sends", "nanoseconds"),
              factory.createIntGauge("stopCQSendsInProgress",
                  "Current number of stopCQ sends being executed", "sends"),
              factory.createIntCounter("stopCQSends",
                  "Total number of stopCQ sends that have completed successfully", "sends"),
              factory.createIntCounter("stopCQSendFailures",
                  "Total number of stopCQ sends that have failed", "sends"),
              factory.createLongCounter("stopCQSendTime",
                  "Total amount of time, in nanoseconds spent doing stopCQ sends", "nanoseconds"),
              factory.createIntGauge("closeCQSendsInProgress",
                  "Current number of closeCQ sends being executed", "sends"),
              factory.createIntCounter("closeCQSends",
                  "Total number of closeCQ sends that have completed successfully", "sends"),
              factory.createIntCounter("closeCQSendFailures",
                  "Total number of closeCQ sends that have failed", "sends"),
              factory.createLongCounter("closeCQSendTime",
                  "Total amount of time, in nanoseconds spent doing closeCQ sends", "nanoseconds"),
              factory.createIntGauge("gatewayBatchSendsInProgress",
                  "Current number of gatewayBatch sends being executed", "sends"),
              factory.createIntCounter("gatewayBatchSends",
                  "Total number of gatewayBatch sends that have completed successfully", "sends"),
              factory.createIntCounter("gatewayBatchSendFailures",
                  "Total number of gatewayBatch sends that have failed", "sends"),
              factory.createLongCounter("gatewayBatchSendTime",
                  "Total amount of time, in nanoseconds spent doing gatewayBatch sends",
                  "nanoseconds"),
              factory.createIntGauge("getDurableCQsSendsInProgressId",
                  "Current number of getDurableCQs sends being executed", "sends"),
              factory.createIntCounter("getDurableCQsSends",
                  "Total number of getDurableCQs sends that have completed successfully", "sends"),
              factory.createIntCounter("getDurableCQsSendFailures",
                  "Total number of getDurableCQs sends that have failed", "sends"),
              factory.createLongCounter("getDurableCQsSendTime",
                  "Total amount of time, in nanoseconds spent doing getDurableCQs sends",
                  "nanoseconds"),
              factory.createIntGauge("readyForEventsSendsInProgress",
                  "Current number of readyForEvents sends being executed", "sends"),
              factory.createIntCounter("readyForEventsSends",
                  "Total number of readyForEvents sends that have completed successfully", "sends"),
              factory.createIntCounter("readyForEventsSendFailures",
                  "Total number of readyForEvents sends that have failed", "sends"),
              factory.createLongCounter("readyForEventsSendTime",
                  "Total amount of time, in nanoseconds spent doing readyForEvents sends",
                  "nanoseconds"),
              factory.createIntGauge("makePrimarySendsInProgress",
                  "Current number of makePrimary sends being executed", "sends"),
              factory.createIntCounter("makePrimarySends",
                  "Total number of makePrimary sends that have completed successfully", "sends"),
              factory.createIntCounter("makePrimarySendFailures",
                  "Total number of makePrimary sends that have failed", "sends"),
              factory.createLongCounter("makePrimarySendTime",
                  "Total amount of time, in nanoseconds spent doing makePrimary sends",
                  "nanoseconds"),
              factory.createIntGauge("closeConSendsInProgress",
                  "Current number of closeCon sends being executed", "sends"),
              factory.createIntCounter("closeConSends",
                  "Total number of closeCon sends that have completed successfully", "sends"),
              factory.createIntCounter("closeConSendFailures",
                  "Total number of closeCon sends that have failed", "sends"),
              factory.createLongCounter("closeConSendTime",
                  "Total amount of time, in nanoseconds spent doing closeCon sends", "nanoseconds"),
              factory.createIntGauge("primaryAckSendsInProgress",
                  "Current number of primaryAck sends being executed", "sends"),
              factory.createIntCounter("primaryAckSends",
                  "Total number of primaryAck sends that have completed successfully", "sends"),
              factory.createIntCounter("primaryAckSendFailures",
                  "Total number of primaryAck sends that have failed", "sends"),
              factory.createLongCounter("primaryAckSendTime",
                  "Total amount of time, in nanoseconds spent doing primaryAck sends",
                  "nanoseconds"),
              factory.createIntGauge("pingSendsInProgress", "Current number of ping sends being executed",
                  "sends"),
              factory.createIntCounter("pingSends",
                  "Total number of ping sends that have completed successfully", "sends"),
              factory.createIntCounter("pingSendFailures", "Total number of ping sends that have failed",
                  "sends"),
              factory.createLongCounter("pingSendTime",
                  "Total amount of time, in nanoseconds spent doing ping sends", "nanoseconds"),
              factory.createIntGauge("registerInstantiatorsSendsInProgress",
                  "Current number of registerInstantiators sends being executed", "sends"),
              factory.createIntCounter("registerInstantiatorsSends",
                  "Total number of registerInstantiators sends that have completed successfully",
                  "sends"),
              factory.createIntCounter("registerInstantiatorsSendFailures",
                  "Total number of registerInstantiators sends that have failed", "sends"),
              factory.createLongCounter("registerInstantiatorsSendTime",
                  "Total amount of time, in nanoseconds spent doing registerInstantiators sends",
                  "nanoseconds"),
              factory.createIntGauge("registerDataSerializersSendInProgress",
                  "Current number of registerDataSerializers sends being executed", "sends"),
              factory.createIntCounter("registerDataSerializersSends",
                  "Total number of registerDataSerializers sends that have completed successfully",
                  "sends"),
              factory.createIntCounter("registerDataSerializersSendFailures",
                  "Total number of registerDataSerializers sends that have failed", "sends"),
              factory.createLongCounter("registerDataSerializersSendTime",
                  "Total amount of time, in nanoseconds spent doing registerDataSerializers sends",
                  "nanoseconds"),
              factory.createIntGauge("putAllSendsInProgress",
                  "Current number of putAll sends being executed", "sends"),
              factory.createIntCounter("putAllSends",
                  "Total number of putAll sends that have completed successfully", "sends"),
              factory.createIntCounter("putAllSendFailures",
                  "Total number of putAll sends that have failed", "sends"),
              factory.createLongCounter("putAllSendTime",
                  "Total amount of time, in nanoseconds spent doing putAll sends", "nanoseconds"),
              factory.createIntGauge("removeAllSendsInProgress",
                  "Current number of removeAll sends being executed", "sends"),
              factory.createIntCounter("removeAllSends",
                  "Total number of removeAll sends that have completed successfully", "sends"),
              factory.createIntCounter("removeAllSendFailures",
                  "Total number of removeAll sends that have failed", "sends"),
              factory.createLongCounter("removeAllSendTime",
                  "Total amount of time, in nanoseconds spent doing removeAll sends",
                  "nanoseconds"),
              factory.createIntGauge("getAllSendsInProgress",
                  "Current number of getAll sends being executed", "sends"),
              factory.createIntCounter("getAllSends",
                  "Total number of getAll sends that have completed successfully", "sends"),
              factory.createIntCounter("getAllSendFailures",
                  "Total number of getAll sends that have failed", "sends"),
              factory.createLongCounter("getAllSendTime",
                  "Total amount of time, in nanoseconds spent doing getAll sends", "nanoseconds"),
              factory.createIntGauge("executeFunctionSendsInProgress",
                  "Current number of Function sends being executed", "sends"),
              factory.createIntCounter("executeFunctionSends",
                  "Total number of Function sends that have completed successfully", "sends"),
              factory.createIntCounter("executeFunctionSendFailures",
                  "Total number of Function sends that have failed", "sends"),
              factory.createLongCounter("executeFunctionSendTime",
                  "Total amount of time, in nanoseconds spent doing Function sends", "nanoseconds"),
              factory.createIntGauge("asyncExecuteFunctionSendsInProgress",
                  "Current number of Function sends being executed asynchronously", "sends"),
              factory.createIntCounter("asyncExecuteFunctionSends",
                  "Total number of asynchronous Function sends that have completed successfully",
                  "sends"),
              factory.createIntCounter("asyncExecuteFunctionSendFailures",
                  "Total number of asynchronous Function sends that have failed", "sends"),
              factory.createLongCounter("asyncExecuteFunctionSendTime",
                  "Total amount of time, in nanoseconds spent doing asynchronous Function sends",
                  "nanoseconds"),
              factory.createIntGauge("getClientPRMetadataSendsInProgress",
                  "Current number of getClientPRMetadata operation's request messages being send from the client to server",
                  "sends"),
              factory.createIntCounter("getClientPRMetadataSendFailures",
                  "Total number of getClientPRMetadata operation's request messages not sent successfully from the client to server",
                  "sends"),
              factory.createIntCounter("getClientPRMetadataSendsSuccessful",
                  "Total number of getClientPRMetadata operation's request messages sent successfully from the client to server",
                  "sends"),
              factory.createLongCounter("getClientPRMetadataSendTime",
                  "Total amount of time, in nanoseconds spent sending getClientPRMetadata operation's request messages successfully/unsuccessfully from the client to server",
                  "nanoseconds"),
              factory.createIntGauge("getClientPartitionAttributesSendsInProgress",
                  "Current number of getClientPartitionAttributes operation's request messages being send from the client to server",
                  "sends"),
              factory.createIntCounter("getClientPartitionAttributesSendFailures",
                  "Total number of getClientPartitionAttributes operation's request messages not sent successfully from the client to server",
                  "sends"),
              factory.createIntCounter("getClientPartitionAttributesSendsSuccessful",
                  "Total number of getClientPartitionAttributes operation's request messages sent successfully from the client to server",
                  "sends"),
              factory.createLongCounter("getClientPartitionAttributesSendTime",
                  "Total amount of time, in nanoseconds spent sending getClientPartitionAttributes operation's request messages successfully/unsuccessfully from the client to server",
                  "nanoseconds"),
              factory.createIntGauge("getPDXTypeByIdSendsInProgress",
                  "Current number of getPDXTypeById operation's request messages being send from the client to server",
                  "sends"),
              factory.createIntCounter("getPDXTypeByIdSendFailures",
                  "Total number of getPDXTypeById operation's request messages not sent successfully from the client to server",
                  "sends"),
              factory.createIntCounter("getPDXTypeByIdSendsSuccessful",
                  "Total number of getPDXTypeById operation's request messages sent successfully from the client to server",
                  "sends"),
              factory.createLongCounter("getPDXTypeByIdSendTime",
                  "Total amount of time, in nanoseconds spent sending getPDXTypeById operation's request messages successfully/unsuccessfully from the client to server",
                  "nanoseconds"),
              factory.createIntGauge("getPDXIdForTypeSendsInProgress",
                  "Current number of getPDXIdForType operation's request messages being send from the client to server",
                  "sends"),
              factory.createIntCounter("getPDXIdForTypeSendFailures",
                  "Total number of getPDXIdForType operation's request messages not sent successfully from the client to server",
                  "sends"),
              factory.createIntCounter("getPDXIdForTypeSendsSuccessful",
                  "Total number of getPDXIdForType operation's request messages sent successfully from the client to server",
                  "sends"),
              factory.createLongCounter("getPDXIdForTypeSendTime",
                  "Total amount of time, in nanoseconds spent sending getPDXIdForType operation's request messages successfully/unsuccessfully from the client to server",
                  "nanoseconds"),
              factory.createIntGauge("addPdxTypeSendsInProgress",
                  "Current number of addPdxType operation's request messages being send from the client to server",
                  "sends"),
              factory.createIntCounter("addPdxTypeSendFailures",
                  "Total number of addPdxType operation's request messages not sent successfully from the client to server",
                  "sends"),
              factory.createIntCounter("addPdxTypeSendsSuccessful",
                  "Total number of addPdxType operation's request messages sent successfully from the client to server",
                  "sends"),
              factory.createLongCounter("addPdxTypeSendTime",
                  "Total amount of time, in nanoseconds spent sending addPdxType operation's request messages successfully/unsuccessfully from the client to server",
                  "nanoseconds"),});
      ///////////////////////////////////////////////////////////////////////
      /*
       * opInProgressId = type.nameToId("opsInProgress"); opSendInProgressId =
       * sendType.nameToId("opSendsInProgress"); opSendFailedId =
       * sendType.nameToId("opSendFailures"); opSendId = sendType.nameToId("opSends");
       * opSendDurationId = sendType.nameToId("opSendTime"); opTimedOutId =
       * type.nameToId("opTimeouts"); opFailedId = type.nameToId("opFailures"); opId =
       * type.nameToId("ops"); opDurationId = type.nameToId("opTime");
       */
      ///////////////////////////////////////////////////////////////////////
      getInProgressId = type.nameToId("getsInProgress");
      getSendInProgressId = sendType.nameToId("getSendsInProgress");
      getSendFailedId = sendType.nameToId("getSendFailures");
      getSendId = sendType.nameToId("getSends");
      getSendDurationId = sendType.nameToId("getSendTime");
      getTimedOutId = type.nameToId("getTimeouts");
      getFailedId = type.nameToId("getFailures");
      getId = type.nameToId("gets");
      getDurationId = type.nameToId("getTime");
      putInProgressId = type.nameToId("putsInProgress");
      putSendInProgressId = sendType.nameToId("putSendsInProgress");
      putSendFailedId = sendType.nameToId("putSendFailures");
      putSendId = sendType.nameToId("putSends");
      putSendDurationId = sendType.nameToId("putSendTime");
      putTimedOutId = type.nameToId("putTimeouts");
      putFailedId = type.nameToId("putFailures");
      putId = type.nameToId("puts");
      putDurationId = type.nameToId("putTime");
      destroyInProgressId = type.nameToId("destroysInProgress");
      destroySendInProgressId = sendType.nameToId("destroySendsInProgress");
      destroySendFailedId = sendType.nameToId("destroySendFailures");
      destroySendId = sendType.nameToId("destroySends");
      destroySendDurationId = sendType.nameToId("destroySendTime");
      destroyTimedOutId = type.nameToId("destroyTimeouts");
      destroyFailedId = type.nameToId("destroyFailures");
      destroyId = type.nameToId("destroys");
      destroyDurationId = type.nameToId("destroyTime");
      destroyRegionInProgressId = type.nameToId("destroyRegionsInProgress");
      destroyRegionSendInProgressId = sendType.nameToId("destroyRegionSendsInProgress");
      destroyRegionSendFailedId = sendType.nameToId("destroyRegionSendFailures");
      destroyRegionSendId = sendType.nameToId("destroyRegionSends");
      destroyRegionSendDurationId = sendType.nameToId("destroyRegionSendTime");
      destroyRegionTimedOutId = type.nameToId("destroyRegionTimeouts");
      destroyRegionFailedId = type.nameToId("destroyRegionFailures");
      destroyRegionId = type.nameToId("destroyRegions");
      destroyRegionDurationId = type.nameToId("destroyRegionTime");
      clearInProgressId = type.nameToId("clearsInProgress");
      clearSendInProgressId = sendType.nameToId("clearSendsInProgress");
      clearSendFailedId = sendType.nameToId("clearSendFailures");
      clearSendId = sendType.nameToId("clearSends");
      clearSendDurationId = sendType.nameToId("clearSendTime");
      clearTimedOutId = type.nameToId("clearTimeouts");
      clearFailedId = type.nameToId("clearFailures");
      clearId = type.nameToId("clears");
      clearDurationId = type.nameToId("clearTime");
      containsKeyInProgressId = type.nameToId("containsKeysInProgress");
      containsKeySendInProgressId = sendType.nameToId("containsKeySendsInProgress");
      containsKeySendFailedId = sendType.nameToId("containsKeySendFailures");
      containsKeySendId = sendType.nameToId("containsKeySends");
      containsKeySendDurationId = sendType.nameToId("containsKeySendTime");
      containsKeyTimedOutId = type.nameToId("containsKeyTimeouts");
      containsKeyFailedId = type.nameToId("containsKeyFailures");
      containsKeyId = type.nameToId("containsKeys");
      containsKeyDurationId = type.nameToId("containsKeyTime");

      keySetInProgressId = type.nameToId("keySetsInProgress");
      keySetSendInProgressId = sendType.nameToId("keySetSendsInProgress");
      keySetSendFailedId = sendType.nameToId("keySetSendFailures");
      keySetSendId = sendType.nameToId("keySetSends");
      keySetSendDurationId = sendType.nameToId("keySetSendTime");
      keySetTimedOutId = type.nameToId("keySetTimeouts");
      keySetFailedId = type.nameToId("keySetFailures");
      keySetId = type.nameToId("keySets");
      keySetDurationId = type.nameToId("keySetTime");

      commitInProgressId = type.nameToId("commitsInProgress");
      commitSendInProgressId = sendType.nameToId("commitSendsInProgress");
      commitSendFailedId = sendType.nameToId("commitSendFailures");
      commitSendId = sendType.nameToId("commitSends");
      commitSendDurationId = sendType.nameToId("commitSendTime");
      commitDurationId = type.nameToId("commitTime");
      commitTimedOutId = type.nameToId("commitTimeouts");
      commitFailedId = type.nameToId("commitFailures");
      commitId = type.nameToId("commits");

      rollbackInProgressId = type.nameToId("rollbacksInProgress");
      rollbackSendInProgressId = sendType.nameToId("rollbackSendsInProgress");
      rollbackSendFailedId = sendType.nameToId("rollbackSendFailures");
      rollbackSendId = sendType.nameToId("rollbackSends");
      rollbackSendDurationId = sendType.nameToId("rollbackSendTime");
      rollbackDurationId = type.nameToId("rollbackTime");
      rollbackTimedOutId = type.nameToId("rollbackTimeouts");
      rollbackFailedId = type.nameToId("rollbackFailures");
      rollbackId = type.nameToId("rollbacks");

      getEntryInProgressId = type.nameToId("getEntrysInProgress");
      getEntrySendInProgressId = sendType.nameToId("getEntrySendsInProgress");
      getEntrySendFailedId = sendType.nameToId("getEntrySendFailures");
      getEntrySendId = sendType.nameToId("getEntrySends");
      getEntrySendDurationId = sendType.nameToId("getEntrySendTime");
      getEntryDurationId = type.nameToId("getEntryTime");
      getEntryTimedOutId = type.nameToId("getEntryTimeouts");
      getEntryFailedId = type.nameToId("getEntryFailures");
      getEntryId = type.nameToId("getEntrys");

      txSynchronizationInProgressId = type.nameToId("jtaSynchronizationsInProgress");
      txSynchronizationSendInProgressId = sendType.nameToId("jtaSynchronizationSendsInProgress");
      txSynchronizationSendFailedId = sendType.nameToId("jtaSynchronizationSendFailures");
      txSynchronizationSendId = sendType.nameToId("jtaSynchronizationSends");
      txSynchronizationSendDurationId = sendType.nameToId("jtaSynchronizationSendTime");
      txSynchronizationDurationId = type.nameToId("jtaSynchronizationTime");
      txSynchronizationTimedOutId = type.nameToId("jtaSynchronizationTimeouts");
      txSynchronizationFailedId = type.nameToId("jtaSynchronizationFailures");
      txSynchronizationId = type.nameToId("jtaSynchronizations");

      txFailoverInProgressId = type.nameToId("txFailoversInProgress");
      txFailoverSendInProgressId = sendType.nameToId("txFailoverSendsInProgress");
      txFailoverSendFailedId = sendType.nameToId("txFailoverSendFailures");
      txFailoverSendId = sendType.nameToId("txFailoverSends");
      txFailoverSendDurationId = sendType.nameToId("txFailoverSendTime");
      txFailoverDurationId = type.nameToId("txFailoverTime");
      txFailoverTimedOutId = type.nameToId("txFailoverTimeouts");
      txFailoverFailedId = type.nameToId("txFailoverFailures");
      txFailoverId = type.nameToId("txFailovers");

      sizeInProgressId = type.nameToId("sizesInProgress");
      sizeSendInProgressId = sendType.nameToId("sizeSendsInProgress");
      sizeSendFailedId = sendType.nameToId("sizeSendFailures");
      sizeSendId = sendType.nameToId("sizeSends");
      sizeSendDurationId = sendType.nameToId("sizeSendTime");
      sizeDurationId = type.nameToId("sizeTime");
      sizeTimedOutId = type.nameToId("sizeTimeouts");
      sizeFailedId = type.nameToId("sizeFailures");
      sizeId = type.nameToId("sizes");


      invalidateInProgressId = type.nameToId("invalidatesInProgress");
      invalidateSendInProgressId = sendType.nameToId("invalidateSendsInProgress");
      invalidateSendFailedId = sendType.nameToId("invalidateSendFailures");
      invalidateSendId = sendType.nameToId("invalidateSends");
      invalidateSendDurationId = sendType.nameToId("invalidateSendTime");
      invalidateDurationId = type.nameToId("invalidateTime");
      invalidateTimedOutId = type.nameToId("invalidateTimeouts");
      invalidateFailedId = type.nameToId("invalidateFailures");
      invalidateId = type.nameToId("invalidates");


      registerInterestInProgressId = type.nameToId("registerInterestsInProgress");
      registerInterestSendInProgressId = sendType.nameToId("registerInterestSendsInProgress");
      registerInterestSendFailedId = sendType.nameToId("registerInterestSendFailures");
      registerInterestSendId = sendType.nameToId("registerInterestSends");
      registerInterestSendDurationId = sendType.nameToId("registerInterestSendTime");
      registerInterestTimedOutId = type.nameToId("registerInterestTimeouts");
      registerInterestFailedId = type.nameToId("registerInterestFailures");
      registerInterestId = type.nameToId("registerInterests");
      registerInterestDurationId = type.nameToId("registerInterestTime");
      unregisterInterestInProgressId = type.nameToId("unregisterInterestsInProgress");
      unregisterInterestSendInProgressId = sendType.nameToId("unregisterInterestSendsInProgress");
      unregisterInterestSendFailedId = sendType.nameToId("unregisterInterestSendFailures");
      unregisterInterestSendId = sendType.nameToId("unregisterInterestSends");
      unregisterInterestSendDurationId = sendType.nameToId("unregisterInterestSendTime");
      unregisterInterestTimedOutId = type.nameToId("unregisterInterestTimeouts");
      unregisterInterestFailedId = type.nameToId("unregisterInterestFailures");
      unregisterInterestId = type.nameToId("unregisterInterests");
      unregisterInterestDurationId = type.nameToId("unregisterInterestTime");
      queryInProgressId = type.nameToId("querysInProgress");
      querySendInProgressId = sendType.nameToId("querySendsInProgress");
      querySendFailedId = sendType.nameToId("querySendFailures");
      querySendId = sendType.nameToId("querySends");
      querySendDurationId = sendType.nameToId("querySendTime");
      queryTimedOutId = type.nameToId("queryTimeouts");
      queryFailedId = type.nameToId("queryFailures");
      queryId = type.nameToId("querys");
      queryDurationId = type.nameToId("queryTime");
      createCQInProgressId = type.nameToId("createCQsInProgress");
      createCQSendInProgressId = sendType.nameToId("createCQSendsInProgress");
      createCQSendFailedId = sendType.nameToId("createCQSendFailures");
      createCQSendId = sendType.nameToId("createCQSends");
      createCQSendDurationId = sendType.nameToId("createCQSendTime");
      createCQTimedOutId = type.nameToId("createCQTimeouts");
      createCQFailedId = type.nameToId("createCQFailures");
      createCQId = type.nameToId("createCQs");
      createCQDurationId = type.nameToId("createCQTime");
      stopCQInProgressId = type.nameToId("stopCQsInProgress");
      stopCQSendInProgressId = sendType.nameToId("stopCQSendsInProgress");
      stopCQSendFailedId = sendType.nameToId("stopCQSendFailures");
      stopCQSendId = sendType.nameToId("stopCQSends");
      stopCQSendDurationId = sendType.nameToId("stopCQSendTime");
      stopCQTimedOutId = type.nameToId("stopCQTimeouts");
      stopCQFailedId = type.nameToId("stopCQFailures");
      stopCQId = type.nameToId("stopCQs");
      stopCQDurationId = type.nameToId("stopCQTime");
      closeCQInProgressId = type.nameToId("closeCQsInProgress");
      closeCQSendInProgressId = sendType.nameToId("closeCQSendsInProgress");
      closeCQSendFailedId = sendType.nameToId("closeCQSendFailures");
      closeCQSendId = sendType.nameToId("closeCQSends");
      closeCQSendDurationId = sendType.nameToId("closeCQSendTime");
      closeCQTimedOutId = type.nameToId("closeCQTimeouts");
      closeCQFailedId = type.nameToId("closeCQFailures");
      closeCQId = type.nameToId("closeCQs");
      closeCQDurationId = type.nameToId("closeCQTime");
      gatewayBatchInProgressId = type.nameToId("gatewayBatchsInProgress");
      gatewayBatchSendInProgressId = sendType.nameToId("gatewayBatchSendsInProgress");
      gatewayBatchSendFailedId = sendType.nameToId("gatewayBatchSendFailures");
      gatewayBatchSendId = sendType.nameToId("gatewayBatchSends");
      gatewayBatchSendDurationId = sendType.nameToId("gatewayBatchSendTime");
      gatewayBatchTimedOutId = type.nameToId("gatewayBatchTimeouts");
      gatewayBatchFailedId = type.nameToId("gatewayBatchFailures");
      gatewayBatchId = type.nameToId("gatewayBatchs");
      gatewayBatchDurationId = type.nameToId("gatewayBatchTime");
      getDurableCQsInProgressId = type.nameToId("getDurableCQsInProgress");
      getDurableCQsSendsInProgressId = sendType.nameToId("getDurableCQsSendsInProgressId");
      getDurableCQsSendFailedId = sendType.nameToId("getDurableCQsSendFailures");
      getDurableCQsSendId = sendType.nameToId("getDurableCQsSends");
      getDurableCQsSendDurationId = sendType.nameToId("getDurableCQsSendTime");
      getDurableCQsTimedOutId = type.nameToId("getDurableCQsTimeouts");
      getDurableCQsFailedId = type.nameToId("getDurableCQsFailures");
      getDurableCQsId = type.nameToId("getDurableCQs");
      getDurableCQsDurationId = type.nameToId("getDurableCQsTime");
      readyForEventsInProgressId = type.nameToId("readyForEventsInProgress");
      readyForEventsSendInProgressId = sendType.nameToId("readyForEventsSendsInProgress");
      readyForEventsSendFailedId = sendType.nameToId("readyForEventsSendFailures");
      readyForEventsSendId = sendType.nameToId("readyForEventsSends");
      readyForEventsSendDurationId = sendType.nameToId("readyForEventsSendTime");
      readyForEventsTimedOutId = type.nameToId("readyForEventsTimeouts");
      readyForEventsFailedId = type.nameToId("readyForEventsFailures");
      readyForEventsId = type.nameToId("readyForEvents");
      readyForEventsDurationId = type.nameToId("readyForEventsTime");
      makePrimaryInProgressId = type.nameToId("makePrimarysInProgress");
      makePrimarySendInProgressId = sendType.nameToId("makePrimarySendsInProgress");
      makePrimarySendFailedId = sendType.nameToId("makePrimarySendFailures");
      makePrimarySendId = sendType.nameToId("makePrimarySends");
      makePrimarySendDurationId = sendType.nameToId("makePrimarySendTime");
      makePrimaryTimedOutId = type.nameToId("makePrimaryTimeouts");
      makePrimaryFailedId = type.nameToId("makePrimaryFailures");
      makePrimaryId = type.nameToId("makePrimarys");
      makePrimaryDurationId = type.nameToId("makePrimaryTime");

      closeConInProgressId = type.nameToId("closeConsInProgress");
      closeConSendInProgressId = sendType.nameToId("closeConSendsInProgress");
      closeConSendFailedId = sendType.nameToId("closeConSendFailures");
      closeConSendId = sendType.nameToId("closeConSends");
      closeConSendDurationId = sendType.nameToId("closeConSendTime");
      closeConTimedOutId = type.nameToId("closeConTimeouts");
      closeConFailedId = type.nameToId("closeConFailures");
      closeConId = type.nameToId("closeCons");
      closeConDurationId = type.nameToId("closeConTime");

      primaryAckInProgressId = type.nameToId("primaryAcksInProgress");
      primaryAckSendInProgressId = sendType.nameToId("primaryAckSendsInProgress");
      primaryAckSendFailedId = sendType.nameToId("primaryAckSendFailures");
      primaryAckSendId = sendType.nameToId("primaryAckSends");
      primaryAckSendDurationId = sendType.nameToId("primaryAckSendTime");
      primaryAckTimedOutId = type.nameToId("primaryAckTimeouts");
      primaryAckFailedId = type.nameToId("primaryAckFailures");
      primaryAckId = type.nameToId("primaryAcks");
      primaryAckDurationId = type.nameToId("primaryAckTime");

      pingInProgressId = type.nameToId("pingsInProgress");
      pingSendInProgressId = sendType.nameToId("pingSendsInProgress");
      pingSendFailedId = sendType.nameToId("pingSendFailures");
      pingSendId = sendType.nameToId("pingSends");
      pingSendDurationId = sendType.nameToId("pingSendTime");
      pingTimedOutId = type.nameToId("pingTimeouts");
      pingFailedId = type.nameToId("pingFailures");
      pingId = type.nameToId("pings");
      pingDurationId = type.nameToId("pingTime");

      registerInstantiatorsInProgressId = type.nameToId("registerInstantiatorsInProgress");
      registerInstantiatorsSendInProgressId =
          sendType.nameToId("registerInstantiatorsSendsInProgress");
      registerInstantiatorsSendFailedId = sendType.nameToId("registerInstantiatorsSendFailures");
      registerInstantiatorsSendId = sendType.nameToId("registerInstantiatorsSends");
      registerInstantiatorsSendDurationId = sendType.nameToId("registerInstantiatorsSendTime");
      registerInstantiatorsTimedOutId = type.nameToId("registerInstantiatorsTimeouts");
      registerInstantiatorsFailedId = type.nameToId("registerInstantiatorsFailures");
      registerInstantiatorsId = type.nameToId("registerInstantiators");
      registerInstantiatorsDurationId = type.nameToId("registerInstantiatorsTime");

      registerDataSerializersInProgressId = type.nameToId("registerDataSerializersInProgress");
      registerDataSerializersSendInProgressId =
          sendType.nameToId("registerDataSerializersSendInProgress");
      registerDataSerializersSendFailedId =
          sendType.nameToId("registerDataSerializersSendFailures");
      registerDataSerializersSendId = sendType.nameToId("registerDataSerializersSends");
      registerDataSerializersSendDurationId = sendType.nameToId("registerDataSerializersSendTime");
      registerDataSerializersTimedOutId = type.nameToId("registerDataSerializersTimeouts");
      registerDataSerializersFailedId = type.nameToId("registerDataSerializersFailures");
      registerDataSerializersId = type.nameToId("registerDataSerializers");
      registerDataSerializersDurationId = type.nameToId("registerDataSerializersTime");

      putAllInProgressId = type.nameToId("putAllsInProgress");
      putAllSendInProgressId = sendType.nameToId("putAllSendsInProgress");
      putAllSendFailedId = sendType.nameToId("putAllSendFailures");
      putAllSendId = sendType.nameToId("putAllSends");
      putAllSendDurationId = sendType.nameToId("putAllSendTime");
      putAllTimedOutId = type.nameToId("putAllTimeouts");
      putAllFailedId = type.nameToId("putAllFailures");
      putAllId = type.nameToId("putAlls");
      putAllDurationId = type.nameToId("putAllTime");

      removeAllInProgressId = type.nameToId("removeAllsInProgress");
      removeAllSendInProgressId = sendType.nameToId("removeAllSendsInProgress");
      removeAllSendFailedId = sendType.nameToId("removeAllSendFailures");
      removeAllSendId = sendType.nameToId("removeAllSends");
      removeAllSendDurationId = sendType.nameToId("removeAllSendTime");
      removeAllTimedOutId = type.nameToId("removeAllTimeouts");
      removeAllFailedId = type.nameToId("removeAllFailures");
      removeAllId = type.nameToId("removeAlls");
      removeAllDurationId = type.nameToId("removeAllTime");

      getAllInProgressId = type.nameToId("getAllsInProgress");
      getAllSendInProgressId = sendType.nameToId("getAllSendsInProgress");
      getAllSendFailedId = sendType.nameToId("getAllSendFailures");
      getAllSendId = sendType.nameToId("getAllSends");
      getAllSendDurationId = sendType.nameToId("getAllSendTime");
      getAllTimedOutId = type.nameToId("getAllTimeouts");
      getAllFailedId = type.nameToId("getAllFailures");
      getAllId = type.nameToId("getAlls");
      getAllDurationId = type.nameToId("getAllTime");

      connectionsId = type.nameToId("connections");
      connectsId = type.nameToId("connects");
      disconnectsId = type.nameToId("disconnects");

      receivedBytesId = type.nameToId("receivedBytes");
      sentBytesId = type.nameToId("sentBytes");
      messagesBeingReceivedId = type.nameToId("messagesBeingReceived");
      messageBytesBeingReceivedId = type.nameToId("messageBytesBeingReceived");

      executeFunctionInProgressId = type.nameToId("executeFunctionsInProgress");
      executeFunctionSendInProgressId = sendType.nameToId("executeFunctionSendsInProgress");
      executeFunctionSendFailedId = sendType.nameToId("executeFunctionSendFailures");
      executeFunctionSendId = sendType.nameToId("executeFunctionSends");
      executeFunctionSendDurationId = sendType.nameToId("executeFunctionSendTime");
      executeFunctionTimedOutId = type.nameToId("executeFunctionTimeouts");
      executeFunctionFailedId = type.nameToId("executeFunctionFailures");
      executeFunctionId = type.nameToId("executeFunctions");
      executeFunctionDurationId = type.nameToId("executeFunctionTime");

      getClientPRMetadataInProgressId = type.nameToId("getClientPRMetadataInProgress");
      getClientPRMetadataSendInProgressId = sendType.nameToId("getClientPRMetadataSendsInProgress");
      getClientPRMetadataSendFailedId = sendType.nameToId("getClientPRMetadataSendFailures");
      getClientPRMetadataSendId = sendType.nameToId("getClientPRMetadataSendsSuccessful");
      getClientPRMetadataSendDurationId = sendType.nameToId("getClientPRMetadataSendTime");
      getClientPRMetadataTimedOutId = type.nameToId("getClientPRMetadataTimeouts");
      getClientPRMetadataFailedId = type.nameToId("getClientPRMetadataFailures");
      getClientPRMetadataId = type.nameToId("getClientPRMetadataSuccessful");
      getClientPRMetadataDurationId = type.nameToId("getClientPRMetadataTime");

      getClientPartitionAttributesInProgressId =
          type.nameToId("getClientPartitionAttributesInProgress");
      getClientPartitionAttributesSendInProgressId =
          sendType.nameToId("getClientPartitionAttributesSendsInProgress");
      getClientPartitionAttributesSendFailedId =
          sendType.nameToId("getClientPartitionAttributesSendFailures");
      getClientPartitionAttributesSendId =
          sendType.nameToId("getClientPartitionAttributesSendsSuccessful");
      getClientPartitionAttributesSendDurationId =
          sendType.nameToId("getClientPartitionAttributesSendTime");
      getClientPartitionAttributesTimedOutId =
          type.nameToId("getClientPartitionAttributesTimeouts");
      getClientPartitionAttributesFailedId = type.nameToId("getClientPartitionAttributesFailures");
      getClientPartitionAttributesId = type.nameToId("getClientPartitionAttributesSuccessful");
      getClientPartitionAttributesDurationId = type.nameToId("getClientPartitionAttributesTime");

      getPDXTypeByIdInProgressId = type.nameToId("getPDXTypeByIdInProgress");
      getPDXTypeByIdSendInProgressId = sendType.nameToId("getPDXTypeByIdSendsInProgress");
      getPDXTypeByIdSendFailedId = sendType.nameToId("getPDXTypeByIdSendFailures");
      getPDXTypeByIdSendId = sendType.nameToId("getPDXTypeByIdSendsSuccessful");
      getPDXTypeByIdSendDurationId = sendType.nameToId("getPDXTypeByIdSendTime");
      getPDXTypeByIdTimedOutId = type.nameToId("getPDXTypeByIdTimeouts");
      getPDXTypeByIdFailedId = type.nameToId("getPDXTypeByIdFailures");
      getPDXTypeByIdId = type.nameToId("getPDXTypeByIdSuccessful");
      getPDXTypeByIdDurationId = type.nameToId("getPDXTypeByIdTime");

      getPDXIdForTypeInProgressId = type.nameToId("getPDXIdForTypeInProgress");
      getPDXIdForTypeSendInProgressId = sendType.nameToId("getPDXIdForTypeSendsInProgress");
      getPDXIdForTypeSendFailedId = sendType.nameToId("getPDXIdForTypeSendFailures");
      getPDXIdForTypeSendId = sendType.nameToId("getPDXIdForTypeSendsSuccessful");
      getPDXIdForTypeSendDurationId = sendType.nameToId("getPDXIdForTypeSendTime");
      getPDXIdForTypeTimedOutId = type.nameToId("getPDXIdForTypeTimeouts");
      getPDXIdForTypeFailedId = type.nameToId("getPDXIdForTypeFailures");
      getPDXIdForTypeId = type.nameToId("getPDXIdForTypeSuccessful");
      getPDXIdForTypeDurationId = type.nameToId("getPDXIdForTypeTime");

      addPdxTypeInProgressId = type.nameToId("addPdxTypeInProgress");
      addPdxTypeSendInProgressId = sendType.nameToId("addPdxTypeSendsInProgress");
      addPdxTypeSendFailedId = sendType.nameToId("addPdxTypeSendFailures");
      addPdxTypeSendId = sendType.nameToId("addPdxTypeSendsSuccessful");
      addPdxTypeSendDurationId = sendType.nameToId("addPdxTypeSendTime");
      addPdxTypeTimedOutId = type.nameToId("addPdxTypeTimeouts");
      addPdxTypeFailedId = type.nameToId("addPdxTypeFailures");
      addPdxTypeId = type.nameToId("addPdxTypeSuccessful");
      addPdxTypeDurationId = type.nameToId("addPdxTypeTime");


      opIds = new int[] {getId, putId, destroyId, destroyRegionId, clearId, containsKeyId, keySetId,
          registerInterestId, unregisterInterestId, queryId, createCQId, stopCQId, closeCQId,
          gatewayBatchId, readyForEventsId, makePrimaryId, closeConId, primaryAckId, pingId,
          putAllId, removeAllId, getAllId, registerInstantiatorsId, executeFunctionId,
          getClientPRMetadataId, getClientPartitionAttributesId, getPDXTypeByIdId,
          getPDXIdForTypeId, addPdxTypeId};
    } catch (RuntimeException t) {
      t.printStackTrace();
      throw t;
    }
  }

  private static long getStatTime() {
    return DistributionStats.getStatTime();
  }

  // instance fields
  private final Statistics stats;
  private final Statistics sendStats;
  private final PoolStats poolStats;

  public ConnectionStats(StatisticsFactory factory, String name,
      PoolStats poolStats/* , GatewayStats gatewayStats */) {
    initializeStats(factory);
    this.stats = factory.createAtomicStatistics(type, "ClientStats-" + name);
    this.sendStats = factory.createAtomicStatistics(sendType, "ClientSendStats-" + name);
    this.poolStats = poolStats;
  }

  /**
   * Records that the specified get is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endGetSend} and
   * {@link #endGet}.
   *
   * @return the start time of this get
   */
  public long startGet() {
    this.stats.incInt(getInProgressId, 1);
    this.sendStats.incInt(getSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the get has completed
   *
   * @param startTime the value returned by {@link #startGet}.
   * @param failed true if the send of the get failed
   */
  public void endGetSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getSendInProgressId, -1);
    int endGetSendId;
    if (failed) {
      endGetSendId = getSendFailedId;
    } else {
      endGetSendId = getSendId;
    }
    this.sendStats.incInt(endGetSendId, 1);
    this.stats.incLong(getSendDurationId, duration);
  }

  /**
   * Records that the specified get has ended
   *
   * @param startTime the value returned by {@link #startGet}.
   * @param timedOut true if get timed out
   * @param failed true if get failed
   */
  public void endGet(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getInProgressId, -1);
    int endGetId;
    if (timedOut) {
      endGetId = getTimedOutId;
    } else if (failed) {
      endGetId = getFailedId;
    } else {
      endGetId = getId;
    }
    this.stats.incInt(endGetId, 1);
    this.stats.incLong(getDurationId, duration);
  }

  public int getGets() {
    return this.stats.getInt(getId);
  }

  public long getGetDuration() {
    return this.stats.getLong(getDurationId);
  }

  /**
   * Records that the specified put is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endPutSend} and
   * {@link #endPut}.
   *
   * @return the start time of this put
   */
  public long startPut() {
    this.stats.incInt(putInProgressId, 1);
    this.sendStats.incInt(putSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the put has completed
   *
   * @param startTime the value returned by {@link #startPut}.
   * @param failed true if the send of the put failed
   */
  public void endPutSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(putSendInProgressId, -1);
    int endPutSendId;
    if (failed) {
      endPutSendId = putSendFailedId;
    } else {
      endPutSendId = putSendId;
    }
    this.sendStats.incInt(endPutSendId, 1);
    this.stats.incLong(putSendDurationId, duration);
  }

  /**
   * Records that the specified put has ended
   *
   * @param startTime the value returned by {@link #startPut}.
   * @param timedOut true if put timed out
   * @param failed true if put failed
   */
  public void endPut(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(putInProgressId, -1);
    int endPutId;
    if (timedOut) {
      endPutId = putTimedOutId;
    } else if (failed) {
      endPutId = putFailedId;
    } else {
      endPutId = putId;
    }
    this.stats.incInt(endPutId, 1);
    this.stats.incLong(putDurationId, duration);
  }

  public int getPuts() {
    return this.stats.getInt(putId);
  }

  public long getPutDuration() {
    return this.stats.getLong(putDurationId);
  }

  /**
   * Records that the specified destroy is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endDestroySend} and
   * {@link #endDestroy}.
   *
   * @return the start time of this destroy
   */
  public long startDestroy() {
    this.stats.incInt(destroyInProgressId, 1);
    this.sendStats.incInt(destroySendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the destroy has completed
   *
   * @param startTime the value returned by {@link #startDestroy}.
   * @param failed true if the send of the destroy failed
   */
  public void endDestroySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(destroySendInProgressId, -1);
    int endDestroySendId;
    if (failed) {
      endDestroySendId = destroySendFailedId;
    } else {
      endDestroySendId = destroySendId;
    }
    this.sendStats.incInt(endDestroySendId, 1);
    this.stats.incLong(destroySendDurationId, duration);
  }

  /**
   * Records that the specified destroy has ended
   *
   * @param startTime the value returned by {@link #startDestroy}.
   * @param timedOut true if destroy timed out
   * @param failed true if destroy failed
   */
  public void endDestroy(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(destroyInProgressId, -1);
    int endDestroyId;
    if (timedOut) {
      endDestroyId = destroyTimedOutId;
    } else if (failed) {
      endDestroyId = destroyFailedId;
    } else {
      endDestroyId = destroyId;
    }
    this.stats.incInt(endDestroyId, 1);
    this.stats.incLong(destroyDurationId, duration);
  }

  /**
   * Records that the specified destroyRegion is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endDestroyRegionSend} and
   * {@link #endDestroyRegion}.
   *
   * @return the start time of this destroyRegion
   */
  public long startDestroyRegion() {
    this.stats.incInt(destroyRegionInProgressId, 1);
    this.sendStats.incInt(destroyRegionSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the destroyRegion has completed
   *
   * @param startTime the value returned by {@link #startDestroyRegion}.
   * @param failed true if the send of the destroyRegion failed
   */
  public void endDestroyRegionSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(destroyRegionSendInProgressId, -1);
    int endDestroyRegionSendId;
    if (failed) {
      endDestroyRegionSendId = destroyRegionSendFailedId;
    } else {
      endDestroyRegionSendId = destroyRegionSendId;
    }
    this.sendStats.incInt(endDestroyRegionSendId, 1);
    this.stats.incLong(destroyRegionSendDurationId, duration);
  }

  /**
   * Records that the specified destroyRegion has ended
   *
   * @param startTime the value returned by {@link #startDestroyRegion}.
   * @param timedOut true if destroyRegion timed out
   * @param failed true if destroyRegion failed
   */
  public void endDestroyRegion(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(destroyRegionInProgressId, -1);
    int endDestroyRegionId;
    if (timedOut) {
      endDestroyRegionId = destroyRegionTimedOutId;
    } else if (failed) {
      endDestroyRegionId = destroyRegionFailedId;
    } else {
      endDestroyRegionId = destroyRegionId;
    }
    this.stats.incInt(endDestroyRegionId, 1);
    this.stats.incLong(destroyRegionDurationId, duration);
  }

  /**
   * Records that the specified clear is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endClearSend} and
   * {@link #endClear}.
   *
   * @return the start time of this clear
   */
  public long startClear() {
    this.stats.incInt(clearInProgressId, 1);
    this.sendStats.incInt(clearSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the clear has completed
   *
   * @param startTime the value returned by {@link #startClear}.
   * @param failed true if the send of the clear failed
   */
  public void endClearSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(clearSendInProgressId, -1);
    int endClearSendId;
    if (failed) {
      endClearSendId = clearSendFailedId;
    } else {
      endClearSendId = clearSendId;
    }
    this.sendStats.incInt(endClearSendId, 1);
    this.stats.incLong(clearSendDurationId, duration);
  }

  /**
   * Records that the specified clear has ended
   *
   * @param startTime the value returned by {@link #startClear}.
   * @param timedOut true if clear timed out
   * @param failed true if clear failed
   */
  public void endClear(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(clearInProgressId, -1);
    int endClearId;
    if (timedOut) {
      endClearId = clearTimedOutId;
    } else if (failed) {
      endClearId = clearFailedId;
    } else {
      endClearId = clearId;
    }
    this.stats.incInt(endClearId, 1);
    this.stats.incLong(clearDurationId, duration);
  }

  /**
   * Records that the specified containsKey is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endContainsKeySend} and
   * {@link #endContainsKey}.
   *
   * @return the start time of this containsKey
   */
  public long startContainsKey() {
    this.stats.incInt(containsKeyInProgressId, 1);
    this.sendStats.incInt(containsKeySendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the containsKey has completed
   *
   * @param startTime the value returned by {@link #startContainsKey}.
   * @param failed true if the send of the containsKey failed
   */
  public void endContainsKeySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(containsKeySendInProgressId, -1);
    int endContainsKeySendId;
    if (failed) {
      endContainsKeySendId = containsKeySendFailedId;
    } else {
      endContainsKeySendId = containsKeySendId;
    }
    this.sendStats.incInt(endContainsKeySendId, 1);
    this.stats.incLong(containsKeySendDurationId, duration);
  }

  /**
   * Records that the specified containsKey has ended
   *
   * @param startTime the value returned by {@link #startContainsKey}.
   * @param timedOut true if containsKey timed out
   * @param failed true if containsKey failed
   */
  public void endContainsKey(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(containsKeyInProgressId, -1);
    int endContainsKeyId;
    if (timedOut) {
      endContainsKeyId = containsKeyTimedOutId;
    } else if (failed) {
      endContainsKeyId = containsKeyFailedId;
    } else {
      endContainsKeyId = containsKeyId;
    }
    this.stats.incInt(endContainsKeyId, 1);
    this.stats.incLong(containsKeyDurationId, duration);
  }

  /**
   * Records that the specified keySet is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endKeySetSend} and
   * {@link #endKeySet}.
   *
   * @return the start time of this keySet
   */
  public long startKeySet() {
    this.stats.incInt(keySetInProgressId, 1);
    this.sendStats.incInt(keySetSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the keySet has completed
   *
   * @param startTime the value returned by {@link #startKeySet}.
   * @param failed true if the send of the keySet failed
   */
  public void endKeySetSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(keySetSendInProgressId, -1);
    int endKeySetSendId;
    if (failed) {
      endKeySetSendId = keySetSendFailedId;
    } else {
      endKeySetSendId = keySetSendId;
    }
    this.sendStats.incInt(endKeySetSendId, 1);
    this.stats.incLong(keySetSendDurationId, duration);
  }

  /**
   * Records that the specified keySet has ended
   *
   * @param startTime the value returned by {@link #startKeySet}.
   * @param timedOut true if keySet timed out
   * @param failed true if keySet failed
   */
  public void endKeySet(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(keySetInProgressId, -1);
    int endKeySetId;
    if (timedOut) {
      endKeySetId = keySetTimedOutId;
    } else if (failed) {
      endKeySetId = keySetFailedId;
    } else {
      endKeySetId = keySetId;
    }
    this.stats.incInt(endKeySetId, 1);
    this.stats.incLong(keySetDurationId, duration);
  }

  /**
   * Records that the specified registerInterest is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endRegisterInterestSend}
   * and {@link #endRegisterInterest}.
   *
   * @return the start time of this registerInterest
   */
  public long startRegisterInterest() {
    this.stats.incInt(registerInterestInProgressId, 1);
    this.sendStats.incInt(registerInterestSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the registerInterest has completed
   *
   * @param startTime the value returned by {@link #startRegisterInterest}.
   * @param failed true if the send of the registerInterest failed
   */
  public void endRegisterInterestSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(registerInterestSendInProgressId, -1);
    int endRegisterInterestSendId;
    if (failed) {
      endRegisterInterestSendId = registerInterestSendFailedId;
    } else {
      endRegisterInterestSendId = registerInterestSendId;
    }
    this.sendStats.incInt(endRegisterInterestSendId, 1);
    this.stats.incLong(registerInterestSendDurationId, duration);
  }

  /**
   * Records that the specified registerInterest has ended
   *
   * @param startTime the value returned by {@link #startRegisterInterest}.
   * @param timedOut true if registerInterest timed out
   * @param failed true if registerInterest failed
   */
  public void endRegisterInterest(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(registerInterestInProgressId, -1);
    int endRegisterInterestId;
    if (timedOut) {
      endRegisterInterestId = registerInterestTimedOutId;
    } else if (failed) {
      endRegisterInterestId = registerInterestFailedId;
    } else {
      endRegisterInterestId = registerInterestId;
    }
    this.stats.incInt(endRegisterInterestId, 1);
    this.stats.incLong(registerInterestDurationId, duration);
  }

  /**
   * Records that the specified unregisterInterest is starting
   * <p>
   * Note: for every call of this method the caller must also call
   * {@link #endUnregisterInterestSend} and {@link #endUnregisterInterest}.
   *
   * @return the start time of this unregisterInterest
   */
  public long startUnregisterInterest() {
    this.stats.incInt(unregisterInterestInProgressId, 1);
    this.sendStats.incInt(unregisterInterestSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the unregisterInterest has completed
   *
   * @param startTime the value returned by {@link #startUnregisterInterest}.
   * @param failed true if the send of the unregisterInterest failed
   */
  public void endUnregisterInterestSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(unregisterInterestSendInProgressId, -1);
    int endUnregisterInterestSendId;
    if (failed) {
      endUnregisterInterestSendId = unregisterInterestSendFailedId;
    } else {
      endUnregisterInterestSendId = unregisterInterestSendId;
    }
    this.sendStats.incInt(endUnregisterInterestSendId, 1);
    this.stats.incLong(unregisterInterestSendDurationId, duration);
  }

  /**
   * Records that the specified unregisterInterest has ended
   *
   * @param startTime the value returned by {@link #startUnregisterInterest}.
   * @param timedOut true if unregisterInterest timed out
   * @param failed true if unregisterInterest failed
   */
  public void endUnregisterInterest(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(unregisterInterestInProgressId, -1);
    int endUnregisterInterestId;
    if (timedOut) {
      endUnregisterInterestId = unregisterInterestTimedOutId;
    } else if (failed) {
      endUnregisterInterestId = unregisterInterestFailedId;
    } else {
      endUnregisterInterestId = unregisterInterestId;
    }
    this.stats.incInt(endUnregisterInterestId, 1);
    this.stats.incLong(unregisterInterestDurationId, duration);
  }

  /**
   * Records that the specified query is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endQuerySend} and
   * {@link #endQuery}.
   *
   * @return the start time of this query
   */
  public long startQuery() {
    this.stats.incInt(queryInProgressId, 1);
    this.sendStats.incInt(querySendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the query has completed
   *
   * @param startTime the value returned by {@link #startQuery}.
   * @param failed true if the send of the query failed
   */
  public void endQuerySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(querySendInProgressId, -1);
    int endQuerySendId;
    if (failed) {
      endQuerySendId = querySendFailedId;
    } else {
      endQuerySendId = querySendId;
    }
    this.sendStats.incInt(endQuerySendId, 1);
    this.stats.incLong(querySendDurationId, duration);
  }

  /**
   * Records that the specified query has ended
   *
   * @param startTime the value returned by {@link #startQuery}.
   * @param timedOut true if query timed out
   * @param failed true if query failed
   */
  public void endQuery(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(queryInProgressId, -1);
    int endQueryId;
    if (timedOut) {
      endQueryId = queryTimedOutId;
    } else if (failed) {
      endQueryId = queryFailedId;
    } else {
      endQueryId = queryId;
    }
    this.stats.incInt(endQueryId, 1);
    this.stats.incLong(queryDurationId, duration);
  }

  /**
   * Records that the specified createCQ is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endCreateCQSend} and
   * {@link #endCreateCQ}.
   *
   * @return the start time of this createCQ
   */
  public long startCreateCQ() {
    this.stats.incInt(createCQInProgressId, 1);
    this.sendStats.incInt(createCQSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the createCQ has completed
   *
   * @param startTime the value returned by {@link #startCreateCQ}.
   * @param failed true if the send of the createCQ failed
   */
  public void endCreateCQSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(createCQSendInProgressId, -1);
    int endCreateCQSendId;
    if (failed) {
      endCreateCQSendId = createCQSendFailedId;
    } else {
      endCreateCQSendId = createCQSendId;
    }
    this.sendStats.incInt(endCreateCQSendId, 1);
    this.stats.incLong(createCQSendDurationId, duration);
  }

  /**
   * Records that the specified createCQ has ended
   *
   * @param startTime the value returned by {@link #startCreateCQ}.
   * @param timedOut true if createCQ timed out
   * @param failed true if createCQ failed
   */
  public void endCreateCQ(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(createCQInProgressId, -1);
    int endCreateCQId;
    if (timedOut) {
      endCreateCQId = createCQTimedOutId;
    } else if (failed) {
      endCreateCQId = createCQFailedId;
    } else {
      endCreateCQId = createCQId;
    }
    this.stats.incInt(endCreateCQId, 1);
    this.stats.incLong(createCQDurationId, duration);
  }

  /**
   * Records that the specified stopCQ is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endStopCQSend} and
   * {@link #endStopCQ}.
   *
   * @return the start time of this stopCQ
   */
  public long startStopCQ() {
    this.stats.incInt(stopCQInProgressId, 1);
    this.sendStats.incInt(stopCQSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the stopCQ has completed
   *
   * @param startTime the value returned by {@link #startStopCQ}.
   * @param failed true if the send of the stopCQ failed
   */
  public void endStopCQSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(stopCQSendInProgressId, -1);
    int endStopCQSendId;
    if (failed) {
      endStopCQSendId = stopCQSendFailedId;
    } else {
      endStopCQSendId = stopCQSendId;
    }
    this.sendStats.incInt(endStopCQSendId, 1);
    this.stats.incLong(stopCQSendDurationId, duration);
  }

  /**
   * Records that the specified stopCQ has ended
   *
   * @param startTime the value returned by {@link #startStopCQ}.
   * @param timedOut true if stopCQ timed out
   * @param failed true if stopCQ failed
   */
  public void endStopCQ(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(stopCQInProgressId, -1);
    int endStopCQId;
    if (timedOut) {
      endStopCQId = stopCQTimedOutId;
    } else if (failed) {
      endStopCQId = stopCQFailedId;
    } else {
      endStopCQId = stopCQId;
    }
    this.stats.incInt(endStopCQId, 1);
    this.stats.incLong(stopCQDurationId, duration);
  }

  /**
   * Records that the specified closeCQ is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endCloseCQSend} and
   * {@link #endCloseCQ}.
   *
   * @return the start time of this closeCQ
   */
  public long startCloseCQ() {
    this.stats.incInt(closeCQInProgressId, 1);
    this.sendStats.incInt(closeCQSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the closeCQ has completed
   *
   * @param startTime the value returned by {@link #startCloseCQ}.
   * @param failed true if the send of the closeCQ failed
   */
  public void endCloseCQSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(closeCQSendInProgressId, -1);
    int endCloseCQSendId;
    if (failed) {
      endCloseCQSendId = closeCQSendFailedId;
    } else {
      endCloseCQSendId = closeCQSendId;
    }
    this.sendStats.incInt(endCloseCQSendId, 1);
    this.stats.incLong(closeCQSendDurationId, duration);
  }

  /**
   * Records that the specified closeCQ has ended
   *
   * @param startTime the value returned by {@link #startCloseCQ}.
   * @param timedOut true if closeCQ timed out
   * @param failed true if closeCQ failed
   */
  public void endCloseCQ(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(closeCQInProgressId, -1);
    int endCloseCQId;
    if (timedOut) {
      endCloseCQId = closeCQTimedOutId;
    } else if (failed) {
      endCloseCQId = closeCQFailedId;
    } else {
      endCloseCQId = closeCQId;
    }
    this.stats.incInt(endCloseCQId, 1);
    this.stats.incLong(closeCQDurationId, duration);
  }

  /**
   * Records that the specified stopCQ is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endStopCQSend} and
   * {@link #endStopCQ}.
   *
   * @return the start time of this stopCQ
   */
  public long startGetDurableCQs() {
    this.stats.incInt(getDurableCQsInProgressId, 1);
    this.sendStats.incInt(getDurableCQsSendsInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the stopCQ has completed
   *
   * @param startTime the value returned by {@link #startStopCQ}.
   * @param failed true if the send of the stopCQ failed
   */
  public void endGetDurableCQsSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getDurableCQsSendsInProgressId, -1);
    int endGetDurableCQsSendId;
    if (failed) {
      endGetDurableCQsSendId = getDurableCQsSendFailedId;
    } else {
      endGetDurableCQsSendId = getDurableCQsSendId;
    }
    this.sendStats.incInt(endGetDurableCQsSendId, 1);
    this.stats.incLong(getDurableCQsSendDurationId, duration);
  }

  /**
   * Records that the specified stopCQ has ended
   *
   * @param startTime the value returned by {@link #startStopCQ}.
   * @param timedOut true if stopCQ timed out
   * @param failed true if stopCQ failed
   */
  public void endGetDurableCQs(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getDurableCQsInProgressId, -1);
    int endGetDurableCQsId;
    if (timedOut) {
      endGetDurableCQsId = getDurableCQsTimedOutId;
    } else if (failed) {
      endGetDurableCQsId = getDurableCQsFailedId;
    } else {
      endGetDurableCQsId = getDurableCQsId;
    }
    this.stats.incInt(endGetDurableCQsId, 1);
    this.stats.incLong(getDurableCQsDurationId, duration);
  }

  /**
   * Records that the specified gatewayBatch is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endGatewayBatchSend} and
   * {@link #endGatewayBatch}.
   *
   * @return the start time of this gatewayBatch
   */
  public long startGatewayBatch() {
    this.stats.incInt(gatewayBatchInProgressId, 1);
    this.sendStats.incInt(gatewayBatchSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the gatewayBatch has completed
   *
   * @param startTime the value returned by {@link #startGatewayBatch}.
   * @param failed true if the send of the gatewayBatch failed
   */
  public void endGatewayBatchSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(gatewayBatchSendInProgressId, -1);
    int endGatewayBatchSendId;
    if (failed) {
      endGatewayBatchSendId = gatewayBatchSendFailedId;
    } else {
      endGatewayBatchSendId = gatewayBatchSendId;
    }
    this.sendStats.incInt(endGatewayBatchSendId, 1);
    this.stats.incLong(gatewayBatchSendDurationId, duration);
  }

  /**
   * Records that the specified gatewayBatch has ended
   *
   * @param startTime the value returned by {@link #startGatewayBatch}.
   * @param timedOut true if gatewayBatch timed out
   * @param failed true if gatewayBatch failed
   */
  public void endGatewayBatch(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(gatewayBatchInProgressId, -1);
    int endGatewayBatchId;
    if (timedOut) {
      endGatewayBatchId = gatewayBatchTimedOutId;
    } else if (failed) {
      endGatewayBatchId = gatewayBatchFailedId;
    } else {
      endGatewayBatchId = gatewayBatchId;
    }
    this.stats.incInt(endGatewayBatchId, 1);
    this.stats.incLong(gatewayBatchDurationId, duration);
  }

  /**
   * Records that the specified readyForEvents is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endReadyForEventsSend}
   * and {@link #endReadyForEvents}.
   *
   * @return the start time of this readyForEvents
   */
  public long startReadyForEvents() {
    this.stats.incInt(readyForEventsInProgressId, 1);
    this.sendStats.incInt(readyForEventsSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the readyForEvents has completed
   *
   * @param startTime the value returned by {@link #startReadyForEvents}.
   * @param failed true if the send of the readyForEvents failed
   */
  public void endReadyForEventsSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(readyForEventsSendInProgressId, -1);
    int endReadyForEventsSendId;
    if (failed) {
      endReadyForEventsSendId = readyForEventsSendFailedId;
    } else {
      endReadyForEventsSendId = readyForEventsSendId;
    }
    this.sendStats.incInt(endReadyForEventsSendId, 1);
    this.stats.incLong(readyForEventsSendDurationId, duration);
  }

  /**
   * Records that the specified readyForEvents has ended
   *
   * @param startTime the value returned by {@link #startReadyForEvents}.
   * @param timedOut true if readyForEvents timed out
   * @param failed true if readyForEvents failed
   */
  public void endReadyForEvents(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(readyForEventsInProgressId, -1);
    int endReadyForEventsId;
    if (timedOut) {
      endReadyForEventsId = readyForEventsTimedOutId;
    } else if (failed) {
      endReadyForEventsId = readyForEventsFailedId;
    } else {
      endReadyForEventsId = readyForEventsId;
    }
    this.stats.incInt(endReadyForEventsId, 1);
    this.stats.incLong(readyForEventsDurationId, duration);
  }

  /**
   * Records that the specified makePrimary is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endMakePrimarySend} and
   * {@link #endMakePrimary}.
   *
   * @return the start time of this makePrimary
   */
  public long startMakePrimary() {
    this.stats.incInt(makePrimaryInProgressId, 1);
    this.sendStats.incInt(makePrimarySendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the makePrimary has completed
   *
   * @param startTime the value returned by {@link #startMakePrimary}.
   * @param failed true if the send of the makePrimary failed
   */
  public void endMakePrimarySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(makePrimarySendInProgressId, -1);
    int endMakePrimarySendId;
    if (failed) {
      endMakePrimarySendId = makePrimarySendFailedId;
    } else {
      endMakePrimarySendId = makePrimarySendId;
    }
    this.sendStats.incInt(endMakePrimarySendId, 1);
    this.stats.incLong(makePrimarySendDurationId, duration);
  }

  /**
   * Records that the specified makePrimary has ended
   *
   * @param startTime the value returned by {@link #startMakePrimary}.
   * @param timedOut true if makePrimary timed out
   * @param failed true if makePrimary failed
   */
  public void endMakePrimary(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(makePrimaryInProgressId, -1);
    int endMakePrimaryId;
    if (timedOut) {
      endMakePrimaryId = makePrimaryTimedOutId;
    } else if (failed) {
      endMakePrimaryId = makePrimaryFailedId;
    } else {
      endMakePrimaryId = makePrimaryId;
    }
    this.stats.incInt(endMakePrimaryId, 1);
    this.stats.incLong(makePrimaryDurationId, duration);
  }

  /**
   * Records that the specified closeCon is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endCloseConSend} and
   * {@link #endCloseCon}.
   *
   * @return the start time of this closeCon
   */
  public long startCloseCon() {
    this.stats.incInt(closeConInProgressId, 1);
    this.sendStats.incInt(closeConSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the closeCon has completed
   *
   * @param startTime the value returned by {@link #startCloseCon}.
   * @param failed true if the send of the closeCon failed
   */
  public void endCloseConSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(closeConSendInProgressId, -1);
    int endCloseConSendId;
    if (failed) {
      endCloseConSendId = closeConSendFailedId;
    } else {
      endCloseConSendId = closeConSendId;
    }
    this.sendStats.incInt(endCloseConSendId, 1);
    this.stats.incLong(closeConSendDurationId, duration);
  }

  /**
   * Records that the specified closeCon has ended
   *
   * @param startTime the value returned by {@link #startCloseCon}.
   * @param timedOut true if closeCon timed out
   * @param failed true if closeCon failed
   */
  public void endCloseCon(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(closeConInProgressId, -1);
    int endCloseConId;
    if (timedOut) {
      endCloseConId = closeConTimedOutId;
    } else if (failed) {
      endCloseConId = closeConFailedId;
    } else {
      endCloseConId = closeConId;
    }
    this.stats.incInt(endCloseConId, 1);
    this.stats.incLong(closeConDurationId, duration);
  }

  /**
   * Records that the specified primaryAck is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endPrimaryAckSend} and
   * {@link #endPrimaryAck}.
   *
   * @return the start time of this primaryAck
   */
  public long startPrimaryAck() {
    this.stats.incInt(primaryAckInProgressId, 1);
    this.sendStats.incInt(primaryAckSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the primaryAck has completed
   *
   * @param startTime the value returned by {@link #startPrimaryAck}.
   * @param failed true if the send of the primaryAck failed
   */
  public void endPrimaryAckSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(primaryAckSendInProgressId, -1);
    int endPrimaryAckSendId;
    if (failed) {
      endPrimaryAckSendId = primaryAckSendFailedId;
    } else {
      endPrimaryAckSendId = primaryAckSendId;
    }
    this.sendStats.incInt(endPrimaryAckSendId, 1);
    this.stats.incLong(primaryAckSendDurationId, duration);
  }

  /**
   * Records that the specified primaryAck has ended
   *
   * @param startTime the value returned by {@link #startPrimaryAck}.
   * @param timedOut true if primaryAck timed out
   * @param failed true if primaryAck failed
   */
  public void endPrimaryAck(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(primaryAckInProgressId, -1);
    int endPrimaryAckId;
    if (timedOut) {
      endPrimaryAckId = primaryAckTimedOutId;
    } else if (failed) {
      endPrimaryAckId = primaryAckFailedId;
    } else {
      endPrimaryAckId = primaryAckId;
    }
    this.stats.incInt(endPrimaryAckId, 1);
    this.stats.incLong(primaryAckDurationId, duration);
  }

  /**
   * Records that the specified ping is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endPingSend} and
   * {@link #endPing}.
   *
   * @return the start time of this ping
   */
  public long startPing() {
    this.stats.incInt(pingInProgressId, 1);
    this.sendStats.incInt(pingSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the ping has completed
   *
   * @param startTime the value returned by {@link #startPing}.
   * @param failed true if the send of the ping failed
   */
  public void endPingSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(pingSendInProgressId, -1);
    int endPingSendId;
    if (failed) {
      endPingSendId = pingSendFailedId;
    } else {
      endPingSendId = pingSendId;
    }
    this.sendStats.incInt(endPingSendId, 1);
    this.stats.incLong(pingSendDurationId, duration);
  }

  /**
   * Records that the specified ping has ended
   *
   * @param startTime the value returned by {@link #startPing}.
   * @param timedOut true if ping timed out
   * @param failed true if ping failed
   */
  public void endPing(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(pingInProgressId, -1);
    int endPingId;
    if (timedOut) {
      endPingId = pingTimedOutId;
    } else if (failed) {
      endPingId = pingFailedId;
    } else {
      endPingId = pingId;
    }
    this.stats.incInt(endPingId, 1);
    this.stats.incLong(pingDurationId, duration);
  }

  /**
   * Records that the specified registerInstantiators is starting
   * <p>
   * Note: for every call of this method the caller must also call
   * {@link #endRegisterInstantiatorsSend} and {@link #endRegisterInstantiators}.
   *
   * @return the start time of this registerInstantiators
   */
  public long startRegisterInstantiators() {
    this.stats.incInt(registerInstantiatorsInProgressId, 1);
    this.sendStats.incInt(registerInstantiatorsSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public long startRegisterDataSerializers() {
    this.stats.incInt(registerDataSerializersInProgressId, 1);
    this.sendStats.incInt(registerDataSerializersSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the registerInstantiators has completed
   *
   * @param startTime the value returned by {@link #startRegisterInstantiators}.
   * @param failed true if the send of the registerInstantiators failed
   */
  public void endRegisterInstantiatorsSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(registerInstantiatorsSendInProgressId, -1);
    int endRegisterInstantiatorsSendId;
    if (failed) {
      endRegisterInstantiatorsSendId = registerInstantiatorsSendFailedId;
    } else {
      endRegisterInstantiatorsSendId = registerInstantiatorsSendId;
    }
    this.sendStats.incInt(endRegisterInstantiatorsSendId, 1);
    this.stats.incLong(registerInstantiatorsSendDurationId, duration);
  }

  public void endRegisterDataSerializersSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(registerDataSerializersSendInProgressId, -1);
    int endDataSerializersSendId;
    if (failed) {
      endDataSerializersSendId = registerDataSerializersSendFailedId;
    } else {
      endDataSerializersSendId = registerDataSerializersSendId;
    }
    this.sendStats.incInt(endDataSerializersSendId, 1);
    this.stats.incLong(registerDataSerializersSendDurationId, duration);
  }

  /**
   * Records that the specified registerInstantiators has ended
   *
   * @param startTime the value returned by {@link #startRegisterInstantiators}.
   * @param timedOut true if registerInstantiators timed out
   * @param failed true if registerInstantiators failed
   */
  public void endRegisterInstantiators(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(registerInstantiatorsInProgressId, -1);
    int endRegisterInstantiatorsId;
    if (timedOut) {
      endRegisterInstantiatorsId = registerInstantiatorsTimedOutId;
    } else if (failed) {
      endRegisterInstantiatorsId = registerInstantiatorsFailedId;
    } else {
      endRegisterInstantiatorsId = registerInstantiatorsId;
    }
    this.stats.incInt(endRegisterInstantiatorsId, 1);
    this.stats.incLong(registerInstantiatorsDurationId, duration);
  }

  public void endRegisterDataSerializers(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(registerDataSerializersInProgressId, -1);
    int endRegisterDataSerializersId;
    if (timedOut) {
      endRegisterDataSerializersId = registerDataSerializersTimedOutId;
    } else if (failed) {
      endRegisterDataSerializersId = registerDataSerializersFailedId;
    } else {
      endRegisterDataSerializersId = registerDataSerializersId;
    }
    this.stats.incInt(endRegisterDataSerializersId, 1);
    this.stats.incLong(registerDataSerializersDurationId, duration);
  }

  /**
   * Records that the specified putAll is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endPutAllSend} and
   * {@link #endPutAll}.
   *
   * @return the start time of this putAll
   */
  public long startPutAll() {
    this.stats.incInt(putAllInProgressId, 1);
    this.sendStats.incInt(putAllSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the putAll has completed
   *
   * @param startTime the value returned by {@link #startPutAll}.
   * @param failed true if the send of the putAll failed
   */
  public void endPutAllSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(putAllSendInProgressId, -1);
    int endPutAllSendId;
    if (failed) {
      endPutAllSendId = putAllSendFailedId;
    } else {
      endPutAllSendId = putAllSendId;
    }
    this.sendStats.incInt(endPutAllSendId, 1);
    this.stats.incLong(putAllSendDurationId, duration);
  }

  /**
   * Records that the specified putAll has ended
   *
   * @param startTime the value returned by {@link #startPutAll}.
   * @param timedOut true if putAll timed out
   * @param failed true if putAll failed
   */
  public void endPutAll(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(putAllInProgressId, -1);
    int endPutAllId;
    if (timedOut) {
      endPutAllId = putAllTimedOutId;
    } else if (failed) {
      endPutAllId = putAllFailedId;
    } else {
      endPutAllId = putAllId;
    }
    this.stats.incInt(endPutAllId, 1);
    this.stats.incLong(putAllDurationId, duration);
  }

  /**
   * Records that the specified removeAll is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endRemoveAllSend} and
   * {@link #endRemoveAll}.
   *
   * @return the start time of this removeAll
   */
  public long startRemoveAll() {
    this.stats.incInt(removeAllInProgressId, 1);
    this.sendStats.incInt(removeAllSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the removeAll has completed
   *
   * @param startTime the value returned by {@link #startRemoveAll}.
   * @param failed true if the send of the removeAll failed
   */
  public void endRemoveAllSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(removeAllSendInProgressId, -1);
    int endRemoveAllSendId;
    if (failed) {
      endRemoveAllSendId = removeAllSendFailedId;
    } else {
      endRemoveAllSendId = removeAllSendId;
    }
    this.sendStats.incInt(endRemoveAllSendId, 1);
    this.stats.incLong(removeAllSendDurationId, duration);
  }

  /**
   * Records that the specified removeAll has ended
   *
   * @param startTime the value returned by {@link #startRemoveAll}.
   * @param timedOut true if removeAll timed out
   * @param failed true if removeAll failed
   */
  public void endRemoveAll(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(removeAllInProgressId, -1);
    int endRemoveAllId;
    if (timedOut) {
      endRemoveAllId = removeAllTimedOutId;
    } else if (failed) {
      endRemoveAllId = removeAllFailedId;
    } else {
      endRemoveAllId = removeAllId;
    }
    this.stats.incInt(endRemoveAllId, 1);
    this.stats.incLong(removeAllDurationId, duration);
  }

  /**
   * Records that the specified getAll is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endGetAllSend} and
   * {@link #endGetAll}.
   *
   * @return the start time of this getAll
   */
  public long startGetAll() {
    this.stats.incInt(getAllInProgressId, 1);
    this.sendStats.incInt(getAllSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the getAll has completed
   *
   * @param startTime the value returned by {@link #startGetAll}.
   * @param failed true if the send of the getAll failed
   */
  public void endGetAllSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getAllSendInProgressId, -1);
    int endGetAllSendId;
    if (failed) {
      endGetAllSendId = getAllSendFailedId;
    } else {
      endGetAllSendId = getAllSendId;
    }
    this.sendStats.incInt(endGetAllSendId, 1);
    this.stats.incLong(getAllSendDurationId, duration);
  }

  /**
   * Records that the specified getAll has ended
   *
   * @param startTime the value returned by {@link #startGetAll}.
   * @param timedOut true if getAll timed out
   * @param failed true if getAll failed
   */
  public void endGetAll(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getAllInProgressId, -1);
    int endGetAllId;
    if (timedOut) {
      endGetAllId = getAllTimedOutId;
    } else if (failed) {
      endGetAllId = getAllFailedId;
    } else {
      endGetAllId = getAllId;
    }
    this.stats.incInt(endGetAllId, 1);
    this.stats.incLong(getAllDurationId, duration);
  }

  public int getConnections() {
    return this.stats.getInt(connectionsId);
  }

  public int getOps() {
    int ops = 0;
    for (int i = 0; i < opIds.length; i++) {
      ops += this.stats.getInt(i);
    }
    return ops;
  }

  public void incConnections(int delta) {
    this.stats.incInt(connectionsId, delta);
    if (delta > 0) {
      this.stats.incInt(connectsId, delta);
    } else if (delta < 0) {
      this.stats.incInt(disconnectsId, -delta);
    }
    this.poolStats.incConnections(delta);
  }

  private void startClientOp() {
    this.poolStats.startClientOp();
  }

  private void endClientOpSend(long duration, boolean failed) {
    this.poolStats.endClientOpSend(duration, failed);
  }

  private void endClientOp(long duration, boolean timedOut, boolean failed) {
    this.poolStats.endClientOp(duration, timedOut, failed);
  }

  public void close() {
    this.stats.close();
    this.sendStats.close();
  }

  public void incReceivedBytes(long v) {
    this.stats.incLong(receivedBytesId, v);
  }

  public void incSentBytes(long v) {
    this.stats.incLong(sentBytesId, v);
  }

  public void incMessagesBeingReceived(int bytes) {
    stats.incInt(messagesBeingReceivedId, 1);
    if (bytes > 0) {
      stats.incLong(messageBytesBeingReceivedId, bytes);
    }
  }

  public void decMessagesBeingReceived(int bytes) {
    stats.incInt(messagesBeingReceivedId, -1);
    if (bytes > 0) {
      stats.incLong(messageBytesBeingReceivedId, -bytes);
    }
  }

  /**
   * Records that the specified execute Function is starting
   * <p>
   * Note: for every call of this method the caller must also call {@link #endExecuteFunctionSend}
   * and {@link #endExecuteFunction}.
   *
   * @return the start time of this ExecuteFunction
   */
  public long startExecuteFunction() {
    this.stats.incInt(executeFunctionInProgressId, 1);
    this.sendStats.incInt(executeFunctionSendInProgressId, 1);
    return getStatTime();
  }

  /**
   * Records that the send part of the executeFunction has completed
   *
   * @param startTime the value returned by {@link #startExecuteFunction}.
   * @param failed true if the send of the executeFunction failed
   */
  public void endExecuteFunctionSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    this.sendStats.incInt(executeFunctionSendInProgressId, -1);
    int endExecuteFunctionSendId;
    if (failed) {
      endExecuteFunctionSendId = executeFunctionSendFailedId;
    } else {
      endExecuteFunctionSendId = executeFunctionSendId;
    }
    this.sendStats.incInt(endExecuteFunctionSendId, 1);
    this.stats.incLong(executeFunctionSendDurationId, duration);
  }

  /**
   * Records that the specified executeFunction has ended
   *
   * @param startTime the value returned by {@link #startExecuteFunction}.
   * @param timedOut true if executeFunction timed out
   * @param failed true if executeFunction failed
   */
  public void endExecuteFunction(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    this.stats.incInt(executeFunctionInProgressId, -1);
    int endExecuteFunctionId;
    if (timedOut) {
      endExecuteFunctionId = executeFunctionTimedOutId;
    } else if (failed) {
      endExecuteFunctionId = executeFunctionFailedId;
    } else {
      endExecuteFunctionId = executeFunctionId;
    }
    this.stats.incInt(endExecuteFunctionId, 1);
    this.stats.incLong(executeFunctionDurationId, duration);
  }

  public int getExecuteFunctions() {
    return this.stats.getInt(executeFunctionId);
  }

  public long getExecuteFunctionDuration() {
    return this.stats.getLong(executeFunctionDurationId);
  }

  public int getGetDurableCqs() {
    return this.stats.getInt(getDurableCQsId);
  }

  /**
   * Records that the specified GetClientPRMetadata operation is starting
   * <p>
   * Note: for every call of this method the caller must also call
   * {@link #endGetClientPRMetadataSend} and {@link #endGetClientPRMetadata}.
   *
   * @return the start time of this ExecuteFunction
   */
  public long startGetClientPRMetadata() {
    this.stats.incInt(getClientPRMetadataInProgressId, 1);
    this.sendStats.incInt(getClientPRMetadataSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the GetClientPRMetadata has completed
   *
   * @param startTime the value returned by {@link #startGetClientPRMetadata}.
   * @param failed true if the send of the GetClientPRMetadata failed
   */
  public void endGetClientPRMetadataSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getClientPRMetadataSendInProgressId, -1);
    int endGetClientPRMetadataSendId;
    if (failed) {
      endGetClientPRMetadataSendId = getClientPRMetadataSendFailedId;
    } else {
      endGetClientPRMetadataSendId = getClientPRMetadataSendId;
    }
    this.sendStats.incInt(endGetClientPRMetadataSendId, 1);
    this.stats.incLong(getClientPRMetadataSendDurationId, duration);
  }

  /**
   * Records that the specified GetClientPRMetadata has ended
   *
   * @param startTime the value returned by {@link #startGetClientPRMetadata}.
   * @param timedOut true if GetClientPRMetadata timed out
   * @param failed true if GetClientPRMetadata failed
   */
  public void endGetClientPRMetadata(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getClientPRMetadataInProgressId, -1);
    int endGetClientPRMetadataId;
    if (timedOut) {
      endGetClientPRMetadataId = getClientPRMetadataTimedOutId;
    } else if (failed) {
      endGetClientPRMetadataId = getClientPRMetadataFailedId;
    } else {
      endGetClientPRMetadataId = getClientPRMetadataId;
    }
    this.stats.incInt(endGetClientPRMetadataId, 1);
    this.stats.incLong(getClientPRMetadataDurationId, duration);
  }

  /**
   * Records that the specified GetClientPartitionAttributes operation is starting
   * <p>
   * Note: for every call of this method the caller must also call
   * {@link #endGetClientPartitionAttributesSend} and {@link #endGetClientPartitionAttributes}.
   *
   * @return the start time of this GetClientPartitionAttributes
   */
  public long startGetClientPartitionAttributes() {
    this.stats.incInt(getClientPartitionAttributesInProgressId, 1);
    this.sendStats.incInt(getClientPartitionAttributesSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  /**
   * Records that the send part of the GetClientPartitionAttributes operation has completed
   *
   * @param startTime the value returned by {@link #startGetClientPartitionAttributes}.
   * @param failed true if the send of the GetClientPartitionAttributes failed
   */
  public void endGetClientPartitionAttributesSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getClientPartitionAttributesSendInProgressId, -1);
    int endGetClientPartitionAttributesSendId;
    if (failed) {
      endGetClientPartitionAttributesSendId = getClientPartitionAttributesSendFailedId;
    } else {
      endGetClientPartitionAttributesSendId = getClientPartitionAttributesSendId;
    }
    this.sendStats.incInt(endGetClientPartitionAttributesSendId, 1);
    this.stats.incLong(getClientPartitionAttributesSendDurationId, duration);
  }

  /**
   * Records that the specified GetClientPartitionAttributes has ended
   *
   * @param startTime the value returned by {@link #startGetClientPartitionAttributes}.
   * @param timedOut true if GetClientPartitionAttributes timed out
   * @param failed true if GetClientPartitionAttributes failed
   */
  public void endGetClientPartitionAttributes(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getClientPartitionAttributesInProgressId, -1);
    int endGetClientPartitionAttributesId;
    if (timedOut) {
      endGetClientPartitionAttributesId = getClientPartitionAttributesTimedOutId;
    } else if (failed) {
      endGetClientPartitionAttributesId = getClientPartitionAttributesFailedId;
    } else {
      endGetClientPartitionAttributesId = getClientPartitionAttributesId;
    }
    this.stats.incInt(endGetClientPartitionAttributesId, 1);
    this.stats.incLong(getClientPartitionAttributesDurationId, duration);
  }

  public long startGetPDXTypeById() {
    this.stats.incInt(getPDXTypeByIdInProgressId, 1);
    this.sendStats.incInt(getPDXTypeByIdSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public long startGetPDXIdForType() {
    this.stats.incInt(getPDXIdForTypeInProgressId, 1);
    this.sendStats.incInt(getPDXIdForTypeSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endGetPDXTypeByIdSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getPDXTypeByIdSendInProgressId, -1);
    int endGetPDXTypeByIdSendId;
    if (failed) {
      endGetPDXTypeByIdSendId = getPDXTypeByIdSendFailedId;
    } else {
      endGetPDXTypeByIdSendId = getPDXTypeByIdSendId;
    }
    this.sendStats.incInt(endGetPDXTypeByIdSendId, 1);
    this.stats.incLong(getPDXTypeByIdSendDurationId, duration);
  }

  public void endGetPDXIdForTypeSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getPDXIdForTypeSendInProgressId, -1);
    int endGetPDXIdForTypeSendId;
    if (failed) {
      endGetPDXIdForTypeSendId = getPDXIdForTypeSendFailedId;
    } else {
      endGetPDXIdForTypeSendId = getPDXIdForTypeSendId;
    }
    this.stats.incInt(endGetPDXIdForTypeSendId, 1);
    this.stats.incLong(getPDXIdForTypeSendDurationId, duration);
  }

  public void endGetPDXTypeById(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getPDXTypeByIdInProgressId, -1);
    int statId;
    if (timedOut) {
      statId = getPDXTypeByIdTimedOutId;
    } else if (failed) {
      statId = getPDXTypeByIdFailedId;
    } else {
      statId = getPDXTypeByIdId;
    }
    this.stats.incInt(statId, 1);
    this.stats.incLong(getPDXTypeByIdDurationId, duration);
  }

  public void endGetPDXIdForType(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getPDXIdForTypeInProgressId, -1);
    int statId;
    if (timedOut) {
      statId = getPDXIdForTypeTimedOutId;
    } else if (failed) {
      statId = getPDXIdForTypeFailedId;
    } else {
      statId = getPDXIdForTypeId;
    }
    this.stats.incInt(statId, 1);
    this.stats.incLong(getPDXIdForTypeDurationId, duration);
  }

  public long startAddPdxType() {
    this.stats.incInt(addPdxTypeInProgressId, 1);
    this.sendStats.incInt(addPdxTypeSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endAddPdxTypeSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(addPdxTypeSendInProgressId, -1);
    int endAddPdxTypeSendId;
    if (failed) {
      endAddPdxTypeSendId = addPdxTypeSendFailedId;
    } else {
      endAddPdxTypeSendId = addPdxTypeSendId;
    }
    this.sendStats.incInt(endAddPdxTypeSendId, 1);
    this.stats.incLong(addPdxTypeSendDurationId, duration);
  }

  public void endAddPdxType(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(addPdxTypeInProgressId, -1);
    int statId;
    if (timedOut) {
      statId = addPdxTypeTimedOutId;
    } else if (failed) {
      statId = addPdxTypeFailedId;
    } else {
      statId = addPdxTypeId;
    }
    this.stats.incInt(statId, 1);
    this.stats.incLong(addPdxTypeDurationId, duration);
  }

  public long startSize() {
    this.stats.incInt(sizeInProgressId, 1);
    this.sendStats.incInt(sizeSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endSizeSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(sizeSendInProgressId, -1);
    int endSizeSendId;
    if (failed) {
      endSizeSendId = sizeSendFailedId;
    } else {
      endSizeSendId = sizeSendId;
    }
    this.sendStats.incInt(endSizeSendId, 1);
    this.stats.incLong(sizeSendDurationId, duration);

  }

  public void endSize(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(sizeInProgressId, -1);
    int endSizeId;
    if (timedOut) {
      endSizeId = sizeTimedOutId;
    } else if (failed) {
      endSizeId = sizeFailedId;
    } else {
      endSizeId = sizeId;
    }
    this.stats.incInt(endSizeId, 1);
    this.stats.incLong(sizeDurationId, duration);
  }



  public long startInvalidate() {
    this.stats.incInt(invalidateInProgressId, 1);
    this.sendStats.incInt(invalidateSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endInvalidateSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(invalidateSendInProgressId, -1);
    int endInvalidateSendId;
    if (failed) {
      endInvalidateSendId = invalidateSendFailedId;
    } else {
      endInvalidateSendId = invalidateSendId;
    }
    this.sendStats.incInt(endInvalidateSendId, 1);
    this.stats.incLong(invalidateSendDurationId, duration);
  }

  public void endInvalidate(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(invalidateInProgressId, -1);
    int endInvalidateId;
    if (timedOut) {
      endInvalidateId = invalidateTimedOutId;
    } else if (failed) {
      endInvalidateId = invalidateFailedId;
    } else {
      endInvalidateId = invalidateId;
    }
    this.stats.incInt(endInvalidateId, 1);
    this.stats.incLong(invalidateDurationId, duration);
  }

  public long startCommit() {
    this.stats.incInt(commitInProgressId, 1);
    this.sendStats.incInt(commitSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endCommitSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(commitSendInProgressId, -1);
    int endcommitSendId;
    if (failed) {
      endcommitSendId = commitSendFailedId;
    } else {
      endcommitSendId = commitSendId;
    }
    this.sendStats.incInt(endcommitSendId, 1);
    this.stats.incLong(commitSendDurationId, duration);
  }

  public void endCommit(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(commitInProgressId, -1);
    int endcommitId;
    if (timedOut) {
      endcommitId = commitTimedOutId;
    } else if (failed) {
      endcommitId = commitFailedId;
    } else {
      endcommitId = commitId;
    }
    this.stats.incInt(endcommitId, 1);
    this.stats.incLong(commitDurationId, duration);
  }


  public long startGetEntry() {
    this.stats.incInt(getEntryInProgressId, 1);
    this.sendStats.incInt(getEntrySendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endGetEntrySend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(getEntrySendInProgressId, -1);
    int endGetEntrySendId;
    if (failed) {
      endGetEntrySendId = getEntrySendFailedId;
    } else {
      endGetEntrySendId = getEntrySendId;
    }
    this.sendStats.incInt(endGetEntrySendId, 1);
    this.stats.incLong(getEntrySendDurationId, duration);
  }

  public void endGetEntry(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(getEntryInProgressId, -1);
    int endGetEntryId;
    if (timedOut) {
      endGetEntryId = getEntryTimedOutId;
    } else if (failed) {
      endGetEntryId = getEntryFailedId;
    } else {
      endGetEntryId = getEntryId;
    }
    this.stats.incInt(endGetEntryId, 1);
    this.stats.incLong(getEntryDurationId, duration);
  }


  public long startRollback() {
    this.stats.incInt(rollbackInProgressId, 1);
    this.sendStats.incInt(rollbackSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endRollbackSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(rollbackSendInProgressId, -1);
    int endRollbackSendId;
    if (failed) {
      endRollbackSendId = rollbackSendFailedId;
    } else {
      endRollbackSendId = rollbackSendId;
    }
    this.sendStats.incInt(endRollbackSendId, 1);
    this.stats.incLong(rollbackSendDurationId, duration);
  }

  public void endRollback(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(rollbackInProgressId, -1);
    int endRollbackId;
    if (timedOut) {
      endRollbackId = rollbackTimedOutId;
    } else if (failed) {
      endRollbackId = rollbackFailedId;
    } else {
      endRollbackId = rollbackId;
    }
    this.stats.incInt(endRollbackId, 1);
    this.stats.incLong(rollbackDurationId, duration);
  }



  public long startTxFailover() {
    this.stats.incInt(txFailoverInProgressId, 1);
    this.sendStats.incInt(txFailoverSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endTxFailoverSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(txFailoverSendInProgressId, -1);
    int endTxFailoverSendId;
    if (failed) {
      endTxFailoverSendId = txFailoverSendFailedId;
    } else {
      endTxFailoverSendId = txFailoverSendId;
    }
    this.sendStats.incInt(endTxFailoverSendId, 1);
    this.stats.incLong(txFailoverSendDurationId, duration);
  }

  public void endTxFailover(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(txFailoverInProgressId, -1);
    int endTxFailoverId;
    if (timedOut) {
      endTxFailoverId = txFailoverTimedOutId;
    } else if (failed) {
      endTxFailoverId = txFailoverFailedId;
    } else {
      endTxFailoverId = txFailoverId;
    }
    this.stats.incInt(endTxFailoverId, 1);
    this.stats.incLong(txFailoverDurationId, duration);
  }


  public long startTxSynchronization() {
    this.stats.incInt(txSynchronizationInProgressId, 1);
    this.sendStats.incInt(txSynchronizationSendInProgressId, 1);
    startClientOp();
    return getStatTime();
  }

  public void endTxSynchronizationSend(long startTime, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOpSend(duration, failed);
    this.sendStats.incInt(txSynchronizationSendInProgressId, -1);
    int endTxSynchronizationSendId;
    if (failed) {
      endTxSynchronizationSendId = txSynchronizationSendFailedId;
    } else {
      endTxSynchronizationSendId = txSynchronizationSendId;
    }
    this.sendStats.incInt(endTxSynchronizationSendId, 1);
    this.stats.incLong(txSynchronizationSendDurationId, duration);
  }

  public void endTxSynchronization(long startTime, boolean timedOut, boolean failed) {
    long duration = getStatTime() - startTime;
    endClientOp(duration, timedOut, failed);
    this.stats.incInt(txSynchronizationInProgressId, -1);
    int endTxSynchronizationId;
    if (timedOut) {
      endTxSynchronizationId = txSynchronizationTimedOutId;
    } else if (failed) {
      endTxSynchronizationId = txSynchronizationFailedId;
    } else {
      endTxSynchronizationId = txSynchronizationId;
    }
    this.stats.incInt(endTxSynchronizationId, 1);
    this.stats.incLong(txSynchronizationDurationId, duration);
  }
}
