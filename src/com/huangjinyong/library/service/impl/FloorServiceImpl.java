package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.FloorDao;
import com.huangjinyong.library.dao.impl.FloorDaoImpl;
import com.huangjinyong.library.entity.Floor;
import com.huangjinyong.library.service.FloorService;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;

import java.util.List;

/**
 * @author huangjinyong
 */
public class FloorServiceImpl implements FloorService {
    private FloorDao dao=new FloorDaoImpl();
    PageHelper pageHelper=new PageHelper();
    @Override
    public List<Floor> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Floor> findAll(Integer libraryId) {
        return dao.findAll(libraryId);

    }

    @Override
    public Floor findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public PageBean<Floor> findByPage(int i, int pageSize, int id) {
        return pageHelper.doPage(i,pageSize,()->findAll(id));
    }

    @Override
    public boolean save(Floor floor) {
        //查找在该图书馆下有无同名
        List<Floor> floors = dao.findAll(floor.getLibraryId());
        for (Floor floor1 : floors) {
            if(floor1.getName().equals(floor.getName())){
                return false;
            }
        }
        dao.save(floor);
        return true;
    }


    @Override
    public boolean delete(Integer id) {
        int fluentLine=dao.delete(id);
        //影响行数为0，楼层不存在
        if(fluentLine==0){
            return false;
        }
        return true;
    }
}
