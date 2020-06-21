package com.model;

public class Teacher extends Person {
	
	public String getUsername() {
		return getName();
	}

	public void setUsername(String username) {
		setName(username);
	}
	
	public String getTitle(){
		return "老师";
	}

}
