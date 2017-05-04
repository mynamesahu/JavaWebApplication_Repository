<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html>
    
    <head>
        <title>Edit Customer</title>
        <script language="javascript">
            function editRecord(id){
                document.myform.action='http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerEditForm?custID_ForEditCustomer='+id;
                document.myform.submit();
            }
        </script>
            
    </head>
    
    <body>
       
        <c:set var="customerEdited" value="${param.customerEdited}" />
        
        <%--
        <c:if test="${!empty customerEdited}">
        --%>
        
        <c:if test="${customerEdited.equals('no')}">
            <b> <c:out value="customer not edited yet" /> </b>
            <c:set var="customer" value="${customerDetailsForSelectedCustomer}" />
        </c:if>
                
        
        <c:if test="${customerEdited.equals('yes')}">
            <b> <c:out value="customer edited successfully" /> </b>
            <c:set var="customer" value="${editedCustomerDetails}" />
        </c:if>
        <br>
        
        <h1>Click this <a href="http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerDetails">link </a> to View customer details</h1>
        <br>
        <b>Modify Customer Details:</b> <br>
        
        <form name="myform"  method="POST">
            Name : <input type="text" name="custName" value="${customer.custName}"><br>
            Address : <input type="text" name="custAddress" value="${customer.custAddress}"><br>
            Email : <input type="text" name="custEmail" value="${customer.custEmail}"><br><br>
            <input type="button" name="btn_ChangeSubmit" value="Submit the Changes" onclick="editRecord(${customer.custId});">
        </form>
        
    </body>
    
</html>
