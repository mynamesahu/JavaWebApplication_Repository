<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html>
    
    <head>
        <title>Edit Customer</title>
    </head>
    
    <body>
        <%-- The customer detail for the selected customer is pulled from customer entity and captured in  an application-scoped variable - customerDetailsForSelectedCustomer --%>
               
        <c:set var="customer" value="${customerDetailsForSelectedCustomer}" />
        
        <h1>Click this <a href="http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerDetails">link </a> to View customer details</h1>
        <br>
        <b>Modify Customer Details:</b> <br>
        
        <form name="frm_ModifyCustomer" action="welcome" method="POST">
            Name : <input type="text" name="custName" value="${customer.custName}"><br>
            Address : <input type="text" name="custAddress" value="${customer.custAddress}"><br>
            Email : <input type="text" name="custEmail" value="${customer.custEmail}"><br>
            <input type="submit" value="Submit the Changes">
        </form>
        
    </body>
    
</html>
