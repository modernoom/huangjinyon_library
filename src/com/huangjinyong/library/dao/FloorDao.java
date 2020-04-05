package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Floor;

import java.util.List;

/**
 * @author huangjinyong
 */
public interface FloorDao {
    /**
     * find all
     * @return floor
     */
    List<Floor> findAll();
    /**
     * 根据图书馆id查询所有楼层
     * @param libraryId id
     * @return floor
     */
    List<Floor> findAll(Integer libraryId);
}
