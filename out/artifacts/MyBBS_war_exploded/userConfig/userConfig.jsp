<%@ page import="sun.rmi.server.InactiveGroupException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mayijun
  Date: 2016/9/16
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户设置</title>
</head>
<body>
    <%--登录后和登录前不同的TITLE.JSP--%>
    <%
        //获取权限
        int userAuth=(Integer)session.getAttribute("userAuth");
        if(session.getAttribute("username")!=null){%>
    <jsp:include page="${pageContext.request.contextPath}/title/titleSuccess.jsp"/>
    <%
    }
    else {
    %>
    <jsp:include page="${pageContext.request.contextPath}/title/title.jsp"/>
    <%
        }
    %>

    <%--放Mybbs>>XXX这个导航--%>
        <span style="height:5%;width:100%;font-weight: bold;font-size: larger;position: absolute;top:19%;">
            &nbsp;<img height:="2.5%" width="1%" src="<c:url value="/index/image/index-home.jpg"/>"/>  &gt;&gt;  <a href="<c:url value="/index.jsp"/>">MyBBS</a>  &gt;&gt;  用户设置
        </span>

    <%--用户设置正式内容--%>
    <div style="background-color:#99d9ea;border:2px solid black;width:97.6%;height:74%;position:absolute;top:24%;left:1%;">
        <%-- 用户设置标题--%>
        <br/>
        &nbsp;<span style="font-size: 220%"><b>用户设置</b><hr/></span>
        <%--左边的选项栏--%>
        <div style="border:2px solid black;position: absolute;left:10%;top:15%;width:15%;height:75%;">
            <hr/><input type="button" value="修改密码" style="width:100%;height: 15%"
                        onclick="setPassword1()"/>
            <br/><hr/>
            <input type="button" value="自己的主题帖" style="width:100%;height: 15%" onclick="checkTiezi1()"/>
            <br/><hr/>
            <span <%
                if(userAuth<2){
                    out.print("hidden");
                }
            %> >
            <input type="button" value="设置权限" style="width:100%;height: 15%" onclick="setAuth1()"/>
            <br/><hr/>
            </span>
        </div>

    </div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/userConfig/common.js"></script>
</html>
