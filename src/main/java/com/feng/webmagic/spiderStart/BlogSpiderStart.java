package com.feng.webmagic.spiderStart;

import com.feng.servcie.TableOptService;
import com.feng.util.CpuNumUtils;
import com.feng.webmagic.PageProcess.BlogPageProcessor;
import com.feng.webmagic.pipeline.BlogDBPipeline;
import com.feng.webmagic.urlDataConfig.BlogUrlData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 使用webmagic框架，爬虫指定用户博客
 */
@Component
@Slf4j
public class BlogSpiderStart {
    @Autowired
    private BlogDBPipeline blogPipeline;
//    @Autowired
    private RedisScheduler redisScheduler;
    @Autowired
    private BlogPageProcessor blogPageProcessor;
    @Autowired
    private TableOptService tableOptService;

    /**
     * 每隔1天，清空博客数据重新爬虫
     */
    @Scheduled(cron = "* * 9 0/1 * ?")
    public void startScheduled() {
        tableOptService.cleanTableData("tb_blog");
        start();
    }

    public void start() {
        int cpuN = CpuNumUtils.getCpuNum();
        log.info("启动爬虫。。。。。");
        Spider.create(blogPageProcessor).addUrl(BlogUrlData.getSpiderUrls())
                .addPipeline(blogPipeline).thread(cpuN+1)
//                .setScheduler(redisScheduler)
                .setDownloader(new HttpClientDownloader())
                .run();
    }
}
