package com.feng.servcie;

import com.feng.entity.Film;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Service
public interface FilmService {
    List<Film> list(int size, int num);

    Film add(Film film);
}
