<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html>
    
    <head>
        <title>Input the Customers</title>
        
        <style type="text/css">
                    .succssfulMessage{
                        color: green;
                    }
                    
                    .warningMessage{
                        color: red;
                    }
        </style>
    </head>
    
    <body>
        
        <h2>Click this <a href="http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerDetails">link </a> to View customer details</h1>
        
        <br>
        
        <c:set var="customerInserted" value="${param.customerInserted}" />
        
        <c:if test="${!empty customerInserted}">
            <div class="succssfulMessage"><b> <c:out value="customer inserted successfully" /> </b></div>
        </c:if>
        <br>
        
        <b>Enter Customer Details:</b> <br>
        <form name="frm_InputNumbers" action="customerForm" method="POST">
            Name: <input type="text" name="custName"><br>
            Address: <input type="text" name="custAddress"><br>
            Email: <input type="text" name="custEmail"><br>
            Date Of Birth: <input type="text" name="custDOB"><br>
            
            <input type="submit">
        </form>
    </body>
    
</html>
