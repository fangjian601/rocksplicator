/*
 *  Copyright 2017 Pinterest, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.pinterest.rocksplicator.controller.bean;

import com.pinterest.rocksplicator.controller.Cluster;
import org.hibernate.validator.constraints.NotEmpty;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Mapping to another type of config: a collection of consistent hash rings.
 *
 * @author shu (shu@pinterest.com)
 */
public class ConsistentHashRingsBean {

  @NotEmpty
  private Cluster cluster;

  private List<ConsistentHashRingBean> consistentHashRings = Collections.emptyList();

  public Cluster getCluster() {
    return cluster;
  }

  public ConsistentHashRingsBean setCluster(Cluster cluster) {
    this.cluster = cluster;
    return this;
  }

  public List<ConsistentHashRingBean> getConsistentHashRings() {
    return new ArrayList<>(consistentHashRings);
  }

  public ConsistentHashRingsBean setConsistentHashRings(
      List<ConsistentHashRingBean> consistentHashRings) {
    this.consistentHashRings = consistentHashRings;
    return this;
  }

  @Override
  public String toString() {
    return cluster.toString();
  }

  public Set<HostBean> getHosts() {
    Set<HostBean> hosts = new HashSet<>();
    for (ConsistentHashRingBean ring : getConsistentHashRings()) {
      for (HostBean hostBean : ring.getHosts()) {
        hosts.add(hostBean);
      }
    }
    return hosts;
  }

}
