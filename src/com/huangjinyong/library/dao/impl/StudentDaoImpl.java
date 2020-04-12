package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.StudentDao;
import com.huangjinyong.library.entity.Student;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.util.List;


/**
 * @author huangjinyong
 */
public class StudentDaoImpl implements StudentDao {
    private JdbcHelper jdbcHelper=new JdbcHelper();


    @Override
    public void saveWithException(Student student) throws MySQLIntegrityConstraintViolationException {
        String sql="insert into student(username,password,trueName) values (?,?,?)";
        try{
            jdbcHelper.insertWithException(sql,student.getUsername(),student.getPassword(),student.getTrueName());
        }catch (Exception e){
            e.getMessage();
            throw e;
        }
    }

    @Override
    public List<Student> findByName(String username, String password) {
        String sql="select * from student where username=? and password=?";
        return jdbcHelper.query(sql,Student.class,username,password);
    }

    @Override
    public List<Student> findAll() {
        String sql="select * from student";
        return jdbcHelper.query(sql,Student.class);
    }

    @Override
    public Integer findReservationStatus(Integer stuId) {
        String sql="select status from reservation where student_id=? and order_time=(select max(order_time) from reservation)";
        return jdbcHelper.queryAsObject(sql,Integer.class,stuId);
    }

    @Override
    public int updateStatus(Student student) {
        return jdbcHelper.update("update student set status=? where id=?",student.getStatus(),student.getId());
    }

    @Override
    public Student findById(Integer id) {
        List<Student> students = jdbcHelper.query("select * from student where id=?", Student.class, id);
        if(students.size()==1){
            return students.get(0);
        }
        return null;
    }

}
