package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Student;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author huangjinyong
 */
public interface StudentDao {

    /**
     * 注册时使用的插入方法，若插入时用户名重复则抛异常
     * @param student 学生
     * @throws MySQLIntegrityConstraintViolationException 插入时具有相同记录异常
     */
    void saveWithException(Student student) throws MySQLIntegrityConstraintViolationException;

    /**
     * 按student用户名和密码查找
     * @param username 用户名
     * @param password 密码
     * @return student
     */
    List<Student> findByName(String username, String password);

    /**
     * 返回所有记录
     * @return s所有记录
     */
    List<Student> findAll();


}
