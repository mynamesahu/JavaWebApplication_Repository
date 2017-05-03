<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h3>Click this <a href="http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerForm">link </a> to Go Back to Customer Entry Form</h3>
        
        <h1>Customer Details</h1>
        
        <!--Type 'jstl'  -> Ctrl-Space  ->  choose "JSTL For Each" -> 
            Collection:  dollar-sign{customerDetails}   //Resultset List of all customer records (of the "Customer" entity class)stored in an application scoped variable "customerDetails" defined in the controller servlet's context parameter 
            Current Item of the Iteration:  customer    //customer object from which attributes can be derived
        -->   
        <c:forEach var="customer" items="${customerDetails}">    
            <div>
                <c:out value="${customer.custName}" /> 
            </div>
        </c:forEach>
    </body>
    
</html>
