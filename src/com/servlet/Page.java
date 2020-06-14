package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manager.StudentManager;
import com.manager.TeacherManager;
import com.manager.impl.StudentManagerImpl;
import com.manager.impl.TeacherManagerImpl;
import com.model.Student;
import com.model.Teacher;
import com.util.PageModel;

public class Page extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		
		if("teacher".equals(type)) {
			TeacherManager smg = new TeacherManagerImpl();
			PageModel<Teacher> pageModel = smg.getTeachers(pageNo, pageSize);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/jsp/teacher/list.jsp").forward(request, response);
		}else {
			StudentManager smg = new StudentManagerImpl();
			PageModel<Student> pageModel = smg.getStudents(pageNo, pageSize);
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/jsp/student/list.jsp").forward(request, response);
		}
	}

}
