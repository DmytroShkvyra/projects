/*
 * Copyright 2009-2009 Webtide LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mortbay.jetty.rhttp.server;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.eclipse.jetty.client.Address;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.mortbay.jetty.rhttp.client.RHTTPClient;
import org.mortbay.jetty.rhttp.client.JettyClient;


/**
 * @version $Revision$ $Date$
 */
public class DisconnectClientTest extends TestCase
{
    public void testDifferentClientDisconnects() throws Exception
    {
        GatewayServer server = new GatewayServer();
        Connector connector = new SelectChannelConnector();
        server.addConnector(connector);
        server.start();
        try
        {
            Address address = new Address("localhost", connector.getLocalPort());

            HttpClient httpClient = new HttpClient();
            httpClient.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
            httpClient.start();
            try
            {
                final CountDownLatch latch = new CountDownLatch(1);
                String targetId = "1";
                final RHTTPClient client1 = new JettyClient(httpClient, address, server.getContextPath() + server.getGatewayServletPath(), targetId)
                {
                    @Override
                    protected void connectComplete(byte[] responseContent) throws IOException
                    {
                        // If the other client can disconnect this one, this method is called soon after it disconnected
                        latch.countDown();
                        super.connectComplete(responseContent);
                    }
                };
                client1.connect();
                try
                {
                    final RHTTPClient client2 = new JettyClient(httpClient, address, server.getContextPath() + server.getGatewayServletPath(), targetId);
                    // Disconnect client 2, this should not disconnect client1
                    client2.disconnect();

                    // We want the await() to expire, it means it has not disconnected
                    assertFalse(latch.await(1000, TimeUnit.MILLISECONDS));
                }
                finally
                {
                    client1.disconnect();
                }
            }
            finally
            {
                httpClient.stop();
            }
        }
        finally
        {
            server.stop();
        }
    }
}
