package com.servlet;

import com.manager.StudentManager;
import com.manager.TeacherManager;
import com.manager.impl.StudentManagerImpl;
import com.manager.impl.TeacherManagerImpl;
import com.model.Student;
import com.model.Teacher;
import com.util.Constants;
import com.util.PageModel;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对所有请求封装并实现转发：
 * 处理老师、学生的新增、修改、删除、查询等请求
 *
 */
public class ActionServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(ActionServlet.class);

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
		logger.info(String.format("Parameters: id=%s , action=%s ,name=%s", id, actionType, name));
		boolean success = false;

		if (actionType.contains("student")) {
			// forward to student update page
			String gender = req.getParameter("gender");
			String birthday = req.getParameter("birthday");
			String address = req.getParameter("address");
			if ("delete_student".equals(actionType)) {
				success = studentManager.deleteStudent(id);
				// retrieve list page 需要优化代码 提取为一个函数
				PageModel<Student> pageModel = studentManager.getStudents(Constants.DEFAULT_PAGENO, Constants.DEFAULT_PAGESIZE);
				fowardToListPage(req, resp, success, "student", pageModel);
			} else if ("update_student".equals(actionType)) {
				// 先查询
				Student student = studentManager.getStudent(id);
				if (student == null) {
					// 学生信息不存在
					throw new RuntimeException("学生信息不存在!!! id=" + id);
				}
				req.setAttribute("student", student);
				req.getRequestDispatcher("/jsp/student/update.jsp").forward(req, resp);
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
				PageModel<Student> pageModel = studentManager.getStudents(Constants.DEFAULT_PAGENO, Constants.DEFAULT_PAGESIZE);
				fowardToListPage(req, resp, success, "student", pageModel);
			} else if ("add_student".equals(actionType)) {
				req.getRequestDispatcher("/jsp/student/add.jsp").forward(req, resp);
			}
		} else {
			if ("delete_teacher".equals(actionType)) {
				success = teacherManager.deleteTeacher(id);
				PageModel<Teacher> pageModel = teacherManager.getTeachers(Constants.DEFAULT_PAGENO, Constants.DEFAULT_PAGESIZE);
				fowardToListPage(req, resp, success, "teacher", pageModel);
			} else if ("update_teacher".equals(actionType)) {
				Teacher teacher = teacherManager.getTeacher(id);
				if (teacher == null) {
					throw new RuntimeException("老师信息已经不存在!!! id=" + id);
				}
				req.setAttribute("teacher", teacher);
				req.getRequestDispatcher("/jsp/teacher/update.jsp").forward(req, resp);
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
				PageModel<Teacher> pageModel = teacherManager.getTeachers(Constants.DEFAULT_PAGENO, Constants.DEFAULT_PAGESIZE);
				fowardToListPage(req, resp, success, "teacher", pageModel);
			} else if ("add_teacher".equals(actionType)) {
				req.getRequestDispatcher("/jsp/teacher/add.jsp").forward(req, resp);
			}
		}
	}

	private void fowardToListPage(HttpServletRequest req, HttpServletResponse resp, boolean success, String type,
								  PageModel pageModel) throws ServletException, IOException {
		if (success) {
			req.setAttribute("pageNo", Constants.DEFAULT_PAGENO);
			req.setAttribute("pageSize", Constants.DEFAULT_PAGESIZE);
			req.setAttribute("pageModel", pageModel);
			req.getRequestDispatcher("/jsp/" + type + "/list.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

}