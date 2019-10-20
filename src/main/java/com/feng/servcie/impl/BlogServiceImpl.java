package com.feng.servcie.impl;

import com.feng.dao.BlogDao;
import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rf on 2019/4/3.
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Page<Blog> ListPage(Blog search, int num, int size) {
        Pageable pageable = PageRequest.of(num - 1, size);
        Specification specification = (Specification) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (search != null && StringUtils.isNotBlank(search.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + search.getTitle() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return blogDao.findAll(specification, pageable);
    }

    @Override
    public Blog add(Blog blog) {

        blogDao.save(blog);
        return blog;
    }
}
