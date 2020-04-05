package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Student;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;

import java.util.List;

/**
 * @author huangjinyong
 */
public interface StudentService {
    /**
     * 学生注册
     * @param student 学生
     * @return 是否注册成功
     */
    boolean regist(Student student);

    /**
     * 学生登录
     * @param username 用户名
     * @param password 密码
     * @return student
     */
    Student login(String username,String password);

    /**
     * 分页查询
     * @param currentPage 当前页数
     * @param pageSize 每页记录数
     * @return  students
     */
    PageBean<Student> findByPage(Integer currentPage, Integer pageSize);
}
