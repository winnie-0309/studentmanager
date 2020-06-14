<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/student.css">
	
  </head>
  
  <body>
    <h1>学生管理系统</h1>
    <a href="<%=basePath%>servlet/homePage?type=student">学生登陆</a>
    <a href="<%=basePath%>servlet/homePage?type=teacher">老师登陆</a>
    <a href="<%=basePath%>servlet/register?type=student">学生注册</a>
    <a href="<%=basePath%>servlet/register?type=teacher">老师注册</a>
  </body>
  
</html>
