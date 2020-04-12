package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.ReservationDao;
import com.huangjinyong.library.entity.Reservation;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huangjinyong
 */
public class ReservationDaoImpl implements ReservationDao {
    private JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Reservation> findAll(Map<String,?> map) {
        String sql="select * from reservation";
        return jdbcHelper.queryByCondition(sql,Reservation.class,map);
    }

    @Override
    public List<Reservation> findAll(Map<String, ?> map, Map<String, Boolean> order) {
        String sql="select * from reservation";
        return jdbcHelper.queryByCondition(sql,Reservation.class,map,order);
    }

    @Override
    public void save(Reservation reservation) {
        String sql="insert into reservation(seat_id,student_id,order_date,time_from,time_to,order_time) values (?,?,?,?,?,?)";
        jdbcHelper.update(sql,reservation.getSeatId(),reservation.getStudentId(),reservation.getOrderDate(),
                              reservation.getTimeFrom(),reservation.getTimeTo(),reservation.getOrderTime());

    }

    @Override
    public void updateStatus(Reservation reservation) {
        jdbcHelper.update("update reservation set status=?, is_score=? where id=?",reservation.getStatus(),reservation.getIsScore(),reservation.getId());
    }

    @Override
    public void updateFinish(Reservation reservation) {
        jdbcHelper.update("update reservation set is_finish=? where id=?",reservation.getIsFinish(),reservation.getId());
    }
}
