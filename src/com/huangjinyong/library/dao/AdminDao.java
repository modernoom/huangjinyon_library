package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Admin;

import java.util.List;

/**
 * @author huangjinyong
 */
public interface AdminDao {

    /**
     * 按管理员用户名和密码查找
     * @param username 用户名
     * @param password 密码
     * @return 管理员
     */
    List<Admin> findByName(String username, String password);
}
