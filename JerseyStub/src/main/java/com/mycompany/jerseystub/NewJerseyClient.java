/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseystub;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import javax.ws.rs.core.Cookie;

/**
 * Jersey REST client generated for REST resource:HomeController [/]<br>
 *  USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author User
 */
public class NewJerseyClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/GuiceTest";

    public NewJerseyClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI);
    }

    public <T> T throwErrorHTML(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("throw");
        return resource.get(responseType);
    }

    public <T> T throwErrorJSON(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("throw.json");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getTEXT(Class<T> responseType, String path) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.queryParam("param", path);
        resource = resource.path(java.text.MessageFormat.format("{0}.txt", new Object[]{path}));
        Cookie c = new Cookie("param", path);
        Form f = new Form();    
        f.add("param", path); 
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).header("param", path).cookie(c).post(responseType,f);
    }

    public <T> T getXML(Class<T> responseType, String path) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}.xml", new Object[]{path}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getHTML(Class<T> responseType, String path) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{path}));
        return resource.get(responseType);
    }

    public <T> T throwErrorXML(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("throw.xml");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getJSON(Class<T> responseType, String path) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}.json", new Object[]{path}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T throwErrorText(Class<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("throw.txt");
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    public void close() {
        client.destroy();
    }
    
}
