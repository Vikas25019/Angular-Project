<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
       <style>
                   <%@ include file="../../../css/style.css"%>
       </style>
</head>
<body>
<div class="body">
    <%@ include file="header.jsp"%>
    <div class="middle">
        <div class="retrieve" id = "retrieve">
            <p style="color:white;text-align:center;font-size:20px;">SHOW EMPLOYEE INFORMATION</p>
              <br>
              <p style="color:white; text-align:center;">${exception} ${message}</p>
             <form:form action="retrieveEmployee" method="get" modelAttribute="employee">
                 <form:input path="id" maxlength="5" placeholder="Enter employee id"/><br>
                 <input type="submit" value="show"style="text-align:center; cursor:pointer;">
             </form:form>
             <div class="retrieveAll">
              <form:form action="retrieveAllEmployee" method="get" modelAttribute="employee">
                  <input type="submit" value="show all" style="text-align:center; cursor:pointer;">
              </form:form>

         </div>
       </div>
   </div>
</div>
</body>
</html>