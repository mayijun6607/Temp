<%--
  Created by IntelliJ IDEA.
  User: Mayijun
  Date: 2016/9/28
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/JQuery/jquery-3.1.0.js"></script>
</head>
<body>
    <input type="button" value="go"/>
</body>
<script type="text/javascript">
    $('input').click(function(){
        alert($(window).height()); //浏览器当前窗口可视区域高度
        alert($(document).height()); //浏览器当前窗口文档的高度
       alert($('input').height());
    });
</script>
</html>
