package com.feng.servcie.impl;

import com.feng.dao.FilmDao;
import com.feng.entity.Film;
import com.feng.servcie.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
@Slf4j
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmDao filmDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Film add(Film film) {
        String sql = "INSERT INTO tb_film(`img_url`, `score`, `title`, `url`) VALUES('#1','#2','#3','#4')";
        sql =sql.replaceAll("#1",film.getImgUrl());
        sql =sql.replaceAll("#2",film.getScore());
        sql =sql.replaceAll("#3",film.getTitle());
        sql =sql.replaceAll("#4",film.getUrl());
        jdbcTemplate.update(sql);
//        filmDao.saveAndFlush(film);
        return film;
    }

    @Override
    public Page<Film> ListPage(Film search, int num, int size) {
        Pageable pageable = PageRequest.of(num - 1, size);
        Specification specification = (Specification) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (search != null && StringUtils.isNotBlank(search.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + search.getTitle() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return filmDao.findAll(specification, pageable);
    }
}
