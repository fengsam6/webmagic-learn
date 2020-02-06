package com.feng.webmagic.PageProcess;

import com.feng.entity.Film;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用webmagic框架爬爱奇艺影视
 * todo 图片url需要动态解析
 */
@Slf4j
@Component
public class FilmPageProcessor implements PageProcessor {
    //    设置爬虫间隔时间
    @Value("${system.spiderIntervalSecond}")
    private int spiderIntervalSecond = 2;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(spiderIntervalSecond)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    private static final String detailUrl = "https://list.iqiyi.com/www/1/-------------8-1-1-iqiyi--.html";
    private static final String spiderUrl = "https://list\\.iqiyi\\.com/www/\\d+/\\d*-------------\\d+-\\d+-\\d+-iqiyi--.html";

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex(spiderUrl).all());
        log.debug("{}", page.getUrl());
        if (page.getUrl().regex(spiderUrl).match()) {
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
        String imgUrl = li.xpath("//img/@src").toString();
        String score = li.xpath("//span[@class='text-score']/text()").toString();
        if(StringUtils.isEmpty(score)){
            score=" ";
        }
        Film film = new Film(title, url, imgUrl, score);
        film.setUrlSource("爱奇艺影视");
        film.setType("电影");
        log.info("film:{}****************", film.toString());
        return film;
    }

    private String resolveImgUrl(Selectable li) {
        String qyModCoverStyle = li.xpath("//div[@class='qy-mod-cover']/@style").toString();
        return qyModCoverStyle;
    }

    //www.iqiyi.com/v_19rqxb34a0.html
    @Override
    public Site getSite() {
        return site;
    }
}
