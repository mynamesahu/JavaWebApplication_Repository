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


public class HelloWebServlet extends HttpServlet {

    InitialContext ctx = null;
    DataSource ds = null;
    Connection conn = null;
    //Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    

    String sql = "SELECT cust_name, cust_address FROM customer WHERE cust_name=?";
    //String sql = "SELECT * FROM customer";

    public void init () throws ServletException {
        /*try {
            ctx = new InitialContext();
            //ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MySQLDataSource");
            ds = (DataSource) ctx.lookup("jdbc/myTestDataSource");
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            //stmt = conn.createStatement();
        }
        catch (SQLException se) {
            System.out.println("SQLException: "+se.getMessage());
        }
        catch (NamingException ne) {
            System.out.println("NamingException: "+ne.getMessage());
        }*/
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
                        
            String customer_name = req.getParameter("customer_name");
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>");

            writer.println("<p>Customer: "+customer_name+"</p>");
            
            //System.out.println("Prepared Statement before bind variables set:\n\t" + ps.toString());
            //ps.setString(1, customer_name);
            //System.out.println("Prepared Statement after bind variables set:\n\t" + ps.toString());
            
            //System.out.println(((JDBC4PreparedStatement)ps).asSql());
            
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/myTestDataSource");              // Lookup for the Datasource that points to "mytestschema" database
            conn = ds.getConnection();                                          // Open a connection
            
            //stmt = conn.createStatement();                                    // Create a Statement object
            ps = conn.prepareStatement(sql);                                    // Create a PreparedStatement object                                  
            
            ps.setString(1, req.getParameter("customer_name"));               // Set the input parameter in the Prepared statement
            //System.out.println("Prepared Statement after bind variables set:\n\t" + ps.toString());
            
            
            //rs = stmt.executeQuery(sql);                                      // Execute the query through Statement
            rs = ps.executeQuery();                                             // Execute the query through PreparesStatement
            
            
            //writer.println("<p>rs.next(): "+rs.next()+"</p>");
            
                /*if (!rs.next()){
                  writer.println("<p>Customer does not exist!</p>");
                }
                else {*/
                    rs.beforeFirst();
                    while(rs.next()) {
                      writer.println("<p>Row number: "+rs.getRow()+"</p>");
                      writer.println("<p>Customer Name: "+rs.getString("cust_name")+"</p>");
                      writer.println("<p>Customer Address: "+rs.getString("cust_address")+"</p>");
                    }
            
                /*while(rs.next()){
                   
                    //Retrieve by column name
                    int id  = rs.getInt("cust_id");
                    String custName = rs.getString("cust_name");
                    String custAddress = rs.getString("cust_address");
                  
                    //Display values
                    writer.println(" Customer ID: " + id + "<br>");
                    writer.println(" Customer Name: " + custName + "<br>");
                    writer.println(" Customer Name: " + custAddress + "<br>");
                    writer.println("<br>");
                //}*/
                //}
            writer.println("</body></html>");
            //writer.close();
            rs.close();
            //stmt.close();
            ps.close();
            conn.close();
        }catch(SQLException se){                
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{                                   // end try .... catch
            //finally block used to close resources
            try{
                /*if(stmt!=null)
                    stmt.close();*/
                if(ps!=null)
                    ps.close();
            }catch(SQLException se1){
                    // nothing we can do
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
        }//end finally
            
    }//end doGet()
  
  
}

