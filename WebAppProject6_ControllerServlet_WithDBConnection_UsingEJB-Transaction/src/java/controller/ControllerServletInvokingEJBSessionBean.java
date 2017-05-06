
package controller;

import entityBeans.Customer;
import java.io.*;
import java.io.PrintWriter;
import java.sql.*;
//import java.sql.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import sessionBeans.CustomerFacade;


public class ControllerServletInvokingEJBSessionBean extends HttpServlet {
    
    /** Declare the variables **/
        String path = "";
        String url = "";
        String prefix = "";
        String file = "";
        String custID = "";
        String custName = "";
        String custAddress = "";
        String custEmail = "";
        String custDOB = "";
        String custDOR = "";
        Customer custObj;
        DateFormat dateFormat;
        Date date;
        PrintWriter out;
        Logger logger = Logger.getLogger(ControllerServletInvokingEJBSessionBean.class );
    /** Declare the variables ... END**/
    
    @EJB                                    // injects the EJB
    private CustomerFacade customerFacade; // Instantiate the EJB session bean
    
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
        
    }//init() method ... END
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
              
        path= request.getServletPath();
        if (path.equals("/welcome"))
        {
            path = "/welcome";
        }
        else if (path.equals("/customerDetails")) 
        {
            path = "/customerDetails";
        }
        else if (path.equals("/customerForm")) 
        {
            logger.info("inside if block .. customerForm");
            path = "/customerEntryForm";
        }
        
        // use RequestDispatcher to forward request internally
        url = "/WEB-INF/view" + path + ".jsp";
        try {
                request.getRequestDispatcher(url).forward(request, response);
        } 
        catch (Exception ex) {
                ex.printStackTrace();
        }
    }//doGet() method ... END

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
               
        //processRequest(request, response);
        out = response.getWriter();  
        
        /* Configure the  LOG4J properties file */
        
            //prefix =  getServletContext().getRealPath("/");      // will return "D:\Sambit\NetBeansProjects\JavaWebApplication_Repository\WebAppProject3_SimpleServlet_WithDBConnection_UsingJDBC-SQLQuery\build\web"
            //file = getInitParameter("log4j-init-file");          // will return "WEB-INF/log4j.properties") 
            //PropertyConfigurator.configure(prefix+file);         // properties file (log4j.properties) is configured to be read from the servlet

            //logger.info("Properties file Path = "+prefix+file);
        /** Configure LOG4G properties file .. ENDS*/        
        
        
        
            
            
            
            /* Configure the  LOG4J.xml file */
        
            prefix =  getServletContext().getRealPath("/");      // will return "D:\Sambit\NetBeansProjects\JavaWebApplication_Repository\WebAppProject3_SimpleServlet_WithDBConnection_UsingJDBC-SQLQuery\build\web"
            file = getInitParameter("log4j-init-xml");          // will return "WEB-INF/log4j.properties") 
            DOMConfigurator.configure(prefix+file);                // properties file (log4j.properties) is configured to be read from the servlet
            logger.info("log4j.xml file Path = "+prefix+file);
            /** Configure LOG4G properties file .. ENDS*/     
            
            
        //DOMConfigurator.configure("log4j.xml");

        
       
        //Capture the requested URL pattern
            path= request.getServletPath();
        
        logger.info("Requested URL pattern Path = "+path);
        
        if(path.equals("/customerEditForm")){
        
            path = "/customerEditForm";
        
            
            
            /**
              1. customer edit form - just to display the selected customer 
            **/
            if(request.getParameter("custID_ForViewCustomer") != null){
                /*Note:-
                Before forwarding the request to customer edit form, Pull the selected customer's details 
                    -customer's id passed as hidden field from the customer-details page's EDIT button On CLick against a particulat customer- (form submit, method=post),
                */

                /** Process to Pull the selected customer STARTS **/

                    // set the concerned property of Customer entity bean  with the id passed as a hidden field
                    custID = request.getParameter("custID_ForViewCustomer");
                    
                    custObj = new Customer();
                    custObj.setCustId(Integer.parseInt(custID));

                    //Pull the customer's details into an object and put it in an application-scoped variable to be accessed later from the customer edit JSP page
                    try{
                        getServletContext().setAttribute("customerDetailsForSelectedCustomer",customerFacade.find(custObj.getCustId())); 
                    }
                    catch(Exception e){
                        logger.error(e.getMessage());
                    }

                /** The process to pull the selected customer... ENDS **/


                /** Forward the request to the customer edit Page **/
                      
                    url = "/WEB-INF/view" + path + ".jsp?customerEdited=no";
                    try {
                        request.getRequestDispatcher(url).forward(request, response);
                    } 
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                /** Forward the request to the customer edit Page ... ENDS **/
            }
            /**
              2. customer edit form - to Modify the selected customer 
            **/
            else if (request.getParameter("custID_ForEditCustomer")!=null){
                
                //Capture the edited User Inputs
                    custName = request.getParameter("custName");
                    custAddress= request.getParameter("custAddress");
                    custEmail= request.getParameter("custEmail");
                    custDOB= request.getParameter("custDOB");
                    custDOR= request.getParameter("custDOR");
                    
                    logger.info("custDOB= "+custDOB);
                    logger.info("custDOR= "+custDOR);

                //Determine the customer which you want to modify
                    custObj = customerFacade.find(custObj.getCustId());

                // set the concerned properties of Customer entity bean with the respective edited User Inputs
                    custObj.setCustName(custName);
                    custObj.setCustAddress(custAddress);
                    custObj.setCustEmail(custEmail);
                    custObj.setCustDOB(custDOB);
                    custObj.setCustDOR(custDOR);
                    
                    logger.info("custObj.getCustDOB() = "+custObj.getCustDOB());
                    logger.info("custObj.getCustDOR() = "+custObj.getCustDOR());
                
                // Insert a new customer record in customer entity
                    try{
                        customerFacade.edit(custObj);             
                    }
                    catch(Exception e){
                        logger.error(e.getMessage());
                    }

            /** Process to Add the new customer ... END **/ 

            /** Pull the edited  customer data from the customer entity and put the List in an application-scoped variable to be used later in the customer edit Page **/
                
                getServletContext().setAttribute("editedCustomerDetails",custObj); 
            
            /** Pull the edited customer ... END **/     
                
                
            /** Pull the List of all the customers from the customer entity and put the List in an application-scoped variable to be used later in the customer details Page and customer edit Page **/

                getServletContext().setAttribute("customerDetails",customerFacade.findAll()); 

            /** Pull the List of all the customers ... END **/ 



            /** Forward the request back to customer edit Page **/
                String url = "/WEB-INF/view" + path + ".jsp?customerEdited=yes";
                
                try {
                    request.getRequestDispatcher(url).forward(request, response);
                } 
                catch (Exception ex) {
                    ex.printStackTrace();
                }

            /** Forward the request back to customer edit Page ... END **/  
                
            }// else if (request.getParameter("custID_ForEditCustomer")!=null) ... ENDS   
             
            /**
              3. customer edit form - to delete the selected customer 
            **/
            else if (request.getParameter("custID_ForDeleteCustomer")!=null){
                //Capture the edited User Inputs
                    custName = request.getParameter("custName");
                    custAddress= request.getParameter("custAddress");
                    custEmail= request.getParameter("custEmail");
                    custDOB= request.getParameter("custDOB");
                    custDOR= request.getParameter("custDOR");

                //Determine the customer which you want to delete
                    custObj = customerFacade.find(custObj.getCustId());
                    
                    if (custObj != null){
                    
                    // set the concerned properties of Customer entity bean with the respective edited User Inputs
                        custObj.setCustName(custName);
                        custObj.setCustAddress(custAddress);
                        custObj.setCustEmail(custEmail);
                        custObj.setCustDOB(custDOB);
                        custObj.setCustDOR(custDOR);

                    // Insert a new customer record in customer entity
                        try{
                        customerFacade.remove(custObj);             
                        }
                        catch(Exception e){
                            logger.error(e.getMessage());
                        }

                /** Process to Add the new customer ... END **/ 


                /** Pull the deleted  customer data from the customer entity and put the List in an application-scoped variable to be used later in the customer edit Page **/

                    getServletContext().setAttribute("deletedCustomerDetails",custObj); 

                /** Pull the deleted customer ... END **/         

                /** Pull the List of all the customers from the customer entity and put the List in an application-scoped variable to be used later in the customer details Page and customer edit Page **/

                    getServletContext().setAttribute("customerDetails",customerFacade.findAll()); 

                /** Pull the List of all the customers ... END **/ 



                /** Forward the request back to customer edit Page **/
                    url = "/WEB-INF/view" + path + ".jsp?customerEdited=deleted";

                    try {
                        request.getRequestDispatcher(url).forward(request, response);
                    } 
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                /** Forward the request back to customer entry form ... END **/  
                }
                else{ // if (custObj == null) => if the customer is already deleted
                    /** Forward the request back to customer edit Page **/
                    url = "/WEB-INF/view" + path + ".jsp?customerEdited=customerDoesntExistToBeDeleted";

                    try {
                        request.getRequestDispatcher(url).forward(request, response);
                    } 
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    /** Forward the request back to customer entry form ... END **/  
                }
                    
            }// else if (request.getParameter("custID_ForDeleteCustomer")!=null) ... ENDS   
        
        }// END ... if (path.equals("/customerEditForm"))
        
       
        
        
        
        /*Note:-
            Add a new customer and forward the request back to the customer entry Page.
            Before forwaring the request back to customer entry Page, just pull the List of all the customers from customer entity and put the List in an application-scoped variable that can be used later in the customer details Page
        */
        
        /** Process to add the new customer - User Inputs entered in customer entry Form **/
        
        if (path.equals("/customerForm")){
        
            path = "/customerEntryForm";
            
            //Capture the User Inputs
                custName = request.getParameter("custName");
                custAddress= request.getParameter("custAddress");
                custEmail= request.getParameter("custEmail");
                custDOB= request.getParameter("custDOB");
                
            // set the customer's DateofRegistration as current date-time and then assign it to a variable.
                
                /** cust_DateOfRegistration is of data type - DATETIME. It will accept only yyyy-MM-dd format. 
                    So you have to first convert the current date time to this format before inserting in to customer TABLE **/
                     
                date = new Date();  // current date
                custDOR =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                
                
            // set the concerned properties of Customer entity bean with the respective User Inputs
                custObj = new Customer();
                custObj.setCustName(custName);
                custObj.setCustAddress(custAddress);
                custObj.setCustEmail(custEmail);
                custObj.setCustDOB(custDOB);
                custObj.setCustDOR(custDOR);
                
            // Insert a new customer record in customer entity
                try{
                customerFacade.create(custObj);             
                }
                catch(Exception e){
                    logger.info(e.getMessage());
                    logger.error(e.getMessage());
                }
            
        /** Process to Add the new customer ... END **/ 
            
            
        /** Pull the List of all the customers from the customer entity and put the List in an application-scoped variable to be used later in the customer details Page **/
            
            getServletContext().setAttribute("customerDetails",customerFacade.findAll()); 
            
        /** Pull the List of all the customers ... END **/ 
            
            
        /** Forward the request back to customer entry form **/
            url = "/WEB-INF/view" + path + ".jsp?customerInserted=yes";
            
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
            
        /** Forward the request back to customer entry form ... END **/    
           
        }
    
    }//doPost() method ... END
    
}// serv;et class ... END
