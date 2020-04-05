package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.FloorDao;
import com.huangjinyong.library.dao.impl.FloorDaoImpl;
import com.huangjinyong.library.entity.Floor;
import com.huangjinyong.library.service.FloorService;

import java.util.List;

/**
 * @author huangjinyong
 */
public class FloorServiceImpl implements FloorService {
    private FloorDao dao=new FloorDaoImpl();

    @Override
    public List<Floor> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Floor> findAll(Integer libraryId) {
        return dao.findAll(libraryId);

    }
}
