package org.apache.geode.management.internal.cli.commands;

import java.util.Properties;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.EntryOperation;
import org.apache.geode.cache.PartitionResolver;

public class RebalancePartitionResolver implements PartitionResolver {
  @Override
  public Object getRoutingObject(EntryOperation opDetails) {
    String key = (String) opDetails.getKey();
    int number = Integer.valueOf(key.substring(3));
    int mod = number%108;
    if (mod > 48) {mod = number%3;}
    return mod;
  }

  @Override
  public String getName() {
    return this.getClass().getName();
  }

  @Override
  public void close() {

  }
}
