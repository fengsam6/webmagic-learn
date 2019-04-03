package com.feng.webmagic.PageProcess;

import com.feng.entity.Blog;
import com.feng.entity.Film;
import com.feng.webmagic.pipeline.BlogPipeline;
import com.feng.webmagic.pipeline.FilmPipeline;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 使用webmagic框架爬爱奇艺影视
 */
@Slf4j
public class FilmPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    private static final String detailUrl = "https://www\\.iqiyi\\.com/v_\\w+\\.html";
    private static final String helpUrl = "https://list\\.iqiyi\\.com/www/\\d+/----------\\d+---\\d+-\\d+-\\d+-iqiyi--.html";
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex(helpUrl).all());
        log.debug("{}", page.getUrl());
        if (page.getUrl().regex(helpUrl).match()) {
            Html html = page.getHtml();
            String url = html.xpath("//a[@class='site-piclist_pic_link']/@href").toString();
            String title =  html.xpath("//a[@class='site-piclist_pic_link']/@title").toString();
              String imgUrl =  html.xpath("//a[@class='site-piclist_pic_link']/img/@src").toString();
            String RScore = html.xpath("//span[@class='score']/strong/text()").toString();
              String score = html.xpath("//span[@class='score']/text()").toString();
              Film film = new Film(title,url,imgUrl,RScore+score);
            film.setImgUrl(imgUrl);
            film.setTitle(title);
            film.setUrl(url);
            page.putField("film", film);
//            log.info("film:{}****************",film.toString());
        }

    }

    @Override
    public Site getSite() {
        return site;
    }
}
