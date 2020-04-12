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
     * @return 所有记录
     */
    List<Student> findAll();

    /**
     * 查找 与用户关联的最新订单的状态
     * @param stuId 用户id
     * @return 状态 1：未结束 0：已结束
     */
    Integer findReservationStatus(Integer stuId);

    /**
     * 更新用户状态
     * @param student 用户
     * @return 影响的行数
     */
    int updateStatus(Student student);

    /**
     * id 查询
     * @param id
     */
    Student findById(Integer id);
}
