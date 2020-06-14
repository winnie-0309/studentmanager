<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>Option.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
   <link rel="stylesheet" type="text/css" href="css/student.css">
  </head>
  
  <body>
    <h1>请选择您的身份</h1>
    <a href="<%=basePath%>stuLogin.jsp">学生</a>
    <a href="<%=basePath%>teaLogin.jsp">教师</a>
  </body>
</html>
