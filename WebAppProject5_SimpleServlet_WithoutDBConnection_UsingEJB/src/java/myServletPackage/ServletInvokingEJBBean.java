
package myServletPackage;

import myEJBBeanPackage.GreetCustomer;


import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletInvokingEJBBean extends HttpServlet {
    
    
    /*
    In the scenario of distributed system, the servlet is in a server situated in one location and the java class object / a simple bean might be in a machine in some other location)
    In this scenario, the servlet can't invoke the simple java class "GreetCustomer".
    So we have to handle this issue by creating EJB bean.
    Since the EJB bean is created inside the EJB container, 
    it can be accessed from a servlet (the server at any location) by invoking the EJB bean after injecting the EJB 
    */
    
    @EJB                            //Inject the EJB 
    GreetCustomer greetCust;             //Instantiate the EJB session bean "GreetCustomer"
    
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
            
            //Invoke the bean 
            //out.println("<h2> EJB session bean invoked here ....."+ bean.sayHello("Sambit")+ "</h2>");
            out.println("<h2> EJB session bean invoked here and the business logic applied ....."+ greetCust.sayHello("Sambit")+ "</h2>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
