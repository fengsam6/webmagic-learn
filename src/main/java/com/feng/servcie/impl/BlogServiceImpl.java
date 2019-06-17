package com.feng.servcie.impl;

import com.feng.dao.BlogDao;
import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Created by rf on 2019/4/3.
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    public Page<Blog> ListPage(Blog blog, int page, int szie) {
        Pageable pageable = PageRequest.of(page, szie);
        return blogDao.findAll(pageable);
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
