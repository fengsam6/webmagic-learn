package com.feng.webmagic.spiderStart;

import com.feng.servcie.TableOptService;
import com.feng.webmagic.PageProcess.Film360PageProcessor;
import com.feng.webmagic.pipeline.FilmDBPipeline;
import com.feng.webmagic.urlDataConfig.Film360UrlUtil;
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
    @Autowired
    private TableOptService tableOptService;

    /**
     * 每隔2天，清空电影数据重新爬虫
     * todo 做个web页面定时调度
     */
    @Scheduled(cron = "* * 4 0/2 * ?")
    public void startScheduled() {
        //清空表tb_film数据，再重新爬取
//        tableOptService.cleanTableData("tb_film");
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
