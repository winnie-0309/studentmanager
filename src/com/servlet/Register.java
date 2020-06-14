package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		if(type!=null && type.equalsIgnoreCase("teacher")) {
			response.sendRedirect("../teaRegister.jsp");
		}else if(type!=null && type.equalsIgnoreCase("student")) {
			response.sendRedirect("../stuRegister.jsp");
		}
		else{
			response.sendRedirect("../option.jsp");
		}	

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
