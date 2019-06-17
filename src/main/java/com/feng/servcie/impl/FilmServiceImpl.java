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

    @Override
    public Film add(Film film) {
        try {
            filmDao.save(film);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            e.printStackTrace();
        }

        return film;
    }

    @Override
    public Page<Film> ListPage(Film search, int num, int size) {
        Pageable pageable = PageRequest.of(num, size);
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
