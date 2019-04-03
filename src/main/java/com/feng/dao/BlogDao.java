package com.feng.dao;

import com.feng.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rf on 2019/4/3.
 */
public interface BlogDao extends JpaRepository<Blog,Long>{
}
