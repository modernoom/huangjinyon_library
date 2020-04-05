package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.StudentDao;
import com.huangjinyong.library.dao.impl.StudentDaoImpl;
import com.huangjinyong.library.entity.Student;
import com.huangjinyong.library.service.StudentService;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;

import java.util.List;

/**
 * @author huangjinyong
 */
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao=new StudentDaoImpl();

    @Override
    public boolean regist(Student student) {
        try{
            studentDao.saveWithException(student);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Student login(String username,String password) {
        List<Student> students = studentDao.findByName(username, password);
        if(students.size()<=0){
            return null;
        }
        return students.get(0);
    }

    @Override
    public PageBean<Student> findByPage(Integer currentPage, Integer pageSize) {
        PageHelper pageHelper = new PageHelper();
        PageBean<Student> page = pageHelper.doPage(currentPage, pageSize, () -> studentDao.findAll());

        return page;
    }
}
