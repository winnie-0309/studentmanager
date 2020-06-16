<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="css/student.css">
	-->
<script language="javascript">
    function myReload(){
        document.getElementById("CreateCheckCode").src 
        = document.getElementById("CreateCheckCode").src
        + "?nocache=" + new Date().getTime();
    }
    </script>

  </head>
  
  <body>
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
 
  <div align="center" style="padding-top: 2em">
        <span style="font-size: 3em">用户登录/LOGIN</span> 
        <span style="font-size: 3em color:red"><%=request.getAttribute("error")==null?"": request.getAttribute("error")%></span>
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
                    <a href="" onclick="myReload()">看不清楚，换一个</a>  
                    </td>
                </tr>
            </table>
            <table style="padding-left: 0.6em">
                <tr>
                    <td>
                    <input type="radio" name="type" value="teacher">教师
                    <input type="radio" name="type" value="student" checked>学生 </td>
                </tr>
                <tr>
                    <td><input type="submit" value="登录" /> <input type="reset"
                        value="重置" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
