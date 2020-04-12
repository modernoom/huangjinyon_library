package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Floor;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;

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

    /**
     * id查询
     * @param id id
     * @return floor对象
     */
    Floor findById(Integer id);

    /**
     * 分页查找指定lib id的floor
     * @param i
     * @param pageSize
     * @param id
     * @return
     */
    PageBean<Floor> findByPage(int i, int pageSize, int id);

    /**
     * 保存楼层
     * @param floor 楼层
     * @return 是否成功
     */
    boolean save(Floor floor);

    /**
     * 删除
     * @param id id
     * @return
     */
    boolean delete(Integer id);
}
