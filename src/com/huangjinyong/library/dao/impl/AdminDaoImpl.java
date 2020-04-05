package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.AdminDao;
import com.huangjinyong.library.entity.Admin;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;

/**
 * @author huangjinyong
 */
public class AdminDaoImpl implements AdminDao {
    private JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Admin> findByName(String username, String password) {
        String sql="select * from admin where username=? and password=?";
        return jdbcHelper.query(sql,Admin.class,username,password);
    }
}
