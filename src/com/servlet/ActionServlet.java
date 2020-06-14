package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;

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

public class ActionServlet extends HttpServlet {

	private StudentManager studentManager = new StudentManagerImpl();
	private TeacherManager teacherManager = new TeacherManagerImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String actionType = req.getParameter("action");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");

		boolean success = false;

		if (actionType.contains("student")) {
			// forward to student update page
			String gender = req.getParameter("gender");
			String birthday = req.getParameter("birthday");
			String address = req.getParameter("address");
			if ("delete_student".equals(actionType)) {
				success = studentManager.deleteStudent(id);
				// retrieve list page 需要优化代码 提取为一个函数
				if (success) {
					String pageNo = "1";
					String pageSize = "5";
					PageModel<Student> pageModel = studentManager.getStudents(pageNo, pageSize);
					req.setAttribute("pageNo", pageNo);
					req.setAttribute("pageSize", pageSize);
					req.setAttribute("pageModel", pageModel);
					req.getRequestDispatcher("/jsp/student/list.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
				}
			} else if ("update_student".equals(actionType)) {
				// 先查询
				Student student = studentManager.getStudent(id);
				if (student == null) {
					// 学生信息不存在
					throw new RuntimeException("学生信息不存在!!! id=" + id);
				}
				req.setAttribute("student", student);
				req.getRequestDispatcher("/jsp/student/update.jsp").forward(req, resp);;
			} else if ("save_student".equals(actionType)) {
				Student student = new Student();
				student.setName(name);
				student.setPassword(password);
				student.setGender(gender);
				student.setBirthday(birthday);
				student.setAddress(address);
				if (id == null || id.length() == 0) {
					success = studentManager.addStudent(student);
				} else {
					student.setId(Integer.valueOf(id));
					success = studentManager.updateStudent(student);
				}
				// retrieve list page 需要优化代码 提取为一个函数
				if (success) {
					String pageNo = "1";
					String pageSize = "5";
					PageModel<Student> pageModel = studentManager.getStudents(pageNo, pageSize);
					req.setAttribute("pageNo", pageNo);
					req.setAttribute("pageSize", pageSize);
					req.setAttribute("pageModel", pageModel);
					req.getRequestDispatcher("/jsp/student/list.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
				}
			} else if ("add_student".equals(actionType)) {
				req.getRequestDispatcher("/jsp/student/add.jsp").forward(req, resp);
			}
		} else {
			if ("delete_teacher".equals(actionType)) {
				success = teacherManager.deleteTeacher(id);
			} else if ("update_teacher".equals(actionType)) {
				Teacher teacher = teacherManager.getTeacher(id);
				if (teacher == null) {
					throw new RuntimeException("老师信息已经不存在!!! id=" + id);
				}
				req.getRequestDispatcher("/jsp/teacher/upadate.jsp").forward(req, resp);
			} else if ("save_teacher".equals(actionType)) {
				Teacher teacher = new Teacher();
				teacher.setUsername(name);
				teacher.setPassword(password);
				if (id == null || id.length() == 0) {
					success = teacherManager.addTeacher(teacher);
				} else {
					teacher.setId(Integer.valueOf(id));
					success = teacherManager.updateTeacher(teacher);
				}
				// retrieve list page
				if (success) {
					req.getRequestDispatcher("/jsp/teacher/list.jsp").forward(req, resp);
				} else {
					req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
				}
			} else if ("add_teacher".equals(actionType)) {
				req.getRequestDispatcher("/jsp/teacher/add.jsp").forward(req, resp);
			}
		}
	}

}