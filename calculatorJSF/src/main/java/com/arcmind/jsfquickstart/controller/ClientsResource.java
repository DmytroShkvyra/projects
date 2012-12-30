/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arcmind.jsfquickstart.controller;

import com.arcmind.jsfquickstart.model.Client;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author USer
 */
@Path("/client")
public class ClientsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClientsResource
     */
    public ClientsResource() {
    }

    /**
     * Retrieves representation of an instance of com.arcmind.jsfquickstart.controller.ClientsResource
     * @return an instance of com.arcmind.jsfquickstart.model.Client
     */
    @GET
    @Produces("application/xml")
    public Client getXml() {
	//TODO return proper representation object
	throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of ClientResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response postXml(Client content) {
	//TODO
	return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ClientResource getClientResource(@PathParam("id") String id) {
	return ClientResource.getInstance(id);
    }
}
