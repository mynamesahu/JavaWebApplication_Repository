
<%@taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql"  uri="http://java.sun.com/jsp/jstl/sql" %>
<%-- 
    Document   : customerForm
    Created on : Apr 6, 2017, 9:28:58 PM
    Author     : Sambit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #headingRowColor{
                background-color: aqua;
            }
        </style>
        <!--link rel="stylesheet" type="text/css" href="style.css"-->
    </head>
    
    <body>
        
        <h1> Customer Details </h1>
        
        <!--- Define the data source to access the database "mytestschema"   ------->  <!--This can be used if you don't want to refer the "web.xml" file's resource reference entry   -->
        <sql:setDataSource  var="myDataSource" 
                            driver="com.mysql.jdbc.Driver"
                            url="jdbc:mysql://localhost:3306/mytestschema"
                            user="root"  
                            password="nbuser"
        />
        <!--- End Define the data source to access the database "mytestschema""  --->              
        
        
        
        <!--Insert the submitted customer details(name, addr, email) as a new record in the customer table ---->
        <c:if test="${ param.txt_Name != null && param.txt_Addr != null && param.txt_Email != null }">
            <%--sql:update var="customerInsertResult" dataSource="${myDataSource}"--%>   <!--This can be used if you don't want to refer the "web.xml" file's resource reference entry   -->
            <sql:update var="customerInsertResult" dataSource="jdbc/myTestDataSource">
                INSERT INTO customer (cust_name, cust_address, cust_email)
                <%--VALUES ("${param.txt_Name}", "${param.txt_Addr}", "${param.txt_Email}")--%>
                VALUES (?,?,?)
                <sql:param value="${param.txt_Name}" />
                <sql:param value="${param.txt_Addr}" />
                <sql:param value="${param.txt_Email}" />
            </sql:update>
        </c:if>
        <!--End Insert the submitted customer details(name, addr, email) as a new record in the customer table ---->
        
        
        <!-- Print the Data insert successful message -->
        <c:if test="${customerInsertResult>=1}">
            <font size="5" color='green'> 
                Congratulations ! Data inserted successfully.
            </font>
        </c:if>
        <!-- End Print the Data insert successful message -->
        
        
        <!--- Select all records of  Customer table using JSTL-SQL Query------->    
        <%--sql:query var="customerResult" dataSource="${myDataSource}"--%>         <!--This can be used if you don't want to refer the "web.xml" file's resource reference entry   -->
        <sql:query var="customerResult" dataSource="jdbc/myTestDataSource">
            SELECT * FROM customer
        </sql:query>
        <!--- End Select all records of  Customer table using JSTL-SQL Query--->
        
        
        
        <!--- Display Customer table Records----------------------------->
        <table border="1">
            <tr id="headingRowColor">
                <td><b>Customer ID</b></td>
                <td><b>Customer Name</b></td>
                <td><b>Customer Address</b></td>
                <td><b>Customer Email</b></td>
            </tr>
        <c:forEach var="customer" items="${customerResult.rows}">
            <tr>
                <td><c:out value="${customer.cust_ID}"/></td>
                <td><c:out value="${customer.cust_Name}"/></td>
                <td><c:out value="${customer.cust_address}"/></td>
                <td><c:out value="${customer.cust_email}"/></td>
            </tr>
        <br>
        </c:forEach>
        </table>
        <!--- End Display Customer table Records-------------------------->
        
        <br>
        
        <h2> Enter Customer Details: </h2>
                       
        <form name="frmCustomer" action="customerForm.jsp" method="post">
            Name:       <input type="text" name="txt_Name" value=""><br>
            Address:    <input type="text" name="txt_Addr" value=""><br>
            Email:      <input type="text" name="txt_Email" value=""><br>
                        <input type="hidden" name="hid_ID" value="">
                        <input type="submit" value="submit">
        </form>
        <br>
        <br>
        
    </body>
</html>
