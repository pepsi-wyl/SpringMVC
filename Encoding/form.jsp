<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/10/4
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form</title>
</head>
<body>

<form action=${pageContext.request.contextPath}/EncodingController/test1 method="post">
    <input type="text" name="name">
    <input type="password" name="password">
    <input type="submit">
</form>

</body>
</html>
