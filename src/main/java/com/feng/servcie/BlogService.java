package com.feng.servcie;

import com.feng.entity.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
public interface BlogService {
    List<Blog> list();

    Blog add(Blog blog);
}
