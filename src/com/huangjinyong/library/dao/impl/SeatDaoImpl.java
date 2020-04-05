package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.SeatDao;
import com.huangjinyong.library.entity.Seat;
import com.huangjinyong.library.entity.SeatType;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class SeatDaoImpl implements SeatDao {
    private JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Seat> findAll() {
        String sql="select * from seat";
        List<Seat> seats = jdbcHelper.query(sql, Seat.class);
        return seats;
    }

    @Override
    public List<Seat> findByCondition(Map<String, ?> condition) {
        return jdbcHelper.queryByCondition("select * from Seat",Seat.class,condition);
    }

    @Override
    public List<SeatType> findAllType() {
        return jdbcHelper.query("select * from seat_type",SeatType.class);
    }

    @Override
    public List<SeatType> findAllType(Map<String,?> condition) {

        return jdbcHelper.queryByCondition("select * from seat_type",SeatType.class,condition);
    }
}
