package com.feng.webmagic.spiderStart;

import com.feng.webmagic.PageProcess.BlogPageProcessor;
import com.feng.webmagic.pipeline.BlogPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

/**
 * 使用webmagic框架爬CSDN博客
 */
@Component
@Slf4j
public class BlogSpiderStart {
@Scheduled(fixedRate = 1000)
    public void start(){
    log.info("启动爬虫。。。。。");
        Spider.create(new BlogPageProcessor()).addUrl("https://blog.csdn.net/zhaipengfei1231/article/details/79984047")
                .addPipeline(new BlogPipeline())
                .addPipeline(new JsonFilePipeline("blog"))
                .addPipeline(new ConsolePipeline())
//                .setDownloader(new HttpClientDownloader())
                .thread(5).run();
    }
    public static void main(String[] args) {
        Spider.create(new BlogPageProcessor()).addUrl("https://blog.csdn.net/zhaipengfei1231/article/details/79984047")
//                .addPipeline(new ConsolePipeline())
                .addPipeline(new BlogPipeline())
                .addPipeline(new JsonFilePipeline("blog"))
                .setDownloader(new HttpClientDownloader()).thread(5).run();
    }
}
