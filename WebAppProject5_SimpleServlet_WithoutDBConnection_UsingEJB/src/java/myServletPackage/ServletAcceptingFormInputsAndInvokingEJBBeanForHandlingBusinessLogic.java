
package myServletPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myEJBBeanPackage.AddNumbers;


public class ServletAcceptingFormInputsAndInvokingEJBBeanForHandlingBusinessLogic extends HttpServlet {
    
    @EJB                                // injects the EJB container
    AddNumbers addObj;                  // Instantiates the "AddNumbers" EJB bean
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter() ;
        
        //The FORM Inputs are retrieved here.
        int firstNumber = Integer.parseInt(request.getParameter("firstNum"));
        int secondNumber = Integer.parseInt(request.getParameter("secondNum"));
        
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        We are not doing any business logic operation in the servlet.
        The business logic is performed in the EJB bean and we are just using the EJB bean object("addObj") to invoke the add operation 
        For that you have to first set the EJB bean attributes with the respective FORM inputs through setter  method 
        and then fetch the add result(stored in the EJB bean attribute) through getter method.
        */
        
        //Setting the EJB bean attributes with the requested FORM inputs through setter  method
        addObj.setFirstNum(firstNumber);
        addObj.setSecondNum(secondNumber);
                
        //Invoking the actual business logic operation (add two numbers)
        addObj.addNumbers();
        
        //Fetching the add result from the EJB bean through getter method.
        int addResult = addObj.getAddResult();
        /////////////////////////////////////////////////////////////////////////////////////////////////////
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAcceptingFormInputsAndInvokingEJBBeanForHandlingBusinessLogic</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1>Servlet ServletAcceptingFormInputsAndInvokingEJBBeanForHandlingBusinessLogic at " + request.getContextPath() + "</h1>");
            
            out.println("<h2>The add result ="+addResult+ "</h2>");
            out.println("</body>");
            out.println("</html>");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
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
