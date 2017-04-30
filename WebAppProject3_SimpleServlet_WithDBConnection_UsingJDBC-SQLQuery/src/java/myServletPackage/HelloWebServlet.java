/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServletPackage;

import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class HelloWebServlet extends HttpServlet {
    
    final static Logger logger = Logger.getLogger(HelloWebServlet.class);
    
    

    InitialContext ctx = null;
    DataSource ds = null;
    Connection conn = null;
    //Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String sql = "";
    String customerName = null;
    

    public void init () throws ServletException {
        
        /* This code is for logging using LOG4J properties file */
        String prefix =  getServletContext().getRealPath("/");      // will return "D:\Sambit\NetBeansProjects\JavaWebApplication_Repository\WebAppProject3_SimpleServlet_WithDBConnection_UsingJDBC-SQLQuery\build\web"
        String file = getInitParameter("log4j-init-file");          // will return "WEB-INF/log4j.properties") 
        PropertyConfigurator.configure(prefix+file);                // properties file (log4j.properties) is configured to be read from the servlet
        /* This code is for logging using LOG4J properties file ....END*/
        }

    public void destroy () {
        /*try {
            if (rs != null)
              rs.close();
            if (ps != null)
              ps.close();
            if (conn != null)
              conn.close();
            if (ctx != null)
              ctx.close();
        }
        catch (SQLException se) {
            System.out.println("SQLException: "+se.getMessage());
        }
        catch (NamingException ne) {
            System.out.println("NamingException: "+ne.getMessage());
        }*/
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        /*try {
          String customer_name = req.getParameter("customer_name");
          resp.setContentType("text/html");
          PrintWriter writer = resp.getWriter();
          writer.println("<html><body>");

          writer.println("<p>Customer: "+customer_name+"</p>");

          ps.setString(1, customer_name);
          rs = ps.executeQuery();
          if (!rs.next()){
            writer.println("<p>Country does not exist!</p>");
          }
          else {
            rs.beforeFirst();
            while(rs.next()) {
              writer.println("<p>Name: "+rs.getString("cust_name")+"</p>");
              writer.println("<p>Population: "+rs.getString("cust_addr")+"</p>");
            }
          }
          writer.println("</body></html>");
          writer.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }//end try...catch*/
  
    }//end doPost()

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException{
        try {
             
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>");
            
            //sql = "SELECT * FROM customer";
            sql = "SELECT cust_name, cust_address FROM customer WHERE cust_name=?";
    
            customerName = req.getParameter("customerName");
            writer.println("<p>Customer: "+customerName+"</p>");
            logger.info("Information Log :customer name = "+ customerName);
            logger.debug("Debugged Log");
            
            
                       
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/myTestDataSource");              // Lookup for the Datasource that points to "mytestschema" database
            conn = ds.getConnection();                                          // Open a connection
            
            //stmt = conn.createStatement();                                    // Create a Statement object
            ps = conn.prepareStatement(sql);                                    // Create a PreparedStatement object                                  
            ps.setString(1, customerName);                                    // Set the input parameter in the Prepared statement
            //ps.setString(1, "Hari"); 
            
            //rs = stmt.executeQuery(sql);                                      // Execute the query through Statement
            rs = ps.executeQuery();                                             // Execute the query through PreparesStatement
            
                if (!rs.next()){
                  writer.println("<p>Customer does not exist!</p>");
                }
                else {
                    rs.beforeFirst();
                    while(rs.next()) {
                      writer.println("<p>Row number: "+rs.getRow()+"</p>");
                      writer.println("<p>Customer Name: "+rs.getString("cust_name")+"</p>");
                      writer.println("<p>Customer Address: "+rs.getString("cust_address")+"</p>");
                    }
                }
                    
            writer.println("</body></html>");
            
            rs.close();
            //stmt.close();
            ps.close();
            conn.close();
            }catch(SQLException sqle1){     // End of try block           
                sqle1.printStackTrace();
                //logger.debug("Debug SQLExeception message : "+ sqle1.getMessage());
            }catch(Exception e){
                e.printStackTrace();
                logger.debug("Debug Exception message : "+ e.getMessage());
            }
            
    }//End doGet()
  
  
}//End servlet class

