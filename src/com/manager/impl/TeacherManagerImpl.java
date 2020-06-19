package com.manager.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.exception.ApplicationException;
import com.manager.TeacherManager;
import com.model.Student;
import com.model.Teacher;
import com.util.DBConnection;
import com.util.DateFormater;
import com.util.PageModel;
import org.apache.log4j.Logger;

public class TeacherManagerImpl implements TeacherManager {
	private static Logger logger = Logger.getLogger(TeacherManagerImpl.class);
	public Teacher checkLogin(String name, String password) {
		Teacher t = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from teacher where username=? and password=?";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while (rs.next()) {
				t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setUsername(rs.getString("username"));
				t.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, rs);
		}
		return t;
	}

	private int getMaxId(){
		//1. getAllStudents().size();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select max(id) as id from teacher";
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int maxId = rs.getInt("id");
				return maxId+1;
			}
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, rs);
		}
		return -1;
	}

	public boolean addTeacher(Teacher teacher) {
		
		boolean flag = false;
		String sql = "insert into teacher(id,username,password) values(?,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		int teacherMaxId = getMaxId();
		if(teacherMaxId==-1){
			throw new RuntimeException("产生最大序号失败！！！");
		}
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			//get oracle sequence
			//1. select max(id) from student
			pst.setInt(1, teacherMaxId);
			pst.setString(2, teacher.getUsername());
			pst.setString(3, teacher.getPassword());
			pst.executeUpdate();
			flag = true;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, null);
		}
		return flag;
	}

	public boolean updateTeacher(Teacher teacher) {
		boolean flag = false;
		String sql = "update teacher set username=?,password=? where id='" + teacher.getId() + "'";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, teacher.getUsername());
			pst.setString(2, teacher.getPassword());
			pst.executeUpdate();
			flag = true;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, null);
		}
		return flag;
	}

	public boolean deleteTeacher(String id) {
		boolean flag = false;
		String sql = "delete from teacher where id=" + id + "";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			int row = pst.executeUpdate();
			if (row > 0)
				flag = true;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, null);
		}
		return flag;
	}

	public Teacher getTeacher(String teachId) {
		String sql = "SELECT * FROM teacher where id=?";

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(teachId));
			rs = pst.executeQuery();
			Teacher t = null;
			while (rs.next()) {
				t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setUsername(rs.getString("username"));
				// should encrpt
			}
			return t;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, null);
		}
	}
	
	public List<Teacher> getAllTeacher(String teachId) {
		List<Teacher> list = new ArrayList<Teacher>();
		String sql = "select * from teacher";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Teacher t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setUsername(rs.getString("username"));
				t.setPassword(rs.getString("password"));
				list.add(t);
			}
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, st, null);
		}
		return list;
	}

	public boolean addStudent(Student student) {
		boolean flag = false;
		String sql = "insert into student(id,name,password,gender,birthday,address) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		int studentMaxId = getMaxId();
		if(studentMaxId==-1){
			throw new RuntimeException("产生最大序号失败！！！");
		}
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			//get oracle sequence
			//1. select max(id) from student
			pst.setInt(1, studentMaxId);
			pst.setString(2, student.getName());
			pst.setString(3, student.getPassword());
			pst.setString(4, student.getGender());
			pst.setDate(5, DateFormater.toSqlDate(student.getBirthday()));
			pst.setString(6, student.getAddress());
			pst.executeUpdate();
			flag = true;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, null);
		}
		return flag;
	}


	public boolean deleteStudent(String id) {
		boolean flag = false;
		String sql = "delete from student where id=? ";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DBConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(id));
			int row = pst.executeUpdate();
			if (row > 0)
				flag = true;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, null);
		}
		return flag;
	}

	public boolean updateStudent(Student student) {
		boolean flag = false;
		String sql = "delete from student where id=" +student + "";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBConnection.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			int row = pst.executeUpdate();
			if (row > 0)
				flag = true;
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, pst, null);
		}
		return flag;
	}

	public List<Teacher> getAllTeachers() {
		List<Teacher> list = new ArrayList<Teacher>();
		String sql = "select * from teacher";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Teacher t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setUsername(rs.getString("username"));
				t.setPassword(rs.getString("password"));
				list.add(t);
			}
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, st, rs);
		}
		return list;

	}

	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setPassword(rs.getString("password"));
				s.setGender(rs.getString("gender"));
				s.setBirthday(DateFormater.sqlDate2String(rs.getDate("birthday")));
				s.setAddress(rs.getString("address"));
				list.add(s);
			}
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, st, rs);
		}
		return list;

	}
	public List<Student> getStudents(String start, String pagesize) {
		List<Student> list = new ArrayList<Student>();
		// oracle and H2
		String sql ="SELECT * FROM (SELECT ROWNUM rn, s.* FROM student s) a WHERE a.rn>=("+start+" - 1) * "+pagesize+" + 1 AND a.rn <= "+start+" * "+pagesize;
		//找到符合条件的老师记录
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				// encrpt
				s.setPassword(rs.getString("password"));
				s.setGender(rs.getString("gender"));
				s.setBirthday(DateFormater.sqlDate2String(rs.getDate("birthday")));
				s.setAddress(rs.getString("address"));
				list.add(s);
			}
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, st, rs);
		}
		return list;
	}


	private List<Teacher> executeSql(String sql) {
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Teacher> results = new ArrayList<Teacher>();
		try {
			conn = DBConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Teacher t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setUsername(rs.getString("username"));
				// encrpt
				t.setPassword(rs.getString("password"));
				results.add(t);
			}
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, st, rs);
		}
			
		return results;
	}

	public PageModel<Teacher> getTeachers(String start, String pagesize) {
		return queryTeachers(null,start, pagesize);
	}

	public PageModel<Teacher> queryTeachers(Map<String, String> parameters, String pageNo,
			String pageSize) {
		PageModel<Teacher> pageModel = null;
		List<Teacher> list = new ArrayList<Teacher>();
		String baseSql = "SELECT ROWNUM rn, t.* FROM teacher t";
		if (parameters != null && parameters.size()>0) {
			Set<Entry<String, String>> entrySet = parameters.entrySet();
			baseSql = baseSql+ " where 1 = 1 ";
			for (Entry<String, String> entry : entrySet) {
				//TODO only consider string equals
				baseSql = baseSql + " and t."+entry.getKey()+ " ='"+entry.getValue()+"' ";
			}
		}
		//找到老师表总记录数
		logger.info("总记录SQL:"+baseSql);
		List<Teacher> totals = executeSql(baseSql);

		// oracle and H2
		String sql ="SELECT * FROM ("+baseSql+") a WHERE a.rn>=("+pageNo+" - 1) * "+pageSize+" + 1 AND a.rn <= "+pageNo+" * "+pageSize;
		//找到符合条件的老师记录
		logger.info("分页SQL:"+sql);
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Teacher t = new Teacher();
				t.setId(rs.getInt("id"));
				t.setUsername(rs.getString("username"));
				// encrpt
				t.setPassword(rs.getString("password"));
				list.add(t);
			}
			pageModel = new PageModel<Teacher>();
			pageModel.setPageNo(Integer.parseInt(pageNo));
			pageModel.setPageSize(Integer.parseInt(pageSize));
			pageModel.setTotalRecords(totals.size());
			pageModel.setList(list);
		} catch (Exception e) {
			throw new ApplicationException(e);
		} finally {
			DBConnection.close(conn, st, rs);
		}
		return pageModel;
	}
}
