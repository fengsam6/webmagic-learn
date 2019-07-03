package com.feng.webmagic.spiderStart;

import com.feng.webmagic.PageProcess.FilmPageProcessor;
import com.feng.webmagic.pipeline.FilmDBPipeline;
import com.feng.webmagic.urlData.FilmUrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用webmagic框架爬爱奇艺影视
 */
@Component
@Slf4j
public class FilmSpiderStart {
    @Autowired
    private FilmDBPipeline filmPipeline;
    @Autowired
    private RedisScheduler redisScheduler;

    /**
     * 可以开启定时爬虫
     */
        @Scheduled(cron = "* 0-30 9 * * ?")
    public void startScheduled() {
        start();
    }

    public void start() {
        log.info("启动爬虫。。。。。");
        String[] hotUrl = FilmUrlUtil.getHighScoreUrls();
        String[] highScoreUrls = FilmUrlUtil.getHighScoreUrls();
        String[] recentUrl = FilmUrlUtil.getRecentPublishUrls();
        String[] highValueUrl = FilmUrlUtil.getHighValueUrls();//获取综合排序url
        String[] allUrl = FilmUrlUtil.getAllUrl();
        Spider.create(new FilmPageProcessor())
                .addUrl(allUrl) //设置爬虫url
                .addPipeline(filmPipeline)
                .setScheduler(redisScheduler)
                .setDownloader(new HttpClientDownloader()).thread(5).run();
    }


}
