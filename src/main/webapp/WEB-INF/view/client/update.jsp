<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.util.*"%>
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
           <div class="update" id ="update" style="display:block">
           <p style="color:white;text-align:center;font-size:20px;">UPDATE CLIENT INFORMATION</p>
           <br><%
            String id = request.getParameter("clientId");
           %>
                   <p style="color:white; text-align:center;">${exception} ${message}</p>
                    <form:form action="updateClient" method="get" modelAttribute="client">
                        <form:hidden path="id"  value="<%=id%>" /><br>
                        <form:input path="name"  placeholder="Enter client name"/><br>
                        <form:input path="address"  placeholder="Enter client address"/><br>
                        <input type="submit" style="text-align:center; cursor:pointer;">
                     </form:form>
           </div>
        </div>
     </div>

</body>
</html>