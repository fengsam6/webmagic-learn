package com.feng.servcie.impl;

import com.feng.dao.FilmDao;
import com.feng.entity.Film;
import com.feng.servcie.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String sql = "INSERT INTO " +
                "tb_film(`img_url`, `score`, `title`, `url`, `type`, `url_source`, `description`, `actor`)" +
                " VALUES(?,?,?,?,?,?,?,? )";
        List<Object> params = new ArrayList<>();

        params.add(film.getImgUrl());
        params.add(film.getScore());
        params.add(film.getTitle());
        params.add(film.getUrl());
        params.add(film.getType());
        params.add(film.getUrlSource());
        params.add(film.getDescription());
        params.add(film.getActor());
        jdbcTemplate.update(sql, params.toArray());
//        filmDao.saveAndFlush(film);
        return film;
    }

    @Override
    public Page ListPage(Film search, int num, int size) {
        Pageable pageable = PageRequest.of(num - 1, size);
        Specification specification = (Specification) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (search != null) {
                String title = search.getTitle();
                if (StringUtils.isNotBlank(title)) {
                    predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
                }
                String urlSource = search.getUrlSource();
                if (StringUtils.isNotBlank(urlSource)) {
                    predicates.add(criteriaBuilder.equal(root.get("urlSource"), urlSource));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return filmDao.findAll(specification, pageable);
    }

    @Override
    public Page<Film> ListByConditionAndPage(Film search, int num, int size) {
        Pageable pageable = PageRequest.of(num - 1, size);
        Example<Film> searchExample = Example.of(search);
        return filmDao.findAll(searchExample, pageable);
    }
}
