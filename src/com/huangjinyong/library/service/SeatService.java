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
     * 条件查询
     * @param condition 条件map
     * @return seat
     */
    List<Seat> findByCondition(Map<String,?>condition);
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
     * id 查询
     * @param seatTypeId id
     * @return 座位类型
     */
    SeatType findTypeById(Integer seatTypeId);
    /**
     * 更新
     * @param currentSeat 椅子
     */
    void update(Seat currentSeat);

    /**
     * id 查询
     * @param seatId id
     * @return seat
     */
    Seat findById(int seatId);

    /**
     * 更新椅子状态
     * @param seat 椅子
     */
    void updateStatus(Seat seat);

    /**
     * 计算比更新座位评分
     * @param reservationSeat
     * @param comfortScore
     * @param affectScore
     */
    void updateScore(Seat reservationSeat, Integer comfortScore, Integer affectScore);

    /**
     * 保存座位
     * @param seat 座位
     */
    void save(Seat seat);

    /**
     * 移除
     * @param id id
     * @return 是否成功
     */
    boolean delete(int id);

    /**
     * 更新图片
     * @param id id
     * @param imgName 图片名称
     * @return
     */
    boolean updateImg(int id, String imgName);

    /**
     * 保存 类型
     * @param typeName 座位类型
     * @return 是否成功 存在同名类型 返回失败
     */
    boolean saveType(String typeName);
}
