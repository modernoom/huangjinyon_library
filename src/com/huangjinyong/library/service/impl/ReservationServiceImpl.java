package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.ReservationDao;
import com.huangjinyong.library.dao.impl.ReservationDaoImpl;
import com.huangjinyong.library.entity.Reservation;
import com.huangjinyong.library.service.ReservationService;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class ReservationServiceImpl implements ReservationService {
    ReservationDao dao = new ReservationDaoImpl();
    PageHelper pageHelper=new PageHelper();

    @Override
    public void updateStatus(Reservation reservation) {
        HashSet<String> set = new HashSet();
        set.add("status");
        dao.updateStatus(reservation);
    }

    @Override
    public List<Reservation> findByCondition(Map<String, ?> condition) {
        return dao.findAll(condition);
    }

    @Override
    public PageBean<Reservation> findByPage(int currentPage, int pageSize, Map<String,?> map) {


        return pageHelper.doPage(currentPage,pageSize,()->dao.findAll(map));
    }

    @Override
    public PageBean<Reservation> findByPage(int currentPage, int pageSize, Map<String, ?> map, Map<String, Boolean> order) {
        return pageHelper.doPage(currentPage,pageSize,()->dao.findAll(map,order));
    }

    @Override
    public void save(Reservation reservation) {
        dao.save(reservation);
    }

    @Override
    public void updateFinish(Reservation reservation) {
        dao.updateFinish(reservation);
    }
}
