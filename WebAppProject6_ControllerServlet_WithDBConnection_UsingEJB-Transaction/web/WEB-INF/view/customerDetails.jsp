<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script language="javascript">
            function editRecord(id){
                document.myform.action='http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerEditForm?custID_ForViewCustomer='+id;
                document.myform.submit();
            }
        </script>
    </head>
    
    <body>
        <h3>Click this <a href="http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerForm">link </a> to Go Back to Customer Entry Form</h3>
        
        <h1>Customer Details</h1>
        
        <!--Type 'jstl'  -> Ctrl-Space  ->  choose "JSTL For Each" -> 
            Collection:                     dollar{customerDetails}    //Resultset List of all customer records (of the "Customer" entity class)stored in an application scoped variable "customerDetails" defined in the controller servlet's context parameter 
            Current Item of the Iteration:  customer                   //customer object from which attributes can be derived
        --> 
        <!--form name="frm1" action="http://localhost:8080/WebAppProject6_ControllerServlet_WithDBConnection_UsingEJB-Transaction/customerForm" method="post"-->
        <form name="myform"  method="post">
            <Table>
                <c:forEach var="customer" items="${customerDetails}">  
                    <tr>
                        <td><c:out value="${customer.custName}" /></td>
                        <td><c:out value="${customer.custAddress}" /></td>
                        <td><c:out value="${customer.custEmail}" /></td>
                        <!--td><input type="button" name="btn_customerEdit" value="EDIT" onclick = this.form.submit();></td-->
                        <td><input type="button" name="btn_customerEdit" value="EDIT" onclick="editRecord(${customer.custId});"></td>
                        
                    </tr>
                </c:forEach>
            </Table>
        </form>
    </body>
    
</html>
