package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Reservation;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public interface ReservationService {

    /**
     * 更新预约订单状态
     * @param reservation 预约订单
     */
    void updateStatus(Reservation reservation);

    /**
     * 条件查询
     * @param condition 条件map
     * @return 预约reservation
     */
    List<Reservation> findByCondition(Map<String,?> condition);

    /**
     * 分页
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @param map 条件
     * @return 页面
     */
    PageBean<Reservation> findByPage(int currentPage, int pageSize, Map<String,?> map);
    /**
     * 排序分页
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @param map 条件
     * @param order 排序策略
     * @return 页面
     */
    PageBean<Reservation> findByPage(int currentPage, int pageSize, Map<String,?> map,Map<String,Boolean> order);

    /**
     * 保存数据
     * @param reservation 预约
     */
    void save(Reservation reservation);

    /**
     * 更新预约 isFinish状态
     * @param reservation 预约
     */
    void updateFinish(Reservation reservation);
}
