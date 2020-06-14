<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.model.*" %>
<%@ page import="com.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>新增学生</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/student.css">
  </head>
<body>
	<div id="studentAdd">  
        <h1>新增学生</h1>  
        <form action="<%=basePath%>servlet/action?action=save_student" method="post">  
            <input type="text" required="required" placeholder="用户名" name="name"></input><br/>
            <input type="password" required="required" placeholder="密码" name="password"></input><br/>
            <input type="text" required="required" placeholder="性別" name="gender"></input><br/>
            <input type="text" required="required" placeholder="生日" name="birthday"></input><br/>
            <input type="text" required="required" placeholder="地址" name="address"></input><br/>
            <button class="but" type="submit">新增</button>  
        </form>  
    </div>  
	
</body>
</html>