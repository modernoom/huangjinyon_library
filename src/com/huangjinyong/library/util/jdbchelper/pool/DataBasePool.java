package com.huangjinyong.library.util.jdbchelper.pool;

import com.huangjinyong.library.util.jdbchelper.exception.NoUsableConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 线程池
 */
public class DataBasePool {
    private Config config;
    private int maxConnectionCount;//最大连接
    private int activeConnectionCount;//常驻连接
    private int currentCount;//当前连接数
    private LinkedList<Connection> pool;//还未被使用的连接对象队列


    public DataBasePool(Config config){
        this.config=config;
        maxConnectionCount=config.getMaxConnectionCount();
        activeConnectionCount=config.getActiveConnectionCount();
        init(config);
    }

    /**
     * 新建连接对象
     * @return
     */
    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection(config.getUrl(),config.getUsername(),config.getPassword());
    }

    /**
     * 根据配置初始化数据库连接池
     * @param config
     */
    private void init(Config config){
        try {
            Class.forName(config.getDriverClass());
            pool=new LinkedList();
            for (int i = 0; i < activeConnectionCount; i++) {
                pool.add(newConnection());
            }
            currentCount=activeConnectionCount;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取connection方法
     * @return
     */
    public Connection getConnection() throws SQLException {
        Connection connection;
        if(pool.size()!=0){
            connection= pool.pollFirst();
        }else if(currentCount<maxConnectionCount){
            currentCount++;
            connection=newConnection();
        }else{
            throw new NoUsableConnectionException();
        }
        
        return connection;

    }

    /**
     * 归还Conenction方法
     */
    public void close(Connection connection) throws SQLException {
        if(pool.size()>=activeConnectionCount){
            connection.close();
        }else{
            pool.add(connection);
        }
    }






}

