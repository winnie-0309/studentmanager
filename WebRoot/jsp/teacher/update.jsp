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
    <title>老师修改信息</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/student.css">
  </head>
<body>
	<div id="studentAdd">  
        <h1 align="center">修改老师信息</h1>  
        <%
          Teacher teacher = (Teacher)request.getAttribute("teacher");
         %>
        <form action="<%=basePath%>servlet/action?action=save_teacher" method="post"> 
        	<table align="center">
        	<tr>
			   <td>
                    <input type="radio" name="type" value="teacher">教师
                    <input type="radio" name="type" value="student" checked>学生</td> 
			</tr>
        	<tr><td><input type="hidden" name="id" value="<%=teacher.getId() %>"/></td></tr> 
            <tr>
            <td>用户名：</td>
            <td><input type="text" required="required" placeholder="用户名" name="name" value="<%=teacher.getUsername() %>"/></td></tr>
            <tr>
            <td>密码：</td>
            <td><input type="password" required="required" placeholder="密码" name="password" value="<%=teacher.getPassword() %>"/></td></tr>
            <tr><td><button class="but" type="submit">添加</button></td> 
                <td><button class="but" type="reset">重置</button></td></tr>
            </table> 
        </form>  
    </div>
	
</body>
</html>