<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/11/12
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>up</title>
</head>

<style>
    .file {
        position: relative;
        display: inline-block;
        background: #D0EEFF;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #1E88C7;
        text-decoration: none;
        text-indent: 0;
        line-height: 20px;
    }

    .file input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
    }

    .file:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
    }
</style>

<body>

<!--
form标签   method=post(get提交数据长度有限)
          encType=multipart/form-data  上传二进流的方式
使用input  type=file 添加上传的文件
-->
<form action="${pageContext.request.contextPath}/file/up" method="post" enctype="multipart/form-data">
    <input type="file" name="file" class="file"> <br/><br/>
    <input type="submit" value="提交" class="file"><br/><br/>
</form>

</body>
</html>
