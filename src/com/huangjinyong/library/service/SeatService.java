package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Seat;
import com.huangjinyong.library.entity.SeatType;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public interface SeatService {
    /**
     * 分页
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @return 分页信息
     */
    PageBean<Seat>findByPage(int currentPage,int pageSize);

    /**
     * 按条件查询并且分页
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @param condition  条件
     * @return 分页信息
     */
    PageBean<Seat>findByPage(int currentPage, int pageSize, Map<String,?> condition);
    /**
     * 查找座位的类型
     * @return types
     */
    List<SeatType> findAllType();

    /**
     * 查找座位的类型
     * @param condition 条件
     * @return types
     */
    List<SeatType> findAllType(Map<String,?>condition);

}
