package com.feng.webmagic.spiderStart;

import com.feng.webmagic.PageProcess.FilmPageProcessor;
import com.feng.webmagic.PageProcess.IQIYIFilmPageProcessor;
import com.feng.webmagic.pipeline.FilmDBPipeline;
import com.feng.webmagic.urlDataConfig.FilmUrlUtil;
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
public class FilmSpiderStart {
    @Autowired
    private FilmDBPipeline filmPipeline;
    @Autowired
    private RedisScheduler redisScheduler;
    @Autowired
    private FilmPageProcessor filmPageProcessor;
    @Autowired
    private IQIYIFilmPageProcessor iqiyiFilmPageProcessor;

    /**
     * 可以开启定时爬虫
     * todo 做个web页面定时调度
     */
        @Scheduled(cron = "* 0-30 9 * * ?")
    public void startScheduled() {
        start();
    }

    /**
     *爬虫开始url https://list.iqiyi.com/www/1/-------------8-10-1-iqiyi--.html
     */
    public void start() {
        log.info("启动爬虫。。。。。");
        String[] hotUrl = FilmUrlUtil.getHighScoreUrls();
        String[] highScoreUrls = FilmUrlUtil.getHighScoreUrls();
        String[] recentUrl = FilmUrlUtil.getRecentPublishUrls();
        //获取综合排序url
        String[] highValueUrl = FilmUrlUtil.getHighValueUrls();
        //爬虫开始url
        String[] allUrl = FilmUrlUtil.getAllUrl();
        Spider.create(filmPageProcessor)
                .addUrl(allUrl) //设置爬虫url
                .addPipeline(filmPipeline)
//                .setScheduler(redisScheduler)
                .setDownloader(new HttpClientDownloader()).thread(5).run();
    }

    /**
     * 爬虫开始url http://vip.iqiyi.com/hot.html?cid=1
     */
    public void IQIYIStart() {
        String startUrl[] = {"http://vip.iqiyi.com/hot.html?cid=1"};
        log.info("启动爬虫。。。。。");
        Spider.create(iqiyiFilmPageProcessor)
                .addUrl(startUrl) //设置爬虫url
                .addPipeline(filmPipeline)
//                .setScheduler(redisScheduler)
                .setDownloader(new HttpClientDownloader()).thread(5).run();
    }

}
