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
     * 更新
     * @param currentSeat 椅子
     */
    void update(Seat currentSeat);

    /**
     * 根据type id 查询seat type
     * @param seatTypeId type id
     * @return type
     */
    SeatType findTypeById(Integer seatTypeId);

    /**
     * 根据id 查询seat
     * @param seatId  id
     * @return seat
     */
    Seat findById(int seatId);

    /**
     * 更新椅子状态
     * @param seat 椅子
     */
    void updateStatus(Seat seat);

    /**
     * 更新分数
     * @param seat 座位
     */
    void updateScore(Seat seat);

    /**
     * 保存
     * @param seat
     */
    void save(Seat seat);

    /**
     * 移除
     * @param id id
     * @return 影响行数
     */
    int delete(int id);

    /**
     * 名字查找
     * @param typeName 类型名称
     * @return 类型实例
     */
    SeatType findTypeByName(String typeName);

    /**
     * 保存类型
     * @param typeName 类型名称
     */
    void saveType(String typeName);
}
