<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.model.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.exception.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>错误页面.</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/student.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css"/>
    <script type="text/javascript" src="<%=basePath%>js/student.js"></script>
    <script type="text/javascript">
	    window.onload=function(){
	      var olink=document.getElementById("showErrorMessageButton");
	      var odiv=document.getElementById("errorMessageDiv");
	      olink.onclick=function(){
	       toggleMessage(odiv);
	       return false;
	      }
	    }
    </script>

  </head>
  <body>
  	<body>
            <table width="80%" align="center">
                <tr>
                    <td style="border-bottom:dotted 1px Gray;" colspan="2" >
                          错误提示
                    </td><td></td>
                </tr>
                <tr>
                    <td style="width: 130px" >
                        <img src="<%=basePath%>img/error.jpg" id="error_img" />
                    </td>
                    <td>尊敬的用户：<br />系统出现了异常，请重新登录或刷新页面。
                        <br />如果问题重复出现，请向管理员反馈。<br />
                        <a id="showErrorMessageButton" href="javascript:showErrorMessage();">详细错误信息</a>
                        <br />
                    </td>
                </tr>
                <tr>
                    <td style="border-bottom:dotted 1px Gray;" colspan="2" >
                    </td><td></td>
                </tr>
            </table>
            <div id="errorMessageDiv" align="left" >
                <pre>
                <textArea style="width:1030px; height:500px">
                    <%
                       ApplicationException exception = (ApplicationException)request.getAttribute("exception");
                        try {
                            //全部内容先写到内存，然后分别从两个输出流再输出到页面和文件
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            PrintStream printStream = new PrintStream(byteArrayOutputStream);

                            printStream.println();
                            printStream.println("用户信息");
                            printStream.println("访问的路径: " + request.getAttribute("javax.servlet.forward.request_uri"));
                            printStream.println();

                            printStream.println("异常信息");
                            printStream.println(exception.getClass() + " : " + exception.getMessage());
                            printStream.println();

                            Enumeration<String> e = request.getParameterNames();
                            if (e.hasMoreElements()) {
                                printStream.println("请求中的Parameter包括：");
                                while (e.hasMoreElements()) {
                                    String key = e.nextElement();
                                    printStream.println(key + "=" + request.getParameter(key));
                                }
                                printStream.println();
                            }

                            printStream.println("堆栈信息");
                            exception.printStackTrace(printStream);
                            printStream.println();

                            out.print(byteArrayOutputStream);    //输出到网页

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    %>
                 </textArea>
               </pre>
            </div>
  </body>
</html>  