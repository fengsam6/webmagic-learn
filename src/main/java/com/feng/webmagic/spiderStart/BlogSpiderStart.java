package com.feng.webmagic.spiderStart;

import com.feng.webmagic.PageProcess.BlogPageProcessor;
import com.feng.webmagic.pipeline.BlogDBPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.annotation.Resource;

/**
 * 使用webmagic框架爬CSDN博客
 */
@Component
@Slf4j
public class BlogSpiderStart {
    @Autowired
    private BlogDBPipeline blogPipeline;
    @Autowired
    private RedisScheduler redisScheduler;

    @Scheduled(cron = "* 0-30 *  * * ?")
    @Async
    public void startScheduled() {
        start();
    }

    public void start() {
        log.info("启动爬虫。。。。。");
        Spider.create(new BlogPageProcessor()).addUrl("https://blog.csdn.net/wireless_com/article/details/89008061")
                .addPipeline(blogPipeline)
//                .setScheduler(redisScheduler)
                .setDownloader(new HttpClientDownloader())
                .thread(5).run();
        try {
//            Thread.yield();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
