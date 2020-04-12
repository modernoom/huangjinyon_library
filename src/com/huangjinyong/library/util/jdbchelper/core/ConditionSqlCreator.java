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
    public static String getConditionSql(String selectSql, Map<String,?> conditions,Map<String,Boolean> order){
        StringBuilder builder=new StringBuilder(selectSql);
        builder.append(" where 1=1");
        for (String s : conditions.keySet()) {
            builder.append(" and "+s+"=?");
        }
        builder.append(" order by ");
        int i=1;
        for (String s : order.keySet()) {
            String policy;
            if(order.get(s)){
                policy="asc";
            }else {
                policy="desc";
            }
            if(i==1){
                i++;
                builder.append(s).append(" ").append(policy);
                continue;
            }
            i++;
            builder.append(", ").append(s).append(" ").append(policy);
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
