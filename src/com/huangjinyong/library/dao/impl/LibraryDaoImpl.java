package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.LibraryDao;
import com.huangjinyong.library.entity.Library;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;

/**
 * @author huangjinyong
 */
public class LibraryDaoImpl implements LibraryDao {
    JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Library> findAll(){
        return jdbcHelper.query("select * from library",Library.class);
    }
}
