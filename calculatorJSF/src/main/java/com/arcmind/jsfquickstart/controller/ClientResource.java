/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arcmind.jsfquickstart.controller;

import com.arcmind.jsfquickstart.model.Client;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author USer
 */
public class ClientResource {

    private String id;

    /**
     * Creates a new instance of ClientResource
     */
    private ClientResource(String id) {
	this.id = id;
    }

    /**
     * Get instance of the ClientResource
     */
    public static ClientResource getInstance(String id) {
	// The user may use some kind of persistence mechanism
	// to store and restore instances of ClientResource class.
	return new ClientResource(id);
    }

    /**
     * Retrieves representation of an instance of com.arcmind.jsfquickstart.controller.ClientResource
     * @return an instance of com.arcmind.jsfquickstart.model.Client
     */
    @GET
    @Produces("application/xml")
    public Client getXml() {
	//TODO return proper representation object
	throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ClientResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(Client content) {
	System.out.println("content.getAge()="+content.getAge()+" content.getName()="+content.getName());
    }

    /**
     * DELETE method for resource ClientResource
     */
    @DELETE
    public void delete() {
    }
}
