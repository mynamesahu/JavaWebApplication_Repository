<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : customerDetail
    Created on : Mar 26, 2017, 12:32:19 PM
    Author     : Sambit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Customer Details</h1>
        
        
        
        <!--Type 'jstl'  -> Ctrl-Space  ->  choose "JSTL For Each" -> 
            Collection:  dollar-sign{customerDetails}   //Resultset List of all customer records (of the "Customer" entity class)stored in an application scoped variable "customerDetails" defined in the controller servlet's context parameter 
            Current Item of the Iteration:  customer    //customer object from which attributes can be derived
            
            customers
        -->   
    
    <c:forEach var="customer" items="${customerDetails}">    
        <div>
            <c:out value="${customer.custName}" /> 
        </div>
    </c:forEach>
    
        
    </body>
</html>
