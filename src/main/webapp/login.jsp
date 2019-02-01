<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/1/19
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String username = (request.getParameter("username"));
    String password = (request.getParameter("password"));

    String methodType = (request.getMethod());

    if (methodType.equalsIgnoreCase("post")){
        if (username != null && password != null) {
            if (username.equals("admin") && password.equals("password")){
                response.sendRedirect("profile.jsp");
            }
        }
    }
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="partials/navbar.jsp"%>
    <form method="POST" action="login.jsp">
        <div>
            <label for="username">USERNAME: </label>
            <input type="text" id="username" title="username">
        </div>
        <div>
            <label for="password">PASSWORD: </label>
            <input type="text" id="password" title="password">
        </div>
        <button>LOGIN</button>
    </form>
</body>
</html>
