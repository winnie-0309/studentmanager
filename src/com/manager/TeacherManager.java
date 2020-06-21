package com.manager;

import java.util.List;
import java.util.Map;

import com.model.Student;
import com.model.Teacher;
import com.util.PageModel;

public interface TeacherManager {
	
	// 老师登陆时检测
	public Teacher checkLogin(String name, String password);

	// 添加老师信息
	public boolean addTeacher(Teacher teacher);

	// 删除老师信息
	public boolean deleteTeacher(String teachId);

	// 更新老师信息
	public boolean updateTeacher(Teacher teacher);

	// 查询一个老师的信息
	public Teacher getTeacher(String tid);
	// 查询老师信息需分页
	public PageModel<Teacher> getTeachers(String start, String pagesize);
	// 查询老师信息需分页- 有查询条件
	public PageModel<Teacher> queryTeachers(Map<String, String> parameters, String pageNo,
			String pageSize);
	

}
