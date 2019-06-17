package com.feng.dao;

import com.feng.entity.Blog;
import com.feng.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by rf on 2019/4/3.
 */
public interface FilmDao extends JpaRepository<Film,Long>,JpaSpecificationExecutor<Film> {
}
