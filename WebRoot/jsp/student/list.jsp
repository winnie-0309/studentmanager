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
    
    <title>学生列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="学生列表">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/student.css">
  </head>
  
  <body>
    <h1>学生列表</h1>
	<%
		String pageSize = (String) request.getAttribute("pageSize");
		String pageNo = (String) request.getAttribute("pageNo");
		PageModel<Student> pageModel=(PageModel<Student>) request.getAttribute("pageModel");
		List<Student> list=pageModel.getList();
	%>
    <form name="form1" action="<%=basePath%>servlet/page?type=student" method="post">
	    <table align="center" >
		        <tr>
		            <td align="center" colspan="4">
		                <h2>所有信息 - <a href="<%=basePath%>servlet/action?action=add_student">新增</a></h2> 
		            </td>
		        </tr>
		        <tr align="center">
		            <td><b>ID</b></td>
		            <td><b>姓名</b></td>
		            <td><b>地址</b></td>
		            <td><b>操作</b></td>
		        </tr> 
		         
		  <%
		    if(list==null||list.size()<1){
		        %><p align="center">还没有任何数据！</p>
		    <%}else{
		        for(Student rec:list){
		  %>   
		  <tr>
		     <td><%=rec.getId() %></td>
		     <td><%=rec.getName() %></td>
		     <td><%=rec.getAddress() %></td>
		     <td><a href="<%=basePath%>servlet/action?action=update_student&id=<%=rec.getId()%>">修改</a><a href="<%=basePath%>servlet/action?action=delete_student&id=<%=rec.getId()%>">刪除</a></td>       
		  </tr>
		  <%
		        }
		    }    
		  %>
		</table>
	
        <TABLE border="0" width="100%" >
            <TR>
                <TD align="left"><a>每页条数</a>
                <select name="pageSize"
                    onchange="document.all.pageNo.value='1';document.all.form1.submit();">
                    <option value="5" <%if(pageSize.equals("5")){%>
                        selected="selected" <%}%>>5</option>
                    <option value="10" <%if(pageSize.equals("10")){%>
                        selected="selected" <%}%>>10</option>
                    <option value="20" <%if(pageSize.equals("20")){%>
                        selected="selected" <%}%>>20</option>
                </select></TD>
                <TD align="right">
                <a href="javascript:document.all.pageNo.value='<%= pageModel.getTopPageNo() %>';document.all.form1.submit();">首页</a>
                <a href="javascript:document.all.pageNo.value='<%= pageModel.getPreviousPageNo() %>';document.all.form1.submit();">上一页</a>
                <a href="javascript:document.all.pageNo.value='<%= pageModel.getNextPageNo()%>';document.all.form1.submit();">下一页</a>
                <a href="javascript:document.all.pageNo.value='<%= pageModel.getBottomPageNo()%>';document.all.form1.submit();">尾页</a>
                <a>第</a>
                <select name="pageNo" onchange="document.all.form1.submit();">
                    <%
                        int pageCount = pageModel.getTotalPages();
                    %>
                    <%
                        for (int i = 1; i <= pageCount; i++) {
                    %>
                    <option value="<%=i%>" <%if(pageNo.equals(i+"")){%>
                        selected="selected" <%}%>><%=i%></option>
                    <%
                        }
                    %>
                </select><a>页</a></TD>
            </TR>
        </TABLE>
        </form>
	</body>
</html>