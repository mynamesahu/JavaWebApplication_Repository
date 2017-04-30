/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServletPackage;

import mySimpleClassPackage.GreetCustomer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletInvokingSimpleJavaClass extends HttpServlet {

    GreetCustomer greetCust = new GreetCustomer();          // instantiate the Simple Java class
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServletForEJB</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Servlet context path --> " + request.getContextPath() + "</h1>");
            out.println("<h1>Servlet path -->           " + request.getServletPath() + "</h1>");
            
            out.println("<h2> Simple Java Class object's sayHello() method invoked here ....."+ greetCust.sayHello("Sambit")+ "</h2>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
}
