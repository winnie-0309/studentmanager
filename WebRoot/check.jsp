<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>验证码校验</title>
     <link rel="stylesheet" type="text/css" href="css/student.css">
  </head>
  
  <body>
   <%
   //check code is what input
      String checkcode=request.getParameter("checkCode");
      if(checkcode==null || checkcode.length==0 ){
         out.print("<script>alert('请输入正确验证码');window.location.href('<%=basePath%><%=request.getParameter("type")%>Login.jsp')</script>");
      }else{
      
         if(!checkcode.equalsIgnoreCase((String)session.getAttribute("piccode"))){
              out.print("<script>alter('验证码不正确，请重新输入');history.back(-1);</script>");
         }else{
               out.print("登陆成功");
               
              window.location.href('<%=basePath%>home.jsp') 
         }
      }
    %>
     
   
  </body>
</html>
