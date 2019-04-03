package com.feng.servcie.impl;

import com.feng.dao.BlogDao;
import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    public List<Blog> list(){
        return blogDao.findAll();
    }

    @Override
    public Blog add(Blog blog) {
        blogDao.save(blog);
        return blog;
    }
}
