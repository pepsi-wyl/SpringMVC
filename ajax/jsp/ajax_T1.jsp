
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax_T1</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        <%--验证用户名--%>
        $(function () {
            $("#userName").bind("blur", function () {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/ajax/ajax_T1",
                    data: {
                        // userName: userName.val()
                        userName: $("#userName").val(),
                    },
                    dataType: "json",
                    success: function (date) {
                        if ("true" == date) {
                            alert("存在");
                        } else if ("false" == date) {
                            alert("不存在");
                        }
                    },
                    // error: function (date) {
                    //     alert("请求错误！！！");
                    // }
                });
            });
        });
    </script>
</head>
<body>

<input type="text" id="userName" name="userName">

</body>
</html>

