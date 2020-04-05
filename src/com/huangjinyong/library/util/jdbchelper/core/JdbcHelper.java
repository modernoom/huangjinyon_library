package com.huangjinyong.library.util.jdbchelper.core;

import com.huangjinyong.library.util.jdbchelper.pool.DataBasePool;
import com.huangjinyong.library.util.jdbchelper.pool.DataBasePoolFactory;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class JdbcHelper implements IJdbcHelper{
    private DataBasePool pool= DataBasePoolFactory.getPool();
    private Connection connection;


    @Override
    public <T> List<T> query(String sql, Class<T> tClass, Object... objs) {
        List<T> list = new ArrayList();

        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql, objs);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            Method[] methods = tClass.getMethods();
            Map<Integer, Method> map = getMap(methods, metaData);
            while (resultSet.next()) {
                T t = tClass.newInstance();
                for (Integer index : map.keySet()) {
                    Object obj = resultSet.getObject(index);
                    if(obj==null){
                        continue;
                    }
                    Method method = map.get(index);
                    Class<?> paramClass = method.getParameterTypes()[0];
                    method.invoke(t, paramClass.cast(obj));
                }
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                pool.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public <T> List<T> queryByCondition(String sql, Class<T> tClass, Map<String, ?> condition) {
        String conditoinSql = ConditionSqlCreator.getConditionSql(sql,condition);
        Object[] conditionVal = ConditionSqlCreator.getConditionVal(condition);
        return query(conditoinSql,tClass,conditionVal);
    }

    @Override
    public void update(String sql, Object... objs) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql, objs);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pool.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertWithException(String sql,Object... objs) throws MySQLIntegrityConstraintViolationException {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql, objs);
            preparedStatement.executeUpdate();
        }catch (MySQLIntegrityConstraintViolationException e) {
            throw e;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                pool.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close(){
        try {
            pool.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String colToProp(String columnName)
    {
        return columnName.replaceAll("_","");

    }

    private String funnameToProp(String funname){
        String s= funname.replaceAll("set","");
        String propName = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toLowerCase());
        return propName;
    }

    private Map<Integer,Method> getMap(Method[] methods, ResultSetMetaData metaData) throws SQLException {
        Map<Integer,Method> map=new HashMap();
        for (int i = 1; i <=metaData.getColumnCount() ; i++) {
            String colName=colToProp(metaData.getColumnName(i));
            for(Method method:methods){
                String funcName=method.getName();
                if(!funcName.startsWith("set")){
                    continue;
                }
                String propName=funnameToProp(funcName);
                if(colName.equalsIgnoreCase(propName)){
                    map.put(i,method);
                    break;
                }
            }
        }

        return map;
    }

    private PreparedStatement getPreparedStatement(String sql,Object... objs) throws SQLException {
        connection=pool.getConnection();
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(sql);
        }catch (MySQLIntegrityConstraintViolationException e){
            throw e;
        }
        for (int i = 1; i <=objs.length ; i++) {
            preparedStatement.setObject(i,objs[i-1]);
        }
        System.out.println(preparedStatement);
        return preparedStatement;
    }

}
