package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.ReservationDao;
import com.huangjinyong.library.entity.Reservation;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class ReservationDaoImpl implements ReservationDao {
    JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Reservation> findAll(Map<String,?> map) {
        String sql="select * from reservation";
        return jdbcHelper.queryByCondition(sql,Reservation.class,map);
    }

    @Override
    public void save(Reservation reservation) {
        jdbcHelper.update("insert into reservation(seat_id,student_id) values (?,?)",reservation.getSeatId(),reservation.getStudentId());
    }
}
