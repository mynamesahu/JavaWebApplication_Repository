
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
    
    /** Declare the variables **/
        
          
        Logger logger = Logger.getLogger(ControllerServletInvokingEJBSessionBean.class );
        
        Customer custObj;
        
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
        String urlInsideDoGet = "/WEB-INF/view" + path + ".jsp";
        logger.info("urlInsideDoGet= "+urlInsideDoGet);
        try {
                request.getRequestDispatcher(urlInsideDoGet).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //processRequest(request, response);
        PrintWriter out = response.getWriter();  
        
        /* Configure the  LOG4J properties file */
        
            String prefix =  getServletContext().getRealPath("/");      // will return "D:\Sambit\NetBeansProjects\JavaWebApplication_Repository\WebAppProject3_SimpleServlet_WithDBConnection_UsingJDBC-SQLQuery\build\web"
            String file = getInitParameter("log4j-init-file");          // will return "WEB-INF/log4j.properties") 
            PropertyConfigurator.configure(prefix+file);                // properties file (log4j.properties) is configured to be read from the servlet

        /** Configure LOG4G properties file .. ENDS*/        
        
        //Capture the requested URL pattern
        String path= request.getServletPath();
        
        
        
        if (path.equals("/customerEditForm")){
        
            path = "/customerEditForm";
        
            logger.info("Inside doGet..inside if block .. customerEditForm");
            
            
            
            
            if (request.getParameter("custID_ForViewCustomer") != null){
                /*Note:-
                Before forwarding the request to customer edit form, 
                Pull the selected customer's details 
                    -customer's id passed as hidden field from the customer details page- (form submit, method=post),
                     On CLick of the EDIT button against a particulat customer*/

                /** Process to Pull the selected customer STARTS **/

                    // set the concerned property of Customer entity bean  with the id passed as a hidden field
                    String custID = request.getParameter("custID_ForViewCustomer");
                    logger.info("custID= "+custID);
                    custObj = new Customer();
                    custObj.setCustId(Integer.parseInt(custID));

                    logger.info("custObj.getCustId()= "+custObj.getCustId());

                    //Pull the customer's details into an object and put it in an application-scoped variable to be accessed later from the customer edit JSP page
                    try{
                        getServletContext().setAttribute("customerDetailsForSelectedCustomer",customerFacade.find(custObj.getCustId())); 
                        //getServletContext().setAttribute("customerDetailsForSelectedCustomer",customerFacade.find(2));
                    }
                    catch(Exception e){
                        logger.error(e.getMessage());
                    }

                /** The process of Fetch customer... ENDS **/


                /** Forward the request to the customer edit Page **/
                      
                    String url = "/WEB-INF/view" + path + ".jsp?customerEdited=no";
                    logger.info("INSIDE ....if ..custID_ForViewCustomer");
                    logger.info("cust view url= "+url);
                    try {
                        request.getRequestDispatcher(url).forward(request, response);
                    } 
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                /** Forward the request to the customer edit Page ... ENDS **/
            }
            else if (request.getParameter("custID_ForEditCustomer")!=null){
                    //Capture the edited User Inputs
                String custName = request.getParameter("custName");
                String custAddress= request.getParameter("custAddress");
                String custEmail= request.getParameter("custEmail");

                //Determine the customer which you want to modify
                custObj = customerFacade.find(custObj.getCustId());

                // set the concerned properties of Customer entity bean with the respective edited User Inputs
                custObj.setCustName(custName);
                custObj.setCustAddress(custAddress);
                custObj.setCustEmail(custEmail);
                
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
            
            /** Pull the List of all the customers ... END **/     
                
                
            /** Pull the List of all the customers from the customer entity and put the List in an application-scoped variable to be used later in the customer details Page and customer edit Page **/

                getServletContext().setAttribute("customerDetails",customerFacade.findAll()); 

            /** Pull the List of all the customers ... END **/ 



            /** Forward the request back to customer entry form **/
                String url = "/WEB-INF/view" + path + ".jsp?customerEdited=yes";
                logger.info("INSIDE ....if ..custID_ForEditCustomer");
                logger.info("cust edit  url= "+url);
                try {
                        request.getRequestDispatcher(url).forward(request, response);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

            /** Forward the request back to customer entry form ... END **/  
                
            }// else if (request.getParameter("custID_ForEditCustomer")!=null) ... ENDS   
                
         
        }// END ... if (path.equals("/customerEditForm"))
        
        
        
        /*Note:-
            Add a new customer and forward the request back to the customer entry Page.
            Before forwaring the request back to customer entry Page, just pull the List of all the customers from customer entity and put the List in an application-scoped variable that can be used later in the customer details Page
        */
        
        /** Process to add the new customer - User Inputs entered in customer entry Form **/
        
        if (path.equals("/customerForm")){
        
            path = "/customerEntryForm";
            
            //Capture the User Inputs
            String custName = request.getParameter("custName");
            String custAddress= request.getParameter("custAddress");
            

            // set the concerned properties of Customer entity bean with the respective User Inputs
            custObj = new Customer();
            custObj.setCustName(custName);
            custObj.setCustAddress(custAddress);
            custObj.setCustEmail(custName+"@gmail.com");

            // Insert a new customer record in customer entity
            try{
            customerFacade.create(custObj);             
            }
            catch(Exception e){
                logger.error(e.getMessage());
            }
            
        /** Process to Add the new customer ... END **/ 

            
            
        /** Pull the List of all the customers from the customer entity and put the List in an application-scoped variable to be used later in the customer details Page **/
            
            getServletContext().setAttribute("customerDetails",customerFacade.findAll()); 
            
        /** Pull the List of all the customers ... END **/ 

            
            
        /** Forward the request back to customer entry form **/
            String url = "/WEB-INF/view" + path + ".jsp?customerInserted=yes";
            //String url = "/WEB-INF/view/customerEntryForm.jsp?customerInserted=yes";
            logger.info("second URL inside doPost = "+url);
            try {
                    request.getRequestDispatcher(url).forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            
        /** Forward the request back to customer entry form ... END **/    
           
        }
        
    
    //@Override
    //public String getServletInfo() {
        //return "Short description";
    //}

    }
}
