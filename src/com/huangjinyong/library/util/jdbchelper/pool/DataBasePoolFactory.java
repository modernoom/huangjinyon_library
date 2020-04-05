package com.huangjinyong.library.util.jdbchelper.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * 单例程序池工厂
 */
public class DataBasePoolFactory {
    private static DataBasePool pool;
    private static final String DRIVERCLASS="driverClass";
    private static final String URL="url";
    private static final String USERNAME="username";
    private static final String PASSWORD="password";
    private static final String MAXCONNECTION="maxConnectionCount";
    private static final String ACTIVECONNECTION="activeConnectionCount";

    /**
     * 解析配置文件
     * @return Config
     */
    private static Config parseProp() throws IOException {
        InputStream inputStream = DataBasePoolFactory.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        Map<String,String> map =(Map)properties;
        Config config = new Config();

        for (String propKey : map.keySet()) {
            String propValue=map.get(propKey);
            switch (propKey){
                case DRIVERCLASS:config.setDriverClass(propValue);
                break;
                case URL:config.setUrl(propValue);
                break;
                case USERNAME:config.setUsername(propValue);
                break;
                case PASSWORD:config.setPassword(propValue);
                break;
                case MAXCONNECTION:config.setMaxConnectionCount(Integer.parseInt(propValue));
                break;
                case ACTIVECONNECTION:config.setActiveConnectionCount(Integer.parseInt(propValue));
                break;
            }
        }

        return config;
    }

    /**
     * 获取线程池
     */
    public static DataBasePool getPool()  {
        //内存中已有线程池对象 直接返回
        if(pool!=null){
            return pool;
        }
        //创建线程池对象
        Config config = null;
        try {
            config = parseProp();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool = new DataBasePool(config);
        return pool;
    }

}
