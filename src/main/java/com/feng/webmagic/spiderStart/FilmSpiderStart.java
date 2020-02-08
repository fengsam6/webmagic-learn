package com.feng.webmagic.spiderStart;

import com.feng.servcie.TableOptService;
import com.feng.util.CpuNumUtils;
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
//    @Autowired
    private RedisScheduler redisScheduler;
    @Autowired
    private FilmPageProcessor filmPageProcessor;
    @Autowired
    private IQIYIFilmPageProcessor iqiyiFilmPageProcessor;
    @Autowired
    private TableOptService tableOptService;
    private int cpuN = CpuNumUtils.getCpuNum();

    /**
     * 每隔2天，清空电影数据重新爬虫
     * 这个爬虫获取数据为主
     */
    @Scheduled(cron = "* * 1 0/2 * ?")
    public void IQIYIStartScheduled() {
        //清空表tb_film数据，再重新爬取
        tableOptService.cleanTableData("tb_film");
        IQIYIStart();
    }

    @Scheduled(cron = "* * 5 0/2 * ?")
    public void startScheduled2() {
        start();
    }

    /**
     * 爬虫开始url https://list.iqiyi.com/www/1/-------------8-10-1-iqiyi--.html
     *
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
                .setDownloader(new HttpClientDownloader()).thread(cpuN+1).run();
    }

    /**
     * 爬虫开始url http://vip.iqiyi.com/hot.html?cid=1
     */
    public void IQIYIStart() {
        String[] startUrl = {"http://vip.iqiyi.com/hot.html?cid=1"};
        log.info("启动爬虫。。。。。");
        Spider.create(iqiyiFilmPageProcessor)
                .addUrl(startUrl) //设置爬虫url
                .addPipeline(filmPipeline)
//                .setScheduler(redisScheduler)
                .setDownloader(new HttpClientDownloader()).thread(cpuN+1).run();
    }

}
