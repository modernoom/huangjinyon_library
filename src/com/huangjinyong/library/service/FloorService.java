package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Floor;

import java.util.List;

/**
 * @author huangjinyong
 */
public interface FloorService {
    /**
     * find floor
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
