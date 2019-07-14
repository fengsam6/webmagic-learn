package com.feng.webmagic.PageProcess;

import com.feng.entity.Film;
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
public class IQIYIFilmPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    private static final String detailUrl = "http://www.iqiyi.com/dianying/vip.html";
    private static final String helpUrl = "http://www\\.iqiyi\\.com/\\w*\\/vip.html";
    private static final String helpUrl2 = "http://vip\\.iqiyi\\.com/hot\\.html\\?cid=1";

    @Override
    public void process(Page page) {

        if (page.getUrl().regex(helpUrl).match() || page.getUrl().regex(helpUrl2).match()) {
            page.addTargetRequests(page.getHtml().links().regex(helpUrl).all());
            page.addTargetRequests(page.getHtml().links().regex(helpUrl2).all());
            log.debug("{}", page.getUrl());
            Html html = page.getHtml();

            List<Selectable> liSelectables = html.xpath("//div[@class='wrapper-piclist']/ul/li").nodes();
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
        String url = li.xpath("//a[@class='site-piclist_pic_link']/@href").toString();
        url = checkHttpPrefixAndAdd(url);
        String title = li.xpath("//a[@class='site-piclist_pic_link']/@title").toString();
        String imgUrl = resolveImg(li);
        String score = li.xpath("//span[@class='text-score']/text()").toString();
        Film film = new Film(title, url, imgUrl, score);
        log.info("film:{}****************", film.toString());
        return film;
    }

    private String checkHttpPrefixAndAdd(String url) {
        if (url != null && !url.startsWith("http:")) {
            url = "http:" + url;
        }
        return url;
    }

    private String resolveImg(Selectable li) {
        //img url 有两种存在形式
        String imgUrl = li.xpath("//img/@src").toString();
        if (imgUrl == null || imgUrl.length() == 0) {
            imgUrl = li.xpath("//img/@data-src").toString();
        }
        imgUrl = checkHttpPrefixAndAdd(imgUrl);
        return imgUrl;
    }

    private String checkHttpsPrefixAndAdd(String url) {
        if (url != null && !url.startsWith("https:")) {
            url = "https:" + url;
        }
        return url;
    }

    //www.iqiyi.com/v_19rqxb34a0.html
    @Override
    public Site getSite() {
        return site;
    }
}
