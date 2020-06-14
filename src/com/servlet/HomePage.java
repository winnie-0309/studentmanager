package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		if(type!=null && type.equalsIgnoreCase("teacher")) {
			response.sendRedirect("../teaLogin.jsp");
		}else if(type!=null && type.equalsIgnoreCase("student")) {
			response.sendRedirect("../stuLogin.jsp");
		}
		else{
			response.sendRedirect("../option.jsp");
		}	

	}
}
