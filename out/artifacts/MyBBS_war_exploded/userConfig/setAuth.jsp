<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mayijun
  Date: 2016/9/20
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限设置</title>
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
                <input type="button" disabled value="设置权限" style="width:100%;height: 15%" onclick="setAuth1()"/>
                <br/><hr/>
                </span>
        </div>
            <%--右边的显示框：设置权限--%>
            <div id="setPassword" style="border:2px solid black;position: absolute;left:30%;top:15%;width:50%;height:80%;">
                <%--设置权限标题--%>
                <br/>
                &nbsp;<span style="font-size: 150%"><b>设置权限</b><hr/></span>
                 <%--   &lt;%&ndash;页码选择&ndash;%&gt;
                    <div style="position: absolute;left:30%;top:3%;">
                        <form action="<c:url value=""/> " method="get">
                            <input type="button" value="首页" onclick="firstPage()"/>
                            <input type="button" value="上一页" onclick="previousPage()"/>
                            <%
                                for(int t=1;t<=(Integer)request.getAttribute("totalPage");t++){
                            %>
                            <a href="${pageContext.request.contextPath}/UserTieziServlet?currentPage=<%=t%>"><%=t%></a>
                            <%
                                }
                            %>
                            <input type="button" value="下一页" onclick="nextPage()"/>
                            <input type="button" value="尾页" onclick="lastPage()"/>
                            当前页:<input type="text" value="<%=request.getAttribute("currentPage")%>" disabled size="1"/>
                            <input type="text" name="currentPage" size="1"/>/<%=request.getAttribute("totalPage")%>页
                            <input type="submit" value="go"/>
                        </form>
                    </div>--%>
                <%--模糊查询--%>
                <div style="position: relative;left:3%;">
                    <form action="/SetAuthServlet" method="post">
                        查询用户名:<input type="text" name="queryName" size="15" />
                        <input type="checkbox" name="isBlur" value="yes">模糊查询
                        <input type="submit" value="搜索"/>&nbsp;*只查询前十条*&nbsp;
                        <font color="red" size="1">
                            <%
                                if(request.getAttribute("queryWarn")!=null){
                                    out.print((String)request.getAttribute("queryWarn"));
                                }
                            %>
                        </font>
                    </form>
                </div>
                <%--查询结果--%>
                <div  style="position:relative; left:20%;">
                    <%
                        if(request.getAttribute("EuserName")!=null&&request.getAttribute("EauthId")!=null){
                            int EauthId=(Integer)request.getAttribute("EauthId");
                            String Eauth="";
                            if(EauthId==4){
                                Eauth="站长";
                            }
                            else if(EauthId==3){
                                Eauth="管理员";
                            }
                            else if(EauthId==2){
                                Eauth="斑竹";
                            }
                            else if(EauthId==1){
                                Eauth="会员";
                            }
                    %>
                    <table style="border-collapse:   separate;   border-spacing:   6px; border:1px solid black">
                        <tr>
                            <th>用户名</th>
                            <th>权限等级</th>
                        </tr>
                        <tr style="font-size: large">
                            <td align="center"><%=(String)request.getAttribute("EuserName")%></td>
                            <td align="center"><%=Eauth%></td>
                        </tr>
                    </table>
                    <%
                        }
                        String[] BuserName=new String[10];
                        int[] BauthId=new int[10];
                        String[] BuserAuth=new String[10];
                        for(int i=0;i<10;i++){
                            BuserName[i]="";
                            BuserAuth[i]="";
                        }

                        BuserName=(String[])request.getAttribute("BuserName");
                        BauthId=(int[])request.getAttribute("BuserAuth");
                        if(BuserName!=null&&BauthId!=null) {
                            for (int k = 0; k < 10; k++) {
                                if (BauthId[k] == 4) {
                                    BuserAuth[k] = "站长";
                                } else if (BauthId[k] == 3) {
                                    BuserAuth[k] = "管理员";
                                } else if (BauthId[k] == 2) {
                                    BuserAuth[k] = "斑竹";
                                } else if (BauthId[k] == 1) {
                                    BuserAuth[k] = "会员";
                                }
                                else{
                                    BuserAuth[k]="";
                                }
                                if(BuserName[k]==null){
                                    BuserName[k]="";
                                }
                            }
                        }
                    %>
                    <%
                        if(BuserAuth[0]!=""){
                    %>
                    <table style="border-collapse:   separate;   border-spacing:   6px; ">
                        <tr>
                            <th>用户名</th>
                            <th>权限等级</th>
                        </tr>
                        <%
                            for(int j=0;j<10;j++){
                        %>
                        <tr style="font-size: large">
                            <td><%=BuserName[j]%></td>
                            <td><%=BuserAuth[j]%></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <%}%>
                </div>
            </div>
    </div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/userConfig/common.js"></script>
</html>
