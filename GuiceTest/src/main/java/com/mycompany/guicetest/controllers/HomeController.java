package com.mycompany.guicetest.controllers;

import com.google.inject.Inject;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mycompany.guicetest.views.FooPage;
import com.google.inject.Singleton;
import javax.ws.rs.POST;

/**
 * Example controller which verifies HTML/JSON/XML and error display are all working.
 */
@Singleton
@Path("/")
@Produces("text/html")
public class HomeController extends BaseController {
        @Inject
        private TestStub stub;
        
        private String name;

        //
        public void setUsername(String name) {
            this.name = name;
            System.out.println(name+"######################");
        }
        
        
    
	@GET
	public Object getHTML() {
		return getHTML("/");
	}

	@GET
	@Path("{path:.*}")
	public Object getHTML(@PathParam("path") String path) {
		Foo foo = new Foo(path+" "+name, path);

		return view(FooPage.class).data(foo);
	}

	@GET
	@Path("{path:.*}.json")
	@Produces("application/json")
	public Object getJSON(@PathParam("path") String path) {
		return new Foo(path, path);
	}

	/**
     *
     * @param path
     * @param s
     * @return
     */
    @POST
	@Path("{path:.*}.txt")
	@Produces("text/plain")
	public Object getTEXT(@PathParam("path") String path, @javax.ws.rs.HeaderParam(value="param") String s, @javax.ws.rs.FormParam(value="param") String f, @javax.ws.rs.QueryParam(value="param") String q, @javax.ws.rs.CookieParam(value="param") String c) {

		return path + "###" + s + "###" + f + "###" + q + "##" + c;
	}

	@GET
	@Path("{path:.*}.xml")
	@Produces("application/xml")
	public Foo getXML(@PathParam("path") String path) {
		return new Foo(path, path);
	}

	/*------------------------------------------------*/

	@GET
	@Path("throw")
	public Object throwErrorHTML() {
		throw new IllegalStateException("This action always throws an exception in HTML.");
	}

	@GET
	@Path("throw.json")
	@Produces("application/json")
	public Object throwErrorJSON() {
		throw new IllegalStateException("This action always throws an exception in JSON.");
	}

	@GET
	@Path("throw.txt")
	@Produces("text/plain")
	public Object throwErrorText() {
		throw new IllegalStateException("This action always throws an exception in TEXT.");
	}

	@GET
	@Path("throw.xml")
	@Produces("application/xml")
	public Object throwErrorXML() {
		throw new IllegalStateException("This action always throws an exception in XML.");
	}
}
