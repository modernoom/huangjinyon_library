package com.huangjinyong.library.entity;

/**
 * @author huangjinyong
 */
public interface User {

    <T> T getUser(Class<T> tClass);

    String getType();
}
