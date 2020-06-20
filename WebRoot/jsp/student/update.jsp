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
    <title>学生信息修改</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/student.css"/>
  </head>
<body>
	<div id="studentAdd">  
        <h1 align="center">修改学生信息</h1>  
        <%
          Student student = (Student)request.getAttribute("student");
          String birthday = DateFormater.toDateString(student.getBirthday());
         %>
        <form action="<%=basePath%>servlet/action?action=save_student" method="post"> 
        	<table align="center">
        	<tr><td><input type="hidden" name="id" value="<%=student.getId() %>"/></td></tr> 
            <tr>
            <td>用户名：</td>
            <td><input type="text" required="required" placeholder="用户名" name="name" value="<%=student.getName() %>"/></td></tr>
            <tr>
            <td>密码：</td>
            <td><input type="password" required="required" placeholder="密码" name="password" value="<%=student.getPassword() %>"/></td></tr>
            <tr>
            <td>性别：</td>
            <td><input type="text" required="required" placeholder="性別" name="gender" value="<%=student.getGender() %>"/></td></tr>
            <tr>
            <td>生日：</td>
            <td><input type="text" required="required" placeholder="生日" name="birthday" value="<%=birthday %>"/></td></tr>
            <tr>
            <td>地址：</td>
            <td><input type="text" required="required" placeholder="地址" name="address" value="<%=student.getAddress() %>"/></td></tr>
            <tr><td><button class="but" type="submit">修改</button></td> 
                <td><button class="but" type="reset">重置</button></td></tr>
            </table> 
        </form>  
    </div>
	
</body>
</html>