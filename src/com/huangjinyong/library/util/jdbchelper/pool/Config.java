package com.huangjinyong.library.util.jdbchelper.pool;

/**
 * 封装配置信息
 */
public class Config {
    private String username;
    private String password;
    private String url;
    private String driverClass;
    private int maxConnectionCount=10;//默认最大连接数10
    private int activeConnectionCount=5;//默认常驻连接数5

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public int getMaxConnectionCount() {
        return maxConnectionCount;
    }

    public void setMaxConnectionCount(int maxConnectionCount) {
        this.maxConnectionCount = maxConnectionCount;
    }

    public int getActiveConnectionCount() {
        return activeConnectionCount;
    }

    public void setActiveConnectionCount(int activeConnectionCount) {
        this.activeConnectionCount = activeConnectionCount;
    }
}
