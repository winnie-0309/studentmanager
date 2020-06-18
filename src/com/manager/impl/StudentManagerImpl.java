package com.manager.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.manager.StudentManager;
import com.model.Student;
import com.model.Teacher;
import com.util.DBO;
import com.util.DateFormater;
import com.util.PageModel;

public class StudentManagerImpl implements StudentManager {
	
	public Student checkLogin(String name, String password) {
		Student s = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from student where name=? and password=?";
		conn = DBO.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while (rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setPassword(rs.getString("password"));
				s.setGender(rs.getString("gender"));
				s.setAddress(rs.getString("address"));
				s.setBirthday(DateFormater.sqlDate2String(rs.getDate("birthday")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return s;
	}

	private int getMaxId(){
		//1. getAllStudents().size();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select max(id) as id from student";
		conn = DBO.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int maxId = rs.getInt("id");
				return maxId+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return -1;
	}

	public boolean addStudent(Student student) {
		boolean flag = false;
		String sql = "insert into student(id,name,password,gender,birthday,address) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBO.getConnection();
		int studentMaxId = getMaxId();
		if(studentMaxId==-1){
			throw new RuntimeException("产生最大序号失败！！！");
		}
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return flag;
	}

	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "update student set name=?,password=?,gender=?,birthday=?,address=? where id='" + student.getId() + "'";
		Connection conn = null;
		PreparedStatement pst = null;
		conn = (Connection) DBO.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, student.getName());
			pst.setString(2, student.getPassword());
			pst.setString(3, student.getGender());
			pst.setDate(4, DateFormater.toSqlDate(student.getBirthday()));
			pst.setString(5, student.getAddress());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return flag;
	}

	public boolean deleteStudent(String sid) {
		// TODO Auto-generated method stub
		boolean flag = false;
		//String sql = "delete from student where sid='" + sid + "'";
		String sql = "delete from student where id=" + sid + "";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pst = null;
		conn = DBO.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			int row = pst.executeUpdate();
			if (row > 0)
				flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return flag;
	}

	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		conn = DBO.getConnection();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//TODO
		}
		return list;

	}

	public Student getStudent(String id) {
		// oracle and H2
		String sql = "SELECT * FROM student where id=?";

		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBO.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(id));
			rs = pst.executeQuery();
			Student s = null;
			while (rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				// should encrpt
				s.setPassword(rs.getString("password"));
				s.setGender(rs.getString("gender"));
				s.setBirthday(DateFormater.sqlDate2String(rs.getDate("birthday")));
				s.setAddress(rs.getString("address"));
			}
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//TODO
		}
		return null;
	}

	private int getTotal(){
		//找到学生表总记录数
		String sql ="select count(*) from student";
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBO.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Student s = null;
			while (rs.next()) {
				rs = pst.executeQuery();
				int total = 0;
				if (rs.next()) {
					total = rs.getInt(1);// 总的数据条数
				}
				return total;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//TODO
		}
		return 0;
	}

	public PageModel<Student> queryStudents(Map<String, String> parameters,
			String pageNo, String pageSize) {
		PageModel<Student> pageModel = null;
		List<Student> list = new ArrayList<Student>();
		// oracle and H2
		String baseSql = "SELECT ROWNUM rn, s.* FROM student s";
		// 
		if (parameters != null && parameters.size()>0) {
			Set<Entry<String, String>> entrySet = parameters.entrySet();
			baseSql = baseSql+ " where 1 = 1 ";
			for (Entry<String, String> entry : entrySet) {
				//TODO only consider string equals
				baseSql = baseSql + " and s."+entry.getKey()+ " ='"+entry.getValue()+"' ";
			}
		}
		//找到表总记录数
		List<Student> totals = executeSql(baseSql);
		//
		String sql ="SELECT * FROM ("+baseSql+") a WHERE a.rn>=("+pageNo+" - 1) * "+pageSize+" + 1 AND a.rn <= "+pageNo+" * "+pageSize;
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBO.getConnection();
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
			//找到学生表总记录数
			pageModel = new PageModel<Student>();
			pageModel.setPageNo(Integer.parseInt(pageNo));
			pageModel.setPageSize(Integer.parseInt(pageSize));
			pageModel.setTotalRecords(totals.size());
			pageModel.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pageModel;
	}

	private List<Student> executeSql(String baseSql) {
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Student> results = new ArrayList<Student>();
		try {
			conn = DBO.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(baseSql);
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				// encrpt
				s.setPassword(rs.getString("password"));
				s.setGender(rs.getString("gender"));
				s.setBirthday(DateFormater.sqlDate2String(rs.getDate("birthday")));
				s.setAddress(rs.getString("address"));
				results.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		return results;
	}

	public PageModel<Student> getStudents(String pageNo, String pageSize) {
		return this.queryStudents(null, pageNo, pageSize);
	}
}
