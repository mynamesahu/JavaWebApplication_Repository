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
            
            function deleteRecord(id){
                //alert("id = "+id);
                document.myform.action='http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerEditForm?custID_ForDeleteCustomer='+id;
                document.myform.submit();
            }
        </script>
        
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
       
        <c:set var="customerEdited" value="${param.customerEdited}" />
        
        <%--customerEdited = <c:out value="${customerEdited}" />==%>
        
        <%--
        <c:if test="${!empty customerEdited}">
        --%>
        
        <c:if test="${customerEdited.equals('no')}">
            <%--<div class="message"><b><c:out value="customer not edited yet" /></b></div> --%>
            <c:set var="customer" value="${customerDetailsForSelectedCustomer}" />
        </c:if>
                
        
        <c:if test="${customerEdited.equals('yes')}">
            <div class="succssfulMessage"><b> <c:out value="customer edited successfully" /> </b></div>
            <c:set var="customer" value="${editedCustomerDetails}" />
        </c:if>
            
        
         <c:if test="${customerEdited.equals('deleted')}">
            <div class="succssfulMessage"><b> <c:out value="customer deleted successfully" /> </b></div>
            <c:set var="customer" value="${deletedCustomerDetails}" />
        </c:if>
        
       
        <c:if test="${customerEdited.equals('customerDoesntExistToBeDeleted')}">
            <div class="warningMessage"><b> <c:out value="customer does not exist any more to be deleted" /> </b></div>
            <%--<c:set var="customer" value="${deletedCustomerDetails}" />--%>  
        </c:if>
        
        <br>
        
        <b>Modify Customer Details:</b> <br>
        
        <form name="myform"  method="POST">
            Name : <input type="text" name="custName" value="${customer.custName}"><br>
            Address : <input type="text" name="custAddress" value="${customer.custAddress}"><br>
            Email : <input type="text" name="custEmail" value="${customer.custEmail}"><br><br>
            <input type="button" name="btn_ChangeSubmit" value="Submit the Changes" onclick="editRecord(${customer.custId});">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="btn_DeleteSubmit" value="Delete the Customer" onclick="deleteRecord(${customer.custId});">
        </form>
        
    </body>
    
</html>
