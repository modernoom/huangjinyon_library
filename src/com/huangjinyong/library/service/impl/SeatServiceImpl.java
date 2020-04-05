package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.SeatDao;
import com.huangjinyong.library.dao.impl.SeatDaoImpl;
import com.huangjinyong.library.entity.Seat;
import com.huangjinyong.library.entity.SeatType;
import com.huangjinyong.library.service.SeatService;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author huangjinyong
 */
public class SeatServiceImpl implements SeatService {
    private SeatDao dao=new SeatDaoImpl();
    private PageHelper pageHelper=new PageHelper();
    @Override
    public PageBean<Seat> findByPage(int currentPage, int pageSize) {
        PageBean<Seat> page = pageHelper.doPage(currentPage, pageSize, () -> dao.findAll());
        List<Seat> list = page.getList();
        Map map=new HashMap();
        for (Seat seat : list) {
            map.put("id",seat.getSeatTypeId());
            List<SeatType> type = dao.findAllType(map);
            if(type.size()>0){
                seat.setType(type.get(0).getName());
            }
            map.clear();
        }
        return page;
    }

    @Override
    public PageBean<Seat> findByPage(int currentPage, int pageSize, Map<String, ?> condition) {
        PageBean<Seat> page = pageHelper.doPage(currentPage, pageSize, () -> dao.findByCondition(condition));
        List<Seat> list = page.getList();
        Map map=new HashMap();
        for (Seat seat : list) {
            map.put("id",seat.getSeatTypeId());
            List<SeatType> type = dao.findAllType(map);
            if(type.size()>0){
                seat.setType(type.get(0).getName());
                seat.setType(type.get(0).getName());
                map.clear();
            }
        }
        return page;
    }

    @Override
    public List<SeatType> findAllType() {
        return dao.findAllType();
    }

    @Override
    public List<SeatType> findAllType(Map<String, ?> condition) {
        return dao.findAllType(condition);
    }
}
