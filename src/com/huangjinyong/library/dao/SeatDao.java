package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Seat;
import com.huangjinyong.library.entity.SeatType;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public interface SeatDao {
    /**
     * 查询所有
     * @return
     */
    List<Seat> findAll();

    /**
     * 条件查询
     * @param condition 条件
     * @return 结果
     */
    List<Seat> findByCondition(Map<String,?>condition);

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
