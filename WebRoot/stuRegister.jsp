<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stuRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <from method="post" action="">
        <lable>
             <h1>用户注册页面</h1>
             <hr />
        </lable>
        <div>
             用户名：<input type="text" placeholder="请输入用户名" name="name" required/><br>
             密&nbsp; &nbsp;码:<input type="password" placeholder="请输入密码"name="password" required/><br>
             确&nbsp; &nbsp;认:<input type="password" placeholder="请再次输入密码"name="repeat" required/><br>
        <button class="but" type="submit">提交注册</button>  
        </div>
         
      </from>
  </body>
</html>
