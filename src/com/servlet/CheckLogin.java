package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.StudentManager;
import com.manager.TeacherManager;
import com.manager.impl.StudentManagerImpl;
import com.manager.impl.TeacherManagerImpl;
import com.model.Student;
import com.model.Teacher;
import com.util.PageModel;

public class CheckLogin extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		// 生成验证码
		String checkcode = request.getParameter("checkcode");
		String piccode = (String) request.getSession()
				.getAttribute("checkcode");

		if (checkcode.equalsIgnoreCase(piccode)) {

			HttpSession session = request.getSession();
			String pageNo = "1";
			String pageSize = "5";
			if ("teacher".equals(type)) {
				TeacherManager tmg = new TeacherManagerImpl();
				Teacher t = tmg.checkLogin(name, password);
				if (t == null) {
					request.setAttribute("error", "用户名，密码或许不正确！");
					request.getRequestDispatcher("/teaLogin.jsp").forward(request,
							response);
				} else {
					session.setAttribute("person", t);
					PageModel<Teacher> pageModel = tmg.getTeachers(pageNo,
							pageSize);
					request.setAttribute("pageNo", pageNo);
					request.setAttribute("pageSize", pageSize);
					request.setAttribute("pageModel", pageModel);
					request.getRequestDispatcher("/jsp/teacher/list.jsp")
							.forward(request, response);
				}
			} else if ("student".equals(type)) {
				StudentManager smg = new StudentManagerImpl();
				Student s = new Student();
				s = smg.checkLogin(name, password);
				if (s == null) {
					request.setAttribute("error", "登陆失败：用户名，密码，验证码或许不正确！");
					request.getRequestDispatcher("/stuLogin.jsp").forward(request,
							response);
				} else {
					session.setAttribute("person", s);
					PageModel<Student> pageModel = smg.getStudents(pageNo,
							pageSize);
					request.setAttribute("pageNo", pageNo);
					request.setAttribute("pageSize", pageSize);
					request.setAttribute("pageModel", pageModel);
					request.getRequestDispatcher("/jsp/student/list.jsp")
							.forward(request, response);
				}
			}
		} else {
			request.setAttribute("error", "验证码或许不正确！");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

	}
}