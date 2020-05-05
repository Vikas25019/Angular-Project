<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
<style>
            <%@ include file="../../../css/style.css"%>
</style>
</head>
<body>
    <div class="body">
     <%@ include file="header.jsp"%>
        <div class="middle">
            <div class="insert" id ="insert" style="display:block">
                <p style="color:white;text-align:center;font-size:20px;">CREATE NEW EMPLOYEE</p>
                 <br>
                 <p style="color:white; text-align:center;">${exception} ${message}</p>
                <form:form action="saveEmployee" method="get" modelAttribute="employee">
                    <form:input path="id" maxlength="5" placeholder="Enter employee id"/><br>
                    <form:input path="clientId" placeholder="Enter client id"/><br>
                    <form:input path="name" placeholder="Enter employee name"/><br>
                    <form:input path="department" placeholder="Enter department"/><br>
                    <form:input path="email" placeholder="Enter email"/><br>
                    <form:input path="dateOfBirth" placeholder="Enter dob"/><br>
                    <input type="submit" style="text-align:center; cursor:pointer;">
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>