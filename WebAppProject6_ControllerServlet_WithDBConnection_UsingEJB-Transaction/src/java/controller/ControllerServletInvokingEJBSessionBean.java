
package controller;

import entityBeans.Customer;
import java.io.*;
import java.io.PrintWriter;
import java.sql.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sessionBeans.CustomerFacade;


public class ControllerServletInvokingEJBSessionBean extends HttpServlet {
    
    Logger logger = Logger.getLogger(ControllerServletInvokingEJBSessionBean.class );
    
    
    @EJB                                    // injects the EJB
    private CustomerFacade customerFacade; // Instantiate the session bean
    
    @Override
    public void init() throws ServletException {
        /*
        query the database for all records of "Customer" entity by using findAll() functionality of EntityManager 
        through invoking the "CustomerFacade" session bean
        and stores the Resultset (List of Customer objects) in a variable "customerDetails" 
        which is placed in the ServletContext (=> application-scoped variable) 
        that can be accessed by any JSP page 
        */
        getServletContext().setAttribute("customerDetails",customerFacade.findAll()); 
    }
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
               
        String path= request.getServletPath();
        if (path.equals("/welcome"))
        {
            path = "/welcome";
        }
        else if (path.equals("/customer")) 
        {
            path = "/customerDetails";
        }
        else if (path.equals("/customerForm")) 
        {
            path = "/customerEntryForm";
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + path + ".jsp";
        try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        
        /* This code is for logging using LOG4J properties file */
        String prefix =  getServletContext().getRealPath("/");      // will return "D:\Sambit\NetBeansProjects\JavaWebApplication_Repository\WebAppProject3_SimpleServlet_WithDBConnection_UsingJDBC-SQLQuery\build\web"
        String file = getInitParameter("log4j-init-file");          // will return "WEB-INF/log4j.properties") 
        PropertyConfigurator.configure(prefix+file);                // properties file (log4j.properties) is configured to be read from the servlet
        out.println("properties file path = "+prefix+file);
        logger.info("properties file path = "+prefix+file);
        /* This code is for logging using LOG4J properties file ....END*/
        
        
        String custName = request.getParameter("custName");
        String custAddress= request.getParameter("custAddress");
        
        
        Customer custObj = new Customer();
        
        // set the value of Customer entity bean properties with the User Input
        custObj.setCustName(custName);
        custObj.setCustAddress(custAddress);
        //custObj.setCustEmail("customer@gmail.com");
        custObj.setCustEmail(custName+"@gmail.com");
        
        try{
        customerFacade.create(custObj);             // Insert a new customer record in customer entity
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        //catch(SQLException sqle){
            //logger.info(sqle.getMessage());
        //}
        
        
        
        //After insert of new record,  put the entire list of customers in an application-scoped variable
        getServletContext().setAttribute("customerDetails",customerFacade.findAll()); 
        
        
        // Use RequestDispatcher to forward the request back to customer entry form
        String url = "/WEB-INF/view/customerEntryForm.jsp?customerInserted=yes";
        try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
