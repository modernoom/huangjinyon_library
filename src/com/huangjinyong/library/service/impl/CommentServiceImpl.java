package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.CommentDao;
import com.huangjinyong.library.dao.impl.CommentDaoImpl;
import com.huangjinyong.library.entity.Comment;
import com.huangjinyong.library.service.CommentService;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class CommentServiceImpl implements CommentService {
    CommentDao dao = new CommentDaoImpl();
    PageHelper pageHelper=new PageHelper();
    @Override
    public PageBean<Comment> findByPage(int currentPage,int pageSize,Map<String,?> condition) {
        return pageHelper.doPage(currentPage,pageSize,()->dao.findAll(condition));
    }

    @Override
    public List<Comment> findByCondition(Map map) {
        return dao.findAll(map);
    }

    @Override
    public void save(Comment comment) {
        dao.save(comment);
    }
}
