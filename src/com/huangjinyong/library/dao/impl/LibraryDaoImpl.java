package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.LibraryDao;
import com.huangjinyong.library.entity.Library;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class LibraryDaoImpl implements LibraryDao {
    JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Library> findAll(){
        return jdbcHelper.query("select * from library",Library.class);
    }

    @Override
    public List<Library> findAll(Map<String, ?> condition) {
        return jdbcHelper.queryByCondition("select * from library",Library.class,condition);
    }

    @Override
    public Library findById(int id) {
        return jdbcHelper.query("select * from library where id=?",Library.class,id).get(0);
    }

    @Override
    public void save(Library library) {
        jdbcHelper.update("insert into library(name) values(?)",library.getName());
    }

    @Override
    public int delete(Integer id) {
        return jdbcHelper.update("delete from library where id=?",id);
    }
}
