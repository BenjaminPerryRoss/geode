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
package org.apache.geode.pdx.internal;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.geode.cache.wan.GatewaySender;
import org.apache.geode.pdx.PdxInitializationException;

/**
 * A type registration that does nothing. Used if the user explicity disables the type registry
 *
 */
public class NullTypeRegistration implements TypeRegistration {

  @Override
  public int defineType(PdxType newType) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public PdxType getType(int typeId) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public void addRemoteType(int typeId, PdxType type) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public void initialize() {
    // do nothing
  }

  @Override
  public void gatewaySenderStarted(GatewaySender gatewaySender) {
    // do nothing
  }

  @Override
  public void creatingPersistentRegion() {
    // do nothing
  }

  @Override
  public void creatingPool() {
    // do nothing
  }

  @Override
  public int getEnumId(Enum<?> v) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public void addRemoteEnum(int enumId, EnumInfo newInfo) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public int defineEnum(EnumInfo newInfo) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public EnumInfo getEnumById(int enumId) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public Map<Integer, PdxType> types() {
    return Collections.emptyMap();
  }

  @Override
  public Map<Integer, EnumInfo> enums() {
    return Collections.emptyMap();
  }

  @Override
  public PdxType getPdxTypeForField(String fieldName, String className) {
    return null;
  }

  @Override
  public Set<PdxType> getPdxTypesForClassName(String className) {
    return Collections.emptySet();
  }

  @Override
  public boolean isClient() {
    return false;
  }

  @Override
  public void addImportedType(int typeId, PdxType importedType) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public void addImportedEnum(int enumId, EnumInfo importedInfo) {
    throw new PdxInitializationException("Trying to use PDX type, but type registry is disabled");
  }

  @Override
  public int getLocalSize() {
    return 0;
  }

  @Override
  public void removeType(int typeId) {

  }
}
