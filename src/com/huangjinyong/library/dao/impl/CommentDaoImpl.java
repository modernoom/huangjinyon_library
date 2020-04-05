package com.huangjinyong.library.dao.impl;

import com.huangjinyong.library.dao.CommentDao;
import com.huangjinyong.library.entity.Comment;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public class CommentDaoImpl implements CommentDao {
    JdbcHelper jdbcHelper=new JdbcHelper();

    @Override
    public List<Comment> findAll(Map<String, ?> map) {
        return jdbcHelper.queryByCondition("select * from comment",Comment.class,map);
    }
}
