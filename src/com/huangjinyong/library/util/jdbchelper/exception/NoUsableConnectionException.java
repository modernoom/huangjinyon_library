package com.huangjinyong.library.util.jdbchelper.exception;

import java.sql.SQLException;

public class NoUsableConnectionException extends SQLException {

    public NoUsableConnectionException(){
        super("当前没有可用的Connection对象");
    }
}
