package com.feng.servcie.impl;

import com.feng.dao.FilmDao;
import com.feng.entity.Film;
import com.feng.servcie.FilmService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmDao filmDao;

    public List<Film> list(int size, int num) {
        PageHelper.startPage(num,size);
        return filmDao.findAll();
    }

    @Override
    public Film add(Film film) {
        filmDao.save(film);
        return film;
    }
}
