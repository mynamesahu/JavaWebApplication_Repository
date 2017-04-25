<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : testDatasource
    Created on : Mar 25, 2017, 10:48:40 PM
    Author     : Sambit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            
            h1{
                color:blue;
            }
            
            body{
                background-color:aliceblue;
            }
            
        </style>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <!-- ctrl-space -> select "DB Report" -> 
            variable name: QueryResult     //   QueryResult object from which attributes can be derived
        -->
        
    <sql:query var="QueryResult" dataSource="jdbc/myTestDataSource">
        SELECT * from customer
    </sql:query>
        
    <table border="1">
        <!-- column headers -->
        <tr>
        <c:forEach var="columnName" items="${QueryResult.columnNames}">
            <th><c:out value="${columnName}"/></th>
        </c:forEach>
    </tr>
    <!-- column data -->
    <c:forEach var="row" items="${QueryResult.rowsByIndex}">
        <tr>
        <c:forEach var="column" items="${row}">
            <td><c:out value="${column}"/></td>
        </c:forEach>
        </tr>
    </c:forEach>
</table>
    </body>
</html>
