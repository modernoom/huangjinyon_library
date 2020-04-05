package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Reservation;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;

import java.util.Map;

/**
 * @author huangjinyong
 */
public interface ReservationService {
    /**
     * 分页
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @param map 条件
     * @return 页面
     */
    PageBean<Reservation> findByPage(int currentPage, int pageSize, Map<String,?> map);

    /**
     * 保存数据
     * @param reservation 预约
     */
    void save(Reservation reservation);
}
