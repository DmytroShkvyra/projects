/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jettyembededtest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class HelloServlet extends HttpServlet{
    
    private String name;

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html");
                response.getWriter().println("<h1>Hello</h1>");
                response.getWriter().println("<h1>target = "+name+"</h1>");
    }

    public HelloServlet() {
    }

    public HelloServlet(String name) {
        this.name = name;
    }
    
    
}
