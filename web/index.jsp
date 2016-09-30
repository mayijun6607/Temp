<%--
  Created by IntelliJ IDEA.
  User: Mayijun
  Date: 2016/9/6
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>MyBBS</title>
  </head>
  <script type="text/javascript" src="/JQuery/jquery-3.1.0.js"></script>
  <body>
  <%--登录后和登录前不同的TITLE.JSP--%>
  <%
      //设置本页面位置Session
      session.setAttribute("lastURL","/index.jsp");

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

 <%-- 主页的正式内容--%>

  <%--放Mybbs>>XXX这个导航--%>
  <span style="height:5%;width:100%;font-weight: bold;font-size: larger;position: absolute;top:19%;">
   &nbsp;<img height:="2.5%" width="1%" src="<c:url value="/index/image/index-home.jpg"/>"/>  &gt;&gt;  <a href="<c:url value="/index.jsp"/>">MyBBS</a>
  </span>

  <%--日常区板块--%>
  <div style="background-color:#99d9ea;border:2px solid black;height:74%;width:50%;position:absolute;top:24%;left:2%;">

    <%--日常区横幅--%>
    <span ><img  src="<c:url value="/index/image/index-commonday.jpg"/> "/> <hr/></span>

    <%--官方公告--%>
    <div style="border:1px solid black;position:absolute;top:12%;left:1.5%;">
      <img  src="<c:url value="/index/image/index-official.jpg" />"/>
    </div>
      <span style="font-size: x-large;font-weight: bold;position:absolute;top:12.5%;left:17%;"><a href="">MyBBS|官方公告</a> </span>
      <span style="position:absolute;top:20%;left:17%;">官方信息发布<b>(>>未施工<<)</b></span>

    <%--综合讨论--%>
    <div style="position: absolute;top:12% ;left:50%;">
      <img width="40%" height="22%" src="<c:url value="/index/image/index-general.jpg"/> "/>
    </div>
      <span style="font-size: x-large;font-weight: bold;position:absolute;top:12.5%;left:72%;">
        <a href="<c:url value="/general/general.jsp"/> ">MyBBS|综合讨论</a>
      </span>
      <span style="position:absolute;top:20%;left:72%;">综合讨论，日常灌水</span>
    </div>

  <%--图片轮播，右上角板块--%>
  <div style="background-color:#99d9ea;border:2px solid black;height:34%;width:43%;position:absolute;top:24%;right:2%;">
        <div id="imgDiv" style="position:relative;left:20%;top:5%;">
            <img id="image1" height="80%" width="60%" src="/index/exImage/Kayle_Splash_6.jpg"/>
          <%--  <img id="image2" height="80%" width="60%" hidden src="/index/exImage/Leona_Splash_7.jpg"/>
            <img id="image3" height="80%" width="60%" hidden src="/index/exImage/Shyvana_Splash_5.jpg"/>
            <img id="image4" height="80%" width="60%" hidden src="/index/exImage/Vladimir_Splash_0.jpg"/>--%>
        </div>
      <div style="position: absolute;left:40%;top:90%;">
          <input id="imageB1" type="image" src="/index/exImage/point.jpg"/>&nbsp;
          <input id="imageB2" type="image" src="/index/exImage/point-white.jpg"/>&nbsp;
          <input id="imageB3" type="image" src="/index/exImage/point-white.jpg"/>&nbsp;
          <input id="imageB4" type="image" src="/index/exImage/point-white.jpg"/>
      </div>

     <%-- <div style="position:absolute;top:20%;">
          <input id="imageL" type="image" src="/index/exImage/L-white.jpg"/>
      </div>
      *左右箭头*
      <div style="position:absolute;">
          <input id="imageR" type="image" src=""/>
      </div>--%>
  </div>

  <%--没想好，右下角板块--%>
  <div style="background-color:#99d9ea;border:2px solid black;height:38%;width:43%;position:absolute;top:60%;right:2%;">

  </div>
  </body>
