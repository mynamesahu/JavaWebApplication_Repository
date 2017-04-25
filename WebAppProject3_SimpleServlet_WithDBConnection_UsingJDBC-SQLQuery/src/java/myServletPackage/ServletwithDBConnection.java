/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServletPackage;

// Loading required libraries
import java.io.*;
//import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/**
 *
 * @author Sambit
 */
public class ServletwithDBConnection extends HttpServlet {
    
    // ---------JDBC driver name and database URL for MYSQL dabase server-------------------
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/mytestschema";
    //---------------------------------------------------------------------------------------
    
    //  Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "nbuser";

    

    /* --------JDBC driver name and database URL for Oracle database server------------------*/
    //static final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
    //static final String DB_URL="jdbc:oracle:thin:@localhost:1521:mytestschema";
    
    //static final String USERNAME = "system";
    //static final String PASSWORD = "oracle";
    /*---------------------------------------------------------------------------------------*/    

    
        

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        Connection conn =null;
        //Statement stmt = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String title = "Database Result";
        String docType =
          "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
        out.println(    docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor=\"#f0f0f0\">\n" +
                        "<h1 align=\"center\">" + title + "</h1>\n"
                     );
            try{
                Class.forName(JDBC_DRIVER);                                     // Register JDBC driver
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // Open a connection
                
                sql = "SELECT * FROM customer";
                
                //stmt = conn.createStatement();                                // create a Statement object                             
                //rs = stmt.executeQuery(sql);                                  // Execute SQL query through Statement object
                
                ps = conn.prepareStatement(sql);                                // create a PreparedStatement object
                rs = ps.executeQuery();                                         // Execute SQL query through PreparedStatement object

                // Extract data from result set
                while(rs.next()){
                   
                    //Retrieve by column name
                    int id  = rs.getInt("cust_id");
                    String custName = rs.getString("cust_name");
                  
                    //Display values
                    out.println(" Customer ID: " + id + "<br>");
                    out.println(" Customer Name: " + custName + "<br>");
                    out.println("<br>");
                }
                out.println("</body></html>");

                // Clean-up environment
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
            
    }// end of doGet()
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
