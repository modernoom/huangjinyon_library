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
    public void update(Seat seat) {
        String sql="update seat set status=?,icon=?,comfort_score=?,affect_score=?,total_score=?,usable_time=?,time_from=?," +
                "time_to=?,floor_id=?,library_id=?,seat_type_id=? where id=?";
        jdbcHelper.update(sql,seat.getStatus(),seat.getIcon(),seat.getComfortScore(),seat.getAffectScore(),seat.getTotalScore(),
                          seat.getUsableTime(),seat.getTimeFrom(),seat.getTimeTo(),seat.getFloorId(),seat.getLibraryId(),seat.getSeatTypeId(),seat.getId());
    }

    @Override
    public SeatType findTypeById(Integer seatTypeId) {
        List<SeatType> query = jdbcHelper.query("select * from seat_type where id=?", SeatType.class, seatTypeId);
        if(query.size()==0){
            return null;
        }
        return query.get(0);
    }

    @Override
    public Seat findById(int seatId) {
        List<Seat> query = jdbcHelper.query("select * from seat where id=?", Seat.class, seatId);
        if(query.size()!=0){
            return query.get(0);
        }
        return null;
    }

    @Override
    public void updateStatus(Seat seat) {
        jdbcHelper.update("update seat set status=? where id=?",seat.getStatus(),seat.getId());
    }

    @Override
    public void updateScore(Seat seat) {
        String sql="update seat set comfort_score=?, affect_score=?, total_score=?, score_num=? where id=?";
        jdbcHelper.update(sql,seat.getComfortScore(),seat.getAffectScore(),seat.getTotalScore(),seat.getScoreNum(),seat.getId());
    }

    @Override
    public void save(Seat seat) {
        String sql="insert into seat(library_id,floor_id,seat_type_id,usable_time,time_from,time_to) values (?,?,?,?,?,?)";
        jdbcHelper.update(sql,seat.getLibraryId(),seat.getFloorId(),seat.getSeatTypeId(),seat.getUsableTime(),seat.getTimeFrom(),seat.getTimeTo());
    }

    @Override
    public int delete(int id) {
        String sql="delete from seat where id=?";
        return jdbcHelper.update(sql,id);
    }

    @Override
    public SeatType findTypeByName(String typeName) {
        List<SeatType> query = jdbcHelper.query("select * from seat_type where name=?", SeatType.class, typeName);
        if(query.size()==0){
            return null;
        }
        return query.get(0);

    }

    @Override
    public void saveType(String typeName) {

    }
}
