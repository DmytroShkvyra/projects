/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.api.handlers;

import org.apache.ambari.server.api.resources.ResourceInstance;
import org.apache.ambari.server.api.services.Request;
import org.apache.ambari.server.api.services.Result;
import org.apache.ambari.server.api.services.ResultImpl;
import org.apache.ambari.server.api.services.persistence.PersistenceManager;
import org.apache.ambari.server.api.services.persistence.PersistenceManagerImpl;
import org.apache.ambari.server.api.util.TreeNode;
import org.apache.ambari.server.controller.spi.ClusterController;
import org.apache.ambari.server.controller.spi.Predicate;
import org.apache.ambari.server.controller.spi.RequestStatus;
import org.apache.ambari.server.controller.spi.Resource;
import org.apache.ambari.server.controller.utilities.ClusterControllerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Base handler for operations that persist state to the back-end.
 */
public abstract class BaseManagementHandler implements RequestHandler {

  /**
   * Logger instance.
   */
  protected final static Logger LOG =
      LoggerFactory.getLogger(BaseManagementHandler.class);

  /**
   * PersistenceManager implementation.
   */
  PersistenceManager m_pm = new PersistenceManagerImpl(getClusterController());

  protected BaseManagementHandler() {
  }

  public Result handleRequest(Request request) {
    ResourceInstance resource = request.getResource();
    Predicate queryPredicate = request.getQueryPredicate();
    if (queryPredicate != null) {
      resource.getQuery().setUserPredicate(queryPredicate);
    }

    return handleRequest(resource, request.getHttpBodyProperties());
  }

  protected Result handleRequest(ResourceInstance resource, Set<Map<String, Object>> setProperties) {
    return persist(resource, setProperties);
  }

  protected Result createResult(RequestStatus requestStatus) {

    boolean            isSynchronous = requestStatus.getStatus() == RequestStatus.Status.Complete;
    Result             result        = new ResultImpl(isSynchronous);
    TreeNode<Resource> tree          = result.getResultTree();

    if (! isSynchronous) {
      tree.addChild(requestStatus.getRequestResource(), "request");
    }

    //todo: currently always empty
    Set<Resource> setResources = requestStatus.getAssociatedResources();
    if (! setResources.isEmpty()) {
      TreeNode<Resource> resourcesNode = tree.addChild(null, "resources");

      int count = 1;
      for (Resource resource : setResources) {
        //todo: provide a more meaningful node name
        resourcesNode.addChild(resource, resource.getType() + ":" + count++);
      }
    }

    return result;
  }

  //todo: controller should be injected
  protected ClusterController getClusterController() {
    return ClusterControllerHelper.getClusterController();
  }

  protected PersistenceManager getPersistenceManager() {
    return m_pm;
  }

  protected abstract Result persist(ResourceInstance r, Set<Map<String, Object>> properties);
}
