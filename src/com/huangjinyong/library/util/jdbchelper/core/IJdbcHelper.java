package com.huangjinyong.library.util.jdbchelper.core;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 定义了JdbcHelper功能的接口
 * @author  haungjinyong
 */
public interface IJdbcHelper {
    /**
     * 查询返回List集合
     * @param sql
     * @param tClass
     * @param objs
     * @param <T>
     * @return
     */
    <T> List<T> query(String sql, Class<T> tClass, Object... objs);

    /**
     *条件查询
     * @param sql 查询所有的sql
     * @param tClass 要封装的类
     * @param condition 封装条件的map
     * @param <T>
     * @return
     */
    <T> List<T> queryByCondition(String sql, Class<T> tClass, Map<String,?> condition);
    /**
     *条件排序查询
     * @param sql 查询所有的sql
     * @param tClass 要封装的类
     * @param condition 封装条件的map
     * @param order 排序策略 false 降序 true升序
     * @param <T>
     * @return
     */
    <T> List<T> queryByCondition(String sql, Class<T> tClass, Map<String,?> condition,Map<String,Boolean> order);

    /**
     * 查找聚合函数，或某一条记录的某一字段。不能应用于封装实体类
     * @param sql 查询语句
     * @param tClass 要返回的类的Class对象
     * @param <T> 泛型
     * @return 返回值
     */
    <T> T queryAsObject(String sql,Class<T> tClass,Object ... objs);

    /**
     * 增删改方法
     * @param sql
     * @param objs
     * @return 影响的行数
     */
    int update(String sql, Object... objs);

    /**
     * 关闭连接
     */
    void close();

    /**
     * 当插入时有相同记录会抛异常的insert方法
     * @param sql
     * @param objs
     */
    void insertWithException(String sql,Object... objs) throws MySQLIntegrityConstraintViolationException;
}
