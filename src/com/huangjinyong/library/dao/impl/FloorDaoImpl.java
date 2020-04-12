package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.FloorDao;
import com.huangjinyong.library.entity.Floor;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;

/**
 * @author huangjinyong
 */
public class FloorDaoImpl implements FloorDao {
    private JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Floor> findAll() {
        return jdbcHelper.query("select * from floor",Floor.class);
    }

    @Override
    public List<Floor> findAll(Integer libraryId) {
        String sql="select * from floor where library_id=?";
        return jdbcHelper.query(sql,Floor.class,libraryId);
    }

    @Override
    public Floor findById(Integer id) {
        return jdbcHelper.query("select * from floor where id=?",Floor.class,id).get(0);
    }

    @Override
    public void save(Floor floor) {
        jdbcHelper.update("insert into floor(name,library_id) values(?,?)",floor.getName(),floor.getLibraryId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcHelper.update("delete from floor where id=?",id);
    }
}
