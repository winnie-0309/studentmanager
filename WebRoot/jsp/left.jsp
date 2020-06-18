<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.model.*"%>
<%@ page import="com.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
    <ul>
   	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String type = (String)session.getAttribute("type");
		Person p =(Person) session.getAttribute("person");
		String name = p.getName();
		Integer id = p.getId();
		if("student".equals(type)){
	%>
        <li><a href="<%=basePath%>servlet/page?type=<%=type%>&pageNo=1&pageSize=5" target="main">学生列表</a></li>
        <li><a href="<%=basePath%>servlet/action?action=update_<%=type%>&id=<%=id%>" target="main">修改学生</a></li>
        <%
         }else{
        %>
        <li><a href="<%=basePath%>servlet/page?type=<%=type%>&pageNo=1&pageSize=5" target="main">老师列表</a></li>
        <li><a href="<%=basePath%>servlet/action?action=update_<%=type%>&id=<%=id%>" target="main">修改老师</a></li>
        <li><a href="<%=basePath%>servlet/page?type=student&pageNo=1&pageSize=5&name=<%=name%>" target="main">学生列表</a></li>s 
        <%
         }
        %>

    </ul>
</body>
</body>
</html>