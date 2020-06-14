<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>学生登陆界面</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="css/student.css">
    <script language="javascript">
    function myReload(){
        document.getElementById("CreateCheckCode").src 
        = document.getElementById("CreateCheckCode").src
        + "?nocache=" + new Date().getTime();
    }
    </script>

  </head>
  <body>
  <h1>学生登陆</h1>
    <div id="login">  
        <h1>Login</h1> 
        <H3><%=request.getAttribute("error") %></H3> 
        <form action="<%=basePath%>servlet/checkLogin?type=student" method="post">  
            <input type="text" required="required" placeholder="用户名" name="username" value="jack"></input>  
            <input type="password" required="required" placeholder="密码" name="password" value="123456"></input>
            <input name="checkcode" type="text" id="checkCode" title="验证码区分大小写" size="8",maxlength="4"/>
            <img src="<%=basePath%>servlet/picCodeGenerator" id="CreateCheckCode" align="middle">
            <a href="" onclick="myReload()">看不清楚，换一个</a>  
            <button class="but" type="submit">登录</button>  
        </form>  
    </div>  
  </body>
</html>
