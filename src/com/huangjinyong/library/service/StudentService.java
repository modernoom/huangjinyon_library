package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Student;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;


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

    /**
     * 当前用户是否存在未完成预约
     * @param student 用户
     * @return true->存在
     */
    boolean haveUnDone(Student student);

    /**
     * 更新用户状态
     * @param student 用户
     * @return
     */
    boolean updateStatus(Student student);
}
