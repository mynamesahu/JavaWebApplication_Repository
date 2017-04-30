
package myServletPackage;

/*Required libraries*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ServletwithDBConnection extends HttpServlet {
    
    final static Logger logger = Logger.getLogger(ServletwithDBConnection.class);
    
    
    // ---------LOG4JDBC driver name and database URL for MYSQL dabase server-------------------
    static final String JDBC_DRIVER="net.sf.log4jdbc.DriverSpy";
    static final String DB_URL="jdbc:log4jdbc:mysql://localhost:3306/mytestschema";
    static final String USERNAME = "root";
    static final String PASSWORD = "nbuser";
    //--------------------------------------------------------------------------------------
    
    // ---------JDBC driver name and database URL for MYSQL dabase server-------------------
    //static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    //static final String DB_URL="jdbc:mysql://localhost:3306/mytestschema";
    //static final String USERNAME = "root";
    //static final String PASSWORD = "nbuser";
    //---------------------------------------------------------------------------------------
    
    
    /* --------JDBC driver name and database URL for Oracle database server------------------*/
    //static final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
    //static final String DB_URL="jdbc:oracle:thin:@localhost:1521:mytestschema";
    //static final String USERNAME = "system";
    //static final String PASSWORD = "oracle";
    /*---------------------------------------------------------------------------------------*/    

    
    Connection conn =null;
    //Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
    String customerID = null;

        
    public void init () throws ServletException {
        
        /* This code is for logging using LOG4J properties file */
        String prefix =  getServletContext().getRealPath("/");      // will return "D:\Sambit\NetBeansProjects\JavaWebApplication_Repository\WebAppProject3_SimpleServlet_WithDBConnection_UsingJDBC-SQLQuery\build\web"
        String file = getInitParameter("log4j-init-file");          // will return "WEB-INF/log4j.properties") 
        PropertyConfigurator.configure(prefix+file);                // properties file (log4j.properties) is configured to be read from the servlet
        logger.info("properties file path = "+prefix+file);
        /* This code is for logging using LOG4J properties file ....END*/
    }    

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        

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

            //sql = "SELECT * FROM customer";
            sql = "SELECT cust_id, cust_name, cust_address FROM customer WHERE cust_id=?";
            customerID = request.getParameter("customerID");
            logger.info("Information Log"+customerID);

            //stmt = conn.createStatement();                                // create a Statement object                             
            //rs = stmt.executeQuery(sql);                                  // Execute SQL query through Statement object

            ps = conn.prepareStatement(sql);                                // create a PreparedStatement object
            ps.setString(1, customerID); 

            rs = ps.executeQuery();                                         // Execute SQL query through PreparedStatement object

            rs.beforeFirst();
            // Extract data from result set
            while(rs.next()){

                //Retrieve by column name
                int custId  = rs.getInt("cust_id");
                String custName = rs.getString("cust_name");
                String custAddress = rs.getString("cust_address");

                //Display values
                out.println(" Customer ID: " + custId + "<br>");
                out.println(" Customer Name: " + custName + "<br>");
                out.println(" Customer Address: " + custAddress + "<br>");
                out.println("<br>");
            }// End of while

            out.println("</body></html>");

            // Clean-up environment
            rs.close();
            //stmt.close();
            ps.close();
            conn.close();

        }catch(SQLException sqle1){    // End of try block            
            sqle1.printStackTrace();
            logger.debug("Debug SQLExeception message : "+ sqle1.getMessage());
        }catch(Exception e){
            e.printStackTrace();
            logger.debug("Debug Exception message : "+ e.getMessage());
        }
            
    }// End of doGet()
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
