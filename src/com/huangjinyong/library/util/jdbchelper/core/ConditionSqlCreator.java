package com.huangjinyong.library.util.jdbchelper.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class ConditionSqlCreator {

    public static String getConditionSql(String selectSql, Map<String,?> conditions){
        StringBuilder builder=new StringBuilder(selectSql);
        builder.append(" where 1=1");
        for (String s : conditions.keySet()) {
            builder.append(" and "+s+"=?");
        }

        return builder.toString();
    }

    public static Object[] getConditionVal(Map<String,?> condition){
        Object[] a= new Object[condition.size()];
        int i=0;
        for (String s : condition.keySet()) {
            a[i++]=condition.get(s);
        }
        return a;
    }

}