<script type="text/javascript">
    $(function(){
        //按钮变色
        $('#imageB1').mouseover(function(){
            $(this).attr("src","/index/exImage/point.jpg");
        });
        $('#imageB1').mouseout(function(){
            if(($('#image1').is('img[src="/index/exImage/Kayle_Splash_6.jpg"]'))) {
                $('#imageB2').attr("src","/index/exImage/point-white.jpg");
                $('#imageB3').attr("src","/index/exImage/point-white.jpg");
                $('#imageB4').attr("src","/index/exImage/point-white.jpg");
            }
            else{
                $(this).attr("src", "/index/exImage/point-white.jpg");
            }
            //$(this).attr("src", "/index/exImage/point-white.jpg");
        });
        $('#imageB2').mouseover(function(){
            $(this).attr("src","/index/exImage/point.jpg");
        });
        $('#imageB2').mouseout(function(){
            if(($('#image1').is('img[src="/index/exImage/Leona_Splash_7.jpg"]'))) {
                $('#imageB1').attr("src","/index/exImage/point-white.jpg");
                $('#imageB3').attr("src","/index/exImage/point-white.jpg");
                $('#imageB4').attr("src","/index/exImage/point-white.jpg");
            }
            else{
                $(this).attr("src", "/index/exImage/point-white.jpg");
            }
        });
        $('#imageB3').mouseover(function(){
            $(this).attr("src","/index/exImage/point.jpg");
        });
        $('#imageB3').mouseout(function(){
            if(($('#image1').is('img[src="/index/exImage/Shyvana_Splash_5.jpg"]'))) {
                $('#imageB1').attr("src","/index/exImage/point-white.jpg");
                $('#imageB2').attr("src","/index/exImage/point-white.jpg");
                $('#imageB4').attr("src","/index/exImage/point-white.jpg");
            }
            else{
                $(this).attr("src", "/index/exImage/point-white.jpg");
            }
        });
        $('#imageB4').mouseover(function(){
            $(this).attr("src","/index/exImage/point.jpg");
        });
        $('#imageB4').mouseout(function(){
            if(($('#image1').is('img[src="/index/exImage/Vladimir_Splash_0.jpg"]'))) {
                $('#imageB1').attr("src","/index/exImage/point-white.jpg");
                $('#imageB3').attr("src","/index/exImage/point-white.jpg");
                $('#imageB2').attr("src","/index/exImage/point-white.jpg");
            }
            else{
                $(this).attr("src", "/index/exImage/point-white.jpg");
            }
        });


        //按钮的功能
        $('#imageB1').click(function(){
            $('#image1').fadeOut(400,function (){
                $(this).attr("src","/index/exImage/Kayle_Splash_6.jpg").fadeIn(400);
                $('#imageB1').attr("src","/index/exImage/point.jpg");
                $('#imageB2').attr("src","/index/exImage/point-white.jpg");
                $('#imageB3').attr("src","/index/exImage/point-white.jpg");
                $('#imageB4').attr("src","/index/exImage/point-white.jpg");
            });
        });
        $('#imageB2').click(function (){
            $('#image1').fadeOut(400,function(){
                $(this).attr("src","/index/exImage/Leona_Splash_7.jpg").fadeIn(400);
                $('#imageB2').attr("src","/index/exImage/point.jpg");
                $('#imageB1').attr("src","/index/exImage/point-white.jpg");
                $('#imageB3').attr("src","/index/exImage/point-white.jpg");
                $('#imageB4').attr("src","/index/exImage/point-white.jpg");
            });
        });
        $('#imageB3').click(function (){
            $('#image1').fadeOut(400,function(){
                $(this).attr("src","/index/exImage/Shyvana_Splash_5.jpg").fadeIn(400);
                $('#imageB3').attr("src","/index/exImage/point.jpg");
                $('#imageB2').attr("src","/index/exImage/point-white.jpg");
                $('#imageB1').attr("src","/index/exImage/point-white.jpg");
                $('#imageB4').attr("src","/index/exImage/point-white.jpg");
            });
        });
        $('#imageB4').click(function (){
            $('#image1').fadeOut(400,function(){
                $(this).attr("src","/index/exImage/Vladimir_Splash_0.jpg").fadeIn(400);
                $('#imageB4').attr("src","/index/exImage/point.jpg");
                $('#imageB2').attr("src","/index/exImage/point-white.jpg");
                $('#imageB3').attr("src","/index/exImage/point-white.jpg");
                $('#imageB1').attr("src","/index/exImage/point-white.jpg");
            });
        });


    });
    //图片轮播
    function myP(){
        window.setTimeout("$('#imageB2').click()",3000);
        window.setTimeout("$('#imageB3').click()",6000);
        window.setTimeout("$('#imageB4').click()",9000);
        window.setTimeout("$('#imageB1').click()",12000);
    }
    /*  $('#image1').on('myPicture',function(){
     myP();
     }).trigger('myPicture');*/
    window.setTimeout("myP()",0);
    window.setInterval("myP()",12000);
</script>
</html>
