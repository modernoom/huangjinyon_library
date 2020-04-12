package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Reservation;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huangjinyong
 */
public interface ReservationDao {
    /**
     * findAll
     * @param map 条件
     * @return All
     */
    List<Reservation> findAll(Map<String,?> map);

    /**
     * 排序findAll
     * @param map 条件
     * @param order 排序策略
     * @return All
     */
    List<Reservation> findAll(Map<String,?> map,Map<String,Boolean> order);

    /**
     * 保存
     * @param reservation 预约
     */
    void save(Reservation reservation);

    /**
     * 更新
     * @param reservation 要更新的实体类
     */
    void updateStatus(Reservation reservation);

    /**
     * 更新预约 isFinish状态
     * @param reservation 预约
     */
    void updateFinish(Reservation reservation);
}
