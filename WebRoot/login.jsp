<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.*"%>
<%@ page import="com.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/student.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css"/>
    <script type="text/javascript" src="<%=basePath%>js/student.js"></script>
	<script language="javascript">
	        /*分析：点击图片，需要换一张
              1.给图片绑定单击事件
              2.重新设置图片的src属性值
            */
　　　　　　window.onload = function(){
　　　　　　　　 document.getElementById("CreateCheckCode").onclick = function(){
　　　　　　　　　　this.src="<%=basePath%>servlet/picCodeGenerator?nocache=" + new Date().getTime();
　　　　　　　　 }
　　　　　　 }
    </script>
  </head>
  <body>
  <style>

  </style>
 
  <div align="center" style="padding-top: 2em">
        <span style="font-size: 3em">用户登录/LOGIN</span>
        <div style="color:red"><%=request.getAttribute("error") == null ? "" : request.getAttribute("error") %></div>
        <form action="<%=basePath%>servlet/checkLogin" method="post">
        <table style="padding-left: 1em;">
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" name="name" />
                    </td>
                </tr>
                <tr>
                    <td>密&nbsp;&nbsp;&nbsp;码：</td>
                    <td><input type="password" name="password" />
                    </td>
                </tr>
                <tr>
                    <td>验证码：</td>
                    <td><input name="checkcode" type="text" id="checkCode" title="验证码不区分大小写" size="8",maxlength="4"/>
                    <img src="<%=basePath%>servlet/picCodeGenerator" id="CreateCheckCode" align="middle">
                    </td>
                </tr>
            </table>
            <table style="padding-left: 0.6em">
                <tr>
                    <td>
                    <input type="radio" name="type" value="teacher">教师
                    <input type="radio" name="type" value="student" checked>学生</td>
                </tr>
                <tr>
                    <td><input type="submit" value="登录" />
                    <input type="button" onclick="register('type')" value="注册">
                    <input type="reset" value="重置" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
