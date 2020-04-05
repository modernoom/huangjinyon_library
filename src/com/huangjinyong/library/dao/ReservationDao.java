package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Reservation;

import java.util.List;
import java.util.Map;

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
     * 保存
     * @param reservation 预约
     */
    void save(Reservation reservation);
}
