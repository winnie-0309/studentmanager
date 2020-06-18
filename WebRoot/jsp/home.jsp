<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.*"%>
<%@ page import="com.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>xue管理系统</title>
</head>
<frameset rows="15%,*">
    <frame src="<%=basePath%>jsp/top.jsp">
    <frameset cols="20%,*">
        <frame src="<%=basePath%>jsp/left.jsp">
        <frame src="<%=basePath%>jsp/main.jsp" name="main">
    </frameset>
</frameset>
</html>