<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : testDataSource2
    Created on : Mar 26, 2017, 1:18:36 PM
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
        <h1>Hello World!</h1>
        <!--Type 'db' ->  ctrl-space -> select "DB Query Database" -> 
            variable name: customers
        -->
    
        <sql:query var="customers" dataSource="jdbc/myTestDataSource">
        SELECT * from customer
        </sql:query>
      
        <!--Type 'jstl'  -> Ctrl-Space  ->  choose "JSTL For Each" -> 
            //Collection:  dollar-sign{customers.rows}      // Resultset (List of records) from the query
            Current Item of the Iteration:  customer       //customer object from which attributes can be derived
        -->
        <c:forEach var="customer" items="${customers.rows}">
            <div>
                <c:out value="${customer.cust_name}"/>
            </div>
        </c:forEach>
    </body>
</html>
