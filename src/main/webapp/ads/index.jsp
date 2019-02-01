<%--
  Created by IntelliJ IDEA.
  User: landonharvey
  Date: 2/1/19
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:forEach var="ad" items="${ads}">
        <div class="ad">
            <h2>${ad.title}</h2>
            <p>Description ${ad.description}</p>
        </div>
    </c:forEach>

</body>
</html>
