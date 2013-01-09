/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseystub.restclients;

import com.mycompany.jerseystub.restclients.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.representation.Form;
import javax.ws.rs.core.Cookie;

/**
 * Jersey REST client generated for REST resource:ServiceService<br>
 *  USAGE:
 * <pre>
 *        ServiceService client = new ServiceService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author User
 */
public class HostComponentService {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/api/v1/clusters/";
    
    //http://localhost:8080/api/v1/clusters/c1/hosts/$AGENT_HOST/host_components/NAMENODE

    public HostComponentService() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        config.getProperties().put(ClientConfig.FEATURE_DISABLE_XML_SECURITY, true);
        client = Client.create(config);
        webResource = client.resource(BASE_URI);
    }
    
     public <T> T postCreateHostComponent(Class<T> responseType, String clusterName, String hostName, String componentName) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}/{2}/{3}/{4}", new Object[]{clusterName, "hosts", hostName, "host_components", componentName}));
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).post(responseType);
    }   
   
}
