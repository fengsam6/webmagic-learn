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
import java.util.stream.Collectors;

/**
 * 使用webmagic框架爬爱奇艺影视
 */
@Slf4j
@Component
public class IQIYIFilmPageProcessor implements PageProcessor {
    //    设置爬虫间隔时间
    @Value("${system.spiderIntervalSecond}")
    private int spiderIntervalSecond = 2;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(spiderIntervalSecond * 1000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    private static final String detailUrl = "http://www.iqiyi.com/dianying/vip.html";
    private static final String spiderUrl1 = "http://www\\.iqiyi\\.com/\\w*\\/vip.html";
    //    爱奇艺爬虫电影页面
    private static final String spiderUrl2 = "http://vip\\.iqiyi\\.com/hot\\.html\\?cid=1";

    @Override
    public void process(Page page) {

        if (page.getUrl().regex(spiderUrl1).match() || page.getUrl().regex(spiderUrl2).match()) {
            page.addTargetRequests(page.getHtml().links().regex(spiderUrl1).all());
            page.addTargetRequests(page.getHtml().links().regex(spiderUrl2).all());
            log.debug("{}", page.getUrl());
            Html html = page.getHtml();

            List<Selectable> liSelectables = html.xpath("//div[@class='wrapper-piclist']/ul/li").nodes();
//            List<Film> filmList = new ArrayList<>();
//            for (Selectable liSelectable : liSelectables) {
//                Film film = resolve(liSelectable);
//                filmList.add(film);
//            }
            List<Film> filmList =   liSelectables.stream()
                    .map(IQIYIFilmPageProcessor::resolve).collect(Collectors.toList());
            page.putField("filmList", filmList);

            page.addTargetRequest(page.getUrl().toString());
        }
    }


    private static Film resolve(Selectable li) {
        System.out.println(li.toString());
        String url = li.xpath("//a[@class='site-piclist_pic_link']/@href").toString();
        url = checkHttpPrefixAndAdd(url);
        String title = li.xpath("//a[@class='site-piclist_pic_link']/@title").toString();
        String imgUrl = resolveImg(li);
        Selectable scoreEle = li.xpath("//span[@class='score']");
        String scoreEleStr = scoreEle.toString();
        String scoreLeft = scoreEle.xpath("strong/text()").toString();
        String score="";
        if(!StringUtils.isEmpty(scoreEleStr)){
            score = scoreLeft;
            String scoreRight = null;
            try {
                scoreRight = scoreEleStr.substring(scoreEleStr.lastIndexOf("."),scoreEleStr.lastIndexOf("</span>")).trim();
            } catch (Exception e) {
                scoreRight="";
            }
            if (!StringUtils.isEmpty(scoreRight)) {
                score += scoreRight;
            }
        }


        String des = li.xpath("//p[@class='site-piclist_info_describe']/text()").toString();
        Film film = new Film(title, url, imgUrl, score);
        film.setUrlSource("爱奇艺影视");
        film.setType("电影");
        film.setDescription(des);
        log.info("film:{}****************", film.toString());
        return film;
    }

    private static String checkHttpPrefixAndAdd(String url) {
        if (url != null && !url.startsWith("http:")) {
            url = "http:" + url;
        }
        return url;
    }

    private static String resolveImg(Selectable li) {
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
