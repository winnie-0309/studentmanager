package com.model;

import java.util.Date;

import com.util.DateFormater;

public class Student extends Person{
    private String sid;
    private String gender;
    private Date birthday;
    private String address;
    
    public String getTitle(){
		return "同学";
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
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
		return "Student [id=" + getId() + ", sid=" + sid + ", name=" + getName() + ", password=" + getPassword() + ", gender=" + gender
				+ ", birthday=" + birthday + ", address=" + address + "]";
	}
	
	
	
    
}
