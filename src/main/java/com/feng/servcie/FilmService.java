package com.feng.servcie;

import com.feng.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
public interface FilmService {
    Film add(Film film);

    Page<Film> ListPage(Film search, int num, int size);

     Page<Film> ListByConditionAndPage(Film search, int num, int size);
}
