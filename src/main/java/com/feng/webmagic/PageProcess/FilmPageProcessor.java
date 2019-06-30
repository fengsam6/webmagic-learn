package com.feng.webmagic.PageProcess;

import com.feng.entity.Film;
import javafx.collections.transformation.FilteredList;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用webmagic框架爬爱奇艺影视
 */
@Slf4j
public class FilmPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    private static final String detailUrl = "https://list.iqiyi.com/www/1/-------------8-1-1-iqiyi--.html";
    private static final String helpUrl = "https://list\\.iqiyi\\.com/www/\\d+/\\d*-------------\\d+-\\d+-\\d+-iqiyi--.html";

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex(helpUrl).all());
        log.debug("{}", page.getUrl());
        if (page.getUrl().regex(helpUrl).match()) {
            Html html = page.getHtml();

            List<Selectable> liSelectables = html.xpath("//ul[@class='qy-mod-ul']/li").nodes();
            List<Film> filmList = new ArrayList<>();
            for (Selectable liSelectable : liSelectables) {
                Film film = resolve(liSelectable);
                filmList.add(film);
            }

            page.putField("filmList", filmList);

            page.addTargetRequest(page.getUrl().toString());
        }
    }



    private Film resolve(Selectable li) {
        System.out.println(li.toString());
        String url = "https:" + li.xpath("//a[@class='qy-mod-link']/@href").toString();
        String title = li.xpath("//a[@class='qy-mod-link']/@title").toString();
        String imgUrl = li.xpath("//div[@class='icon-tr']/img/@src").toString();
        String score = li.xpath("//span[@class='text-score']/text()").toString();
        Film film = new Film(title, url, imgUrl, score);
        log.info("film:{}****************", film.toString());
        return film;
    }

    //www.iqiyi.com/v_19rqxb34a0.html
    @Override
    public Site getSite() {
        return site;
    }
}
