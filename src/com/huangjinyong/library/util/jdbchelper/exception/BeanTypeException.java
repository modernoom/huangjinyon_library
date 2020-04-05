package com.huangjinyong.library.util.jdbchelper.exception;

public class BeanTypeException extends Exception {
    public BeanTypeException(){
        super("实体类属性与对应列的类型不一致");
    }
}
