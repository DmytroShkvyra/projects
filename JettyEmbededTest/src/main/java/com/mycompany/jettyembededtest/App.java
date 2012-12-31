package com.mycompany.jettyembededtest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HandlerContainer;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandlerContainer;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;




/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        
        Server server = new Server(8080);
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new HelloServlet()),"/*");  
        context.addServlet(new ServletHolder(new HelloServlet("One")),"/1"); 
        context.addServlet(new ServletHolder(new HelloServlet("Two")),"/2");

        /*ContextHandler context = new ContextHandler();
        context.setContextPath("/test");
        context.setResourceBase(".");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(context);
        context.setHandler(new HelloHandler("First"));
        context.setHandler(new HelloHandler("Second"));*/
        
        server.start();
        server.join();
        
        /*Handler handler = new AbstractHandler() {
            public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
                    throws IOException, ServletException {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("<h1>Hello</h1>");
                response.getWriter().println("<h1>target = "+target+"</h1>");
                ((Request) request).setHandled(true);
            }

            public void handle(String target, Request request, HttpServletRequest hsr, HttpServletResponse response) throws IOException, ServletException {
                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("<h1>Hello!</h1>");
                response.getWriter().println("<h1>target = "+target+"</h1>");
                ((Request) request).setHandled(true);
            }

        };

        Server server = new Server(8080);
        server.setHandler(handler);
        server.setStopAtShutdown(true);
        server.start();*/
    }
}
