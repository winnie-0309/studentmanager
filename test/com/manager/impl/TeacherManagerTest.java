package com.manager.impl;

import com.manager.TeacherManager;
import com.model.Teacher;
import com.util.PageModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TeacherManagerTest {

    TeacherManager teacherManager = null;

    @Before
    public void setUp() throws Exception {
        teacherManager = new TeacherManagerImpl();
    }

    @Test
    public void checkLogin() {
        //1.参数赋值(输入)  -->模拟用户在登录页面输入用户名和密码
        String name = "admin";
        String password = "123";
        //2.接口调用(功能实现问题-算法)  -->模拟用户点击了登录按钮，程序根据用户名和密码去数据库学生表检查是否存在
        Teacher teacher =  teacherManager.checkLogin(name, password);
        //3.结果检查(输出) -->模拟用户登录成功
        Assert.assertNotNull(teacher);
        //模拟用户将自己的信息显示到主页面的welcome相关
        Assert.assertEquals(name, teacher.getUsername());
        Assert.assertEquals(password, teacher.getPassword());
    }

    @Test
    public void addTeacher() {
        //1.模拟用户在老师新增或注册页面输入了用户名和密码
        Teacher teacher = new Teacher();
        teacher.setUsername("admin");
        teacher.setPassword("123456");
        //2.点击了新增按钮，触发teacherManager调用addTeacher 将数据保存到数据库
        boolean success = teacherManager.addTeacher(teacher);
        //3.数据保存成功 - 到数据库查看即可
        Assert.assertTrue(success);
        System.out.println(teacher);
    }

    @Test
    public void updateTeacher() {
        //1.左侧栏点击老师修改 2.列表的操作点击修改 - 默认带唯一主键(不可见)
        Teacher teacher = teacherManager.getTeacher("1");
        if(teacher==null){
            throw new RuntimeException("老师不存在！！！");
        }
        //2.模拟修改页面打开后，输入修改的密码
        teacher.setPassword("123");
        //3.模拟点击修改按钮
        boolean success = teacherManager.updateTeacher(teacher);
        //4.修改成功
        // 4.1.对侧边栏而言，啥事不用做
        Assert.assertTrue(success);
        // 4.2.对列表而言，返回列表
        PageModel<Teacher> teachers = teacherManager.getTeachers("1", String.valueOf(10000));
        for (Teacher t: teachers.getList()) {
            System.out.println(t);
        }
    }

    @Test
    public void deleteTeacher() {
        //1老师列表中的删除操作 默认带唯一主键(不可见)
        String teachId="11";
        //2.执行删除操作
        boolean success = teacherManager.deleteTeacher(teachId);
        //3.期望删除成功
        Assert.assertTrue(success);
        //4.对列表而言，删除之后不应该还显示在列表中，需要刷新列表
        PageModel<Teacher> teachers = teacherManager.getTeachers("1", String.valueOf(10000));
        for (Teacher t: teachers.getList()) {
            System.out.println(t);
        }
    }

    @Test
    public void getTeacher() {
        //1.登录时候需要显示用户信息
        //2.修改时候需要 - 因为需要先把database的数据显示到页面- 可能只是需要修改少量的数据列 / 如果是全部的还不如直接新增呢
        //3.删除时候（可选） - 先检查一下是否存在，确保数据库操作正常进行
        String tid="1";
        //
        Teacher teacher = teacherManager.getTeacher("1");
        //
        Assert.assertNotNull(teacher);
        Assert.assertEquals("admin",teacher.getUsername());
        System.out.println(teacher);
    }

    @Test
    public void getTeachers() {
        PageModel<Teacher> teachers = teacherManager.getTeachers("1", String.valueOf(10000));
        Assert.assertNotNull(teachers.getList());
        for (Teacher t: teachers.getList()) {
            System.out.println(t);
        }
    }

    @Test
    public void queryTeachers() {
        //1.模拟在列表也中 - 输入名字去查询
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("username","tomphson");
        //2.模拟点击查询按钮
        PageModel<Teacher> teacherPageModel = teacherManager.queryTeachers(parameters, "1", String.valueOf(10000));
        //3.显示查询结果
        Assert.assertNotNull(teacherPageModel.getList());
        for (Teacher t: teacherPageModel.getList()) {
            System.out.println(t);
        }

    }
}