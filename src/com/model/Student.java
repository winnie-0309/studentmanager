package com.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.util.DateFormater;

public class Student {
    private int id;
    private String sid;
    private String name;
    private String password;
    private String gender;
    private Date birthday;
    private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = DateFormater.toDate(birthday);
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", sid=" + sid + ", name=" + name + ", password=" + password + ", gender=" + gender
				+ ", birthday=" + birthday + ", address=" + address + "]";
	}
	
	
	
    
}
