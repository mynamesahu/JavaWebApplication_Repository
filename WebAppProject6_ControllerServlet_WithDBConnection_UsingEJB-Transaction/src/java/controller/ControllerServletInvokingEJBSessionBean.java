/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionBeans.CustomerFacade;

/**
 *
 * @author Sambit
 */
public class ControllerServletInvokingEJBSessionBean extends HttpServlet {
    
    @EJB        // injects the EJB
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
        
        //response.setContentType("text/html;charset=UTF-8");
        //try (PrintWriter out = response.getWriter()) {
            //PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServletInvokingEJBSessionBean</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServletInvokingEJBSessionBean at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
        //}
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
        String path= request.getServletPath();
        if (path.equals("/welcome"))
        {
            path = "/welcome";
        }
        else if (path.equals("/customer")) 
        {
            path = "/customerDetails";
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + path + ".jsp";
        try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         
         
    }

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
        processRequest(request, response);
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
