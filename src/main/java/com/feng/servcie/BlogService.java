package com.feng.servcie;

import com.feng.entity.Blog;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
public interface BlogService {
    PageInfo<Blog> ListPage(Blog blog, int page, int szie);

    Blog add(Blog blog);
}
