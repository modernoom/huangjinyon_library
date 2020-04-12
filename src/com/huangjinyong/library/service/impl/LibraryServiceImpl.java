package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.LibraryDao;
import com.huangjinyong.library.dao.impl.LibraryDaoImpl;
import com.huangjinyong.library.entity.Library;
import com.huangjinyong.library.service.LibraryService;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class LibraryServiceImpl implements LibraryService {
    private LibraryDao dao=new LibraryDaoImpl();
    private PageHelper pageHelper=new PageHelper();
    @Override
    public List<Library> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Library> findAll(Map<String, ?> condition) {
        return dao.findAll(condition);
    }

    @Override
    public Library findById(int id) {
        return dao.findById(id);
    }

    @Override
    public PageBean<Library> findByPage(int currentPage,int pageSize) {
        return pageHelper.doPage(currentPage,pageSize,()->dao.findAll());
    }

    @Override
    public int save(Library library) {
       Map condition= new HashMap();
       condition.put("name",library.getName());
       List<Library> all = dao.findAll(condition);
       if(all.size()!=0){
           return -1;
       }
       dao.save(library);
       //查找刚刚插入了library主键id 并且返回
        List<Library> libraries= dao.findAll(condition);
        return libraries.get(0).getId();
    }

    @Override
    public boolean delete(Integer id)
    {
        return dao.delete(id)>0;
    }
}
