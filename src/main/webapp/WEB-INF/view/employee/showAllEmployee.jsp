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
    <%@ include file="header.jsp"%>

    <%
     List<LinkedHashMap<String,String>> data = (List<LinkedHashMap<String,String>>)request.getAttribute("data");
     LinkedHashMap<String,String> hashData = data.get(0);
     Set<String> columnSet = hashData.keySet();%>

     <div class='tableContent'>
        <table class='content-table-employee'>
          <thead>
              <tr>
                 <%for(String columnName : columnSet){
                    %><th><%out.print(columnName);%></th><%
                  }
                 %>
                 <th>Edit</th><th>Delete</th><th>View</th>
              </tr>
          </thead>
          <tbody>
            <tr>
            <%
                for(LinkedHashMap<String,String> result : data){
                    Set<String> columns= result.keySet();
                    for(String columnName : columns){
                        %><td><%out.print(result.get(columnName));%></td><%
                    }
                    String idName = "employeeId";
                    String clientIdName = "clientId";
                    String clientIdValue = result.get(clientIdName);
                    String idValue = result.get(idName);
                    String path = "updateEmployeePage?employeeId=%s&clientId=%s";
                    String output = String.format(path,idValue,clientIdValue);
                    %>
                    <td>
                        <a href=<% out.print(output); %>>edit</a>
                    </td>
                    <%
                    path = "deleteEmployee?employeeId=%s";
                    output = String.format(path,idValue);
                    %>
                    <td>
                        <a href=<% out.print(output); %>>delete</a>
                    </td>
                    <%
                       path = "viewMapping?employeeId=%s";
                       output = String.format(path,idValue);
                    %>
                    <td>
                        <a href=<% out.print(output); %>>view</a>
                    </td>
                      </tr>
                    <%}
            %>
                </tbody>
            </table>
        </div>
</body>
</html>