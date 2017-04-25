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
        
        <!--
        Query the customer table
        Type 'db' ->  ctrl-space -> select "DB Query Database" -> variable name: customers
        -->
        <sql:query var="customers" dataSource="jdbc/myTestDataSource">
            SELECT * from customer
        </sql:query>

        
        <!--
        Display all the records of the customer table
        Type 'jstl'  -> Ctrl-Space  ->  choose "JSTL For Each" -> 
        Collection: dollar-sign{customers.rows}     //rows of the Resultset from the query
        Current Item of the Iteration:  customer    //customer object from which attributes can be derived
        -->        
        <c:forEach var="customer" items="${customers.rows}">
            <div>
                <c:out value="${customer.cust_name}" /> 
            </div>
        </c:forEach>
    
    </body>
</html>
