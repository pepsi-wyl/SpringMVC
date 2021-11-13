<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax_T2</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            //ajax
            $("#btn").click(function () {
                $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/ajax/ajax_T2",
                        // data: {method: "jQueryAjax"},
                        dataType: "json",
                        success: function (date) {
                            for (let i = 0; i < date.length; i++) {
                                // html +=
                                //     "<tr>" +
                                //     "<td>" + date[i].bookID + "</td>" +
                                //     "<td>" + date[i].bookName + "</td>" +
                                //     "<td>" + date[i].bookCounts + "</td>" +
                                //     "<td>" + date[i].detail + "</td>" +
                                //     "</tr>"
                                console.log(date[i].bookID);
                                console.log(date[i].bookName);
                                console.log(date[i].bookCounts);
                                console.log(date[i].detail);
                            }
                            console.log(date);
                        },
                    }
                );
                // $("#content").html(html);
            });
        });
    </script>
</head>
<body>

<input type="button" value="加载数据" id="btn">

</body>
</html>
