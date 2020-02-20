package com.feng.webmagic.pipeline;

import com.feng.entity.Film;
import com.feng.servcie.FilmService;
import com.feng.util.CpuNumUtils;
import com.feng.util.ThreadPoolUtils;
import com.feng.util.ThreadSleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 将网页爬虫数据保存数据库中
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
        int count =films.size();
        AtomicInteger inc= new AtomicInteger();
        films = Collections.synchronizedList(films);
        int n = CpuNumUtils.getCpuNum();
        ExecutorService executorService = ThreadPoolUtils.creatIOsThreadPool();
        for (Film film : films) {
            log.info("**********************film:{}****************", film.toString());
            executorService.execute(()->{
                try {
                    filmService.add(film);
                    log.info("插入一条数据成功");
                    inc.getAndIncrement();

                } catch (Exception e) {
                    if(e instanceof DataIntegrityViolationException || e instanceof ConstraintViolationException){
                        log.error("这条数据已经存在，插入忽略");
                        return;
                    }
                    log.error("插入数据失败。。。。");
                    e.printStackTrace();
                }
            });

        }

        log.info("成功解析页面{}条数据，向数据库成功插入{}条数据",count,inc.get());
        ThreadSleepUtils.sleepNsAndYield(4);
    }
}
