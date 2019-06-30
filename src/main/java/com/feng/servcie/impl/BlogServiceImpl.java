package com.feng.servcie.impl;

import com.feng.dao.BlogDao;
import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by rf on 2019/4/3.
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    public PageInfo<Blog> ListPage(Blog blog, int num, int szie) {
        PageHelper.startPage(num,szie);
       List<Blog> blogList = blogDao.findAll();
        return new PageInfo<Blog>(blogList);
    }

    @Override
    public Blog add(Blog blog) {
        try {
            blogDao.save(blog);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            e.printStackTrace();
        }

        return blog;
    }
}
