package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

/**
 *  主要是用于分页列表
 *  需要将数据封装到一个PageModel中
 *
 */
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
		String name = request.getParameter("name");
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		Map<String, String> parameters = new HashMap<String,String>();
		if("teacher".equals(type)) {
			TeacherManager smg = new TeacherManagerImpl();
			PageModel<Teacher> pageModel = new PageModel<Teacher>();
			if(name!=null&&name.length()>0){
				//没有实现的接口
				parameters.put("username", name);
				pageModel = smg.queryTeachers(parameters,pageNo,pageSize);
			}else{
				pageModel = smg.getTeachers(pageNo, pageSize);
			}
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/jsp/teacher/list.jsp").forward(request, response);
		}else {
			StudentManager smg = new StudentManagerImpl();
			PageModel<Student> pageModel = new PageModel<Student>();
			if(name!=null&&name.length()>0){
				parameters.put("name", name);
				pageModel = smg.queryStudents(parameters,pageNo,pageSize);
			}else{
				pageModel = smg.getStudents(pageNo, pageSize);
			}
			request.setAttribute("pageModel", pageModel);
			request.getRequestDispatcher("/jsp/student/list.jsp").forward(request, response);
		}
	}

}
