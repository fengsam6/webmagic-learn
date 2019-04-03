package com.feng.webmagic.pipeline;

import com.feng.entity.Film;
import com.feng.servcie.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by rf on 2019/4/3.
 */
@Slf4j
@Component
public class FilmPipeline implements Pipeline {
    @Autowired
    private FilmService filmService;
    @Override
    public void process(ResultItems resultItems, Task task) {
      Film film = resultItems.get("film");
        log.info("**********************film:{}****************",film.toString());
        filmService.add(film);
    }
}
