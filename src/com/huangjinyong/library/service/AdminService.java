package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Admin;

/**
 * @author huangjinyong
 */
public interface AdminService {
    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return admin
     */
    Admin login(String username,String password);
}
