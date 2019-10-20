package com.feng.webmagic.spiderStart;

import com.feng.webmagic.PageProcess.Film360PageProcessor;
import com.feng.webmagic.PageProcess.FilmPageProcessor;
import com.feng.webmagic.PageProcess.IQIYIFilmPageProcessor;
import com.feng.webmagic.pipeline.FilmDBPipeline;
import com.feng.webmagic.urlData.Film360UrlUtil;
import com.feng.webmagic.urlData.FilmUrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 使用webmagic框架爬爱奇艺影视
 */
@Component
@Slf4j
public class Film360SpiderStart {
    @Autowired
    private FilmDBPipeline filmPipeline;
    @Autowired
    private RedisScheduler redisScheduler;
    @Autowired
    private Film360PageProcessor film360PageProcessor;

    /**
     * 可以开启定时爬虫
     * todo 做个web页面定时调度
     */
        @Scheduled(cron = "* 0-30 9 * * ?")
    public void startScheduled() {
            film360Start();
    }


    /**
     * 爬虫360影视
     */
    public void film360Start() {
        String startUrl[] = Film360UrlUtil.get360DianyingUrls();
        log.info("启动爬虫。。。。。");
        Spider.create(film360PageProcessor)
                .addUrl(startUrl) //设置爬虫url
                .addPipeline(filmPipeline)
                .setDownloader(new HttpClientDownloader()).thread(5).run();
    }

}
