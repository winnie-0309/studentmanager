/**
 * 
 */
package com.manager;

import java.util.List;

import com.model.Student;
import com.util.PageModel;

/**
 * @author yanran
 *
 */
public interface StudentManager {
	// 登陆时
	public Student checkLogin(String name, String password);

	// 添加学生信息
	public boolean addStudent(Student student);

	// 更新学生信息
	public boolean updateStudent(Student student);

	// 删除学生信息
	public boolean deleteStudent(String id);

	// 查询单个学生信息-修改 (删除/修改)
	public Student getStudent(String id);
	// 按地址查询所有人并分页
	public PageModel<Student> queryStudents(String address, String pageNo, String pageSize);
	// 查询所有的学生信息 -全列表不分页
	public List<Student> getAllStudents();
	// 查询学生信息需分页
	public PageModel<Student> getStudents(String pageNo, String pageSize);

}
