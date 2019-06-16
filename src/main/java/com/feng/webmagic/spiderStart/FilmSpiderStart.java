package com.feng.webmagic.spiderStart;

import com.feng.webmagic.PageProcess.FilmPageProcessor;
import com.feng.webmagic.pipeline.FilmDBPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import javax.annotation.Resource;

/**
 * 使用webmagic框架爬爱奇艺影视
 */
@Component
@Slf4j
public class FilmSpiderStart {
    @Autowired
    private FilmDBPipeline filmPipeline;
    @Scheduled(cron = "0/40 * * * * ?")
    public void startScheduled(){
        start();
    }
    public void start(){
        log.info("启动爬虫。。。。。");
        String reqUrl = "https://list.iqiyi.com/www/1/27401----------2---11-1-1-iqiyi--.html";
        Spider.create(new FilmPageProcessor()).addUrl(reqUrl)
                .addPipeline(filmPipeline)
                .setDownloader(new HttpClientDownloader()).thread(5).run();
    }
}
