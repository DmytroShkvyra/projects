/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guicetest.controllers;

import com.google.inject.ScopeAnnotation;
import com.google.inject.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author User
 */
@Singleton
@Provider
@Produces("text/plain")
public class ExceptionWrapper implements
        ExceptionMapper<Exception> {
    public Response toResponse(Exception ex) {
        System.out.println("################################");
        StackTraceElement[] elements = ex.getStackTrace();
        StringBuffer message = new StringBuffer();
        for (int i = 0; i < elements.length; i++) {
            StackTraceElement stackTraceElement = elements[i];
            message.append(stackTraceElement.toString()+"\n");
        }
        
        return Response.status(404).
            entity(ex.getMessage()).
            type("text/plain").
            build();
    }
}
