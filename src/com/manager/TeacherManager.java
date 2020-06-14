package com.manager;

import java.util.List;

import com.model.Student;
import com.model.Teacher;
import com.util.PageModel;

public interface TeacherManager {
	
	// 老师登陆时检测
	public Teacher checkLogin(String name, String password);

	// 添加老师信息
	public boolean addTeacher(Teacher teacher);
	// 添加老师信息
	public boolean addStudent(Student student);

	// 删除老师信息
	public boolean deleteTeacher(String teachId);
	// 删除学生信息
	public boolean deleteStudent(String studentId);

	// 更新老师信息
	public boolean updateTeacher(Teacher teacher);
	// 更新学生信息
	public boolean updateStudent(Student student);

	// 查询一个老师的信息
	public Teacher getTeacher(String tid);
	// 查询所有老师的信息
	public List<Teacher> getAllTeachers();
	// 查询所有学生的信息
	public List<Student> getAllStudents();
	// 学生分页查询
	public List<Student> getStudents(int start, int pagesize);
	// 老师分页查询
	public PageModel<Teacher> getTeachers(String start, String pagesize);
	

}
