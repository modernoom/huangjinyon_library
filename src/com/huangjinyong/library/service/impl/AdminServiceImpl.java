package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.AdminDao;
import com.huangjinyong.library.dao.impl.AdminDaoImpl;
import com.huangjinyong.library.entity.Admin;
import com.huangjinyong.library.service.AdminService;

import java.util.List;

/**
 * @author huangjinyong
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao=new AdminDaoImpl();

    @Override
    public Admin login(String username,String password) {
        List<Admin> admins = adminDao.findByName(username, password);
        if(admins.size()<=0){
            return null;
        }
        return admins.get(0);
    }
}
