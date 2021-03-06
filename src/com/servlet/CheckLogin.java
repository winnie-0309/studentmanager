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
import org.apache.log4j.Logger;

/**
 * 处理老师、学生登录请求
 * 并将老师或学生信息存到session中方便后续调用，不需要再查询数据库
 *
 */
public class CheckLogin extends HttpServlet {

	private static Logger logger = Logger.getLogger(CheckLogin.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// 生成验证码
		String checkcode = request.getParameter("checkcode");
		String piccode = (String) request.getSession().getAttribute("checkcode");
		logger.info(String.format("type=%s ,name=%s checkcode(input)=%s, checkcode(session)=%s ", type, name, checkcode, piccode));
		if (checkcode.equalsIgnoreCase(piccode)) {

			HttpSession session = request.getSession();
			if ("teacher".equals(type)) {
				TeacherManager tmg = new TeacherManagerImpl();
				Teacher t = tmg.checkLogin(name, password);
				if (t == null) {
					request.setAttribute("error", "用户名，密码或许不正确！");
					request.getRequestDispatcher("/login.jsp").forward(request,
							response);
				} else {
					session.setAttribute("person", t);
					session.setAttribute("type", "teacher");
					request.getRequestDispatcher("/jsp/home.jsp")
					.forward(request, response);
				}
			} else if ("student".equals(type)) {
				StudentManager smg = new StudentManagerImpl();
				Student s = smg.checkLogin(name, password);
				if (s == null) {
					request.setAttribute("error", "登陆失败：用户名，密码或许不正确！");
					request.getRequestDispatcher("/login.jsp").forward(request,
							response);
				} else {
					session.setAttribute("person", s);
					session.setAttribute("type", "student");
					request.getRequestDispatcher("/jsp/home.jsp")
					.forward(request, response);
				}
			}
		} else {
			request.setAttribute("error", "验证码或许不正确！");
			request.getSession().setAttribute("checkcode", null);
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}

	}
}