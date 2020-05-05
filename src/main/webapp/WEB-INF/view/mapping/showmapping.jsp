<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<html>
<head>
<style>
            <%@ include file="../../../css/style.css"%>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body class='body'>
    <%@ include file="../employee/header.jsp"%>

    <%
     LinkedHashMap<String,String> data = (LinkedHashMap<String,String>)request.getAttribute("viewData");
     Set<String> columnsSet = data.keySet();
     %>
        <div class='tableContent'>
            <table class='content-table-client'>
                <thead>
                    <tr>
                    <%
                    for(String columns : columnsSet){
                            %><th><%out.print(columns);%></th><%
                         }
                    %>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                          <%
                             for(String columns : columnsSet){
                              %><td><%out.print(data.get(columns));%></td><%
                             }
                          %>
                    </tr>
                </tbody>
            </table>
        </div>
</body>
</html>