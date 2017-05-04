<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html>
    
    <head>
        <title>Input the Customers</title>
    </head>
    
    <body>
        <c:set var="customerInserted" value="${param.customerInserted}" />
        
        <c:if test="${!empty customerInserted}">
            <b> <c:out value="customer inserted successfully" /> </b>
        </c:if>
        <br>
        <h1>Click this <a href="http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerDetails">link </a> to View customer details</h1>
        <br>
        <b>Enter Customer Details:</b> <br>
        <form name="frm_InputNumbers" action="customerForm" method="POST">
            <input type="text" name="custName"><br>
            <input type="text" name="custAddress"><br>
            <input type="submit">
        </form>
    </body>
    
</html>
