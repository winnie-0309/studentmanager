<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.*"%>
<%@ page import="com.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String operation = request.getParameter("operation");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title><%if("register".equals(operation)){%>注册<% }else{%>新增<%}%>老师</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/student.css"/>
	<style>
	html {
	width: 100%;
	height: 100%;
	overflow: hidden;
	font-style: sans-serif;
}

body {
	width: 100%;
	height: 100%;
	margin: 0;
	background-color: #4A374A;
}
</style>
  </head>
<body>
	<div id="teacherAdd">  
        <h1 align="center"><%if("register".equals(operation)){%>注册<% }else{%>新增<%}%>老师</h1>
        <form action="<%=basePath%>servlet/action?action=save_teacher" method="post">
        <input type="hidden" name="operation" value="<%=operation%>"/>
        <table align="center"> 
            <tr>
            <td>用户名：</td>
            <td><input type="text" required="required" placeholder="用户名" name="name"/><td>
            </tr>
            <tr>
            <td>密码：</td>
            <td><input type="password" required="required" placeholder="密码" name="password"/></td>
            </tr>
            <tr><td><button class="but" type="submit"><%if("register".equals(operation)){%>注册<% }else{%>添加<%}%></button></td>
                <td><button class="but" type="reset">重置</button></td>
             </tr>
            </table>
        </form>  
    </div>  
</body>
</html>