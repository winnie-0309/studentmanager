<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teaRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
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
      <from method="post" action="">
        <lable>
             <h1>用户注册页面</h1>
             <hr />
        </lable>
      <div id="teacherAdd">  
        <h1>老师注册</h1>  
        <form action="<%=basePath%>servlet/Register?type=teacher"" method="post">  
            <input type="text" required="required" placeholder="用户名" name="name"></input><br/>
            <input type="password" required="required" placeholder="密码" name="password"></input><br/>
            <button class="but" type="submit">注册</button>  
        </form>  
    </div>  
      </from>
  </body>
</html>
