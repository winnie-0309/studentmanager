package com.manager.impl;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.manager.TeacherManager;
import com.model.Teacher;

public class TeacherManagerTest {

	TeacherManager teacherManager = null;
	@Before
	public void setUp() throws Exception {
		teacherManager = new TeacherManagerImpl();
	}

	@Test
	public void testCheckLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddTeacher() {
		Teacher teacher = new Teacher();
		teacher.setUsername("zhang");
		teacher.setPassword("123456");
		boolean expected = teacherManager.addTeacher(teacher);
		Assert.assertTrue(expected);
	}

	@Test
	public void testUpdateTeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllTeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddStudent() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteStudent() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStudent() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllTeachers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllStudents() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStudents() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTeachers() {
		fail("Not yet implemented");
	}

}
