package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.SeatDao;
import com.huangjinyong.library.dao.impl.SeatDaoImpl;
import com.huangjinyong.library.entity.Seat;
import com.huangjinyong.library.entity.SeatType;
import com.huangjinyong.library.service.SeatService;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;

import java.util.List;
import java.util.Map;


/**
 * @author huangjinyong
 */
public class SeatServiceImpl implements SeatService {
    private SeatDao dao=new SeatDaoImpl();
    private PageHelper pageHelper=new PageHelper();

    @Override
    public List<Seat> findByCondition(Map<String, ?> condition) {
        return dao.findByCondition(condition);
    }

    @Override
    public PageBean<Seat> findByPage(int currentPage, int pageSize) {
        PageBean<Seat> page = pageHelper.doPage(currentPage, pageSize, () -> dao.findAll());
        List<Seat> list = page.getList();
        for (Seat seat : list) {
            SeatType type = dao.findTypeById(seat.getSeatTypeId());
            seat.setType(type.getName());
        }
        return page;
    }

    @Override
    public PageBean<Seat> findByPage(int currentPage, int pageSize, Map<String, ?> condition) {
        PageBean<Seat> page = pageHelper.doPage(currentPage, pageSize, () -> dao.findByCondition(condition));
        List<Seat> list = page.getList();
        for (Seat seat : list) {
            SeatType type = dao.findTypeById(seat.getSeatTypeId());
            seat.setType(type.getName());
        }
        return page;
    }

    @Override
    public List<SeatType> findAllType() {
        return dao.findAllType();
    }

    @Override
    public SeatType findTypeById(Integer seatTypeId) {
        return dao.findTypeById(seatTypeId);
    }


    @Override
    public void update(Seat currentSeat) {
        dao.update(currentSeat);
    }

    @Override
    public Seat findById(int seatId) {

        return dao.findById(seatId);
    }

    @Override
    public void updateStatus(Seat seat) {
        dao.updateStatus(seat);
    }

    @Override
    public void updateScore(Seat reservationSeat, Integer comfortScore, Integer affectScore) {
        //获取评分次数
        int scoreNum = reservationSeat.getScoreNum();
        //获取该椅子之前的评分
        double preComfort=reservationSeat.getComfortScore();
        double preAffect=reservationSeat.getAffectScore();
        double preTotal=reservationSeat.getTotalScore();
        //计算目前评分
        double currentComfort=(preComfort*scoreNum+comfortScore)/(scoreNum+1);
        double currentAffect=(preAffect*scoreNum+affectScore)/(scoreNum+1);
        double currentTotal=(currentComfort+currentAffect)/2;
        //更新评分
        reservationSeat.setComfortScore(currentComfort);
        reservationSeat.setAffectScore(currentAffect);
        reservationSeat.setTotalScore(currentTotal);
        reservationSeat.setScoreNum(scoreNum+1);
        dao.updateScore(reservationSeat);
    }

    @Override
    public void save(Seat seat) {
        dao.save(seat);
    }

    @Override
    public boolean delete(int id) {
        int fluentRow=dao.delete(id);
        return fluentRow>0;
    }

    @Override
    public boolean updateImg(int id, String imgName) {
        //查找文件是否存在
        Seat seat = dao.findById(id);
        if(seat==null){
            return false;
        }
        seat.setIcon(imgName);
        dao.update(seat);
        return true;
    }

    @Override
    public boolean saveType(String typeName) {
        SeatType type = dao.findTypeByName(typeName);
        if(type!=null){
            return false;
        }
        dao.saveType(typeName);
        return true;
    }
}
