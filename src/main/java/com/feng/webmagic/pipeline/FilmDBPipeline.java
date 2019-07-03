package com.feng.webmagic.pipeline;

import com.feng.entity.Film;
import com.feng.servcie.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by rf on 2019/4/3.
 */
@Slf4j
@Component
public class FilmDBPipeline implements Pipeline {
    @Autowired
    private FilmService filmService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Film> films = resultItems.get("filmList");
        if (films == null) {
            log.error("爬虫失败，爬虫url或者页面解析有问题或者该URL已经爬取过（检查redis set中是否有该url）");
            return;
        }
        for (Film film : films) {
            log.info("**********************film:{}****************", film.toString());
            filmService.add(film);
        }

    }
}
