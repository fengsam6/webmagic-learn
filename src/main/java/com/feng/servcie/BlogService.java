package com.feng.servcie;

import com.feng.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
public interface BlogService {
    Page<Blog> ListPage(Blog blog, int page, int szie);

    Blog add(Blog blog);
}
