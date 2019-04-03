package com.feng.webmagic.spiderStart;

import com.feng.webmagic.PageProcess.BlogPageProcessor;
import com.feng.webmagic.PageProcess.FilmPageProcessor;
import com.feng.webmagic.pipeline.BlogPipeline;
import com.feng.webmagic.pipeline.FilmPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

/**
 * 使用webmagic框架爬爱奇艺影视
 */
@Component
@Slf4j
public class FilmSpiderStart {
@Scheduled(fixedRate = 1000)
    public void start(){
    log.info("启动爬虫。。。。。");
    String reqUrl = "https://list.iqiyi.com/www/1/----------2---24-1-1-iqiyi--.html";
    Spider.create(new FilmPageProcessor()).addUrl(reqUrl)
            .addPipeline(new ConsolePipeline())
            .addPipeline(new FilmPipeline())
            .addPipeline(new JsonFilePipeline("film"))
            .setDownloader(new HttpClientDownloader()).thread(5).run();
    }
    public static void main(String[] args) {
        String reqUrl = "https://list.iqiyi.com/www/1/----------2---24-1-1-iqiyi--.html";
        Spider.create(new FilmPageProcessor()).addUrl(reqUrl)
//                .addPipeline(new ConsolePipeline())
                .addPipeline(new FilmPipeline())
                .addPipeline(new JsonFilePipeline("film"))
                .setDownloader(new HttpClientDownloader()).thread(5).run();
    }
}
