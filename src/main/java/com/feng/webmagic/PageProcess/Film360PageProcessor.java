package com.feng.webmagic.PageProcess;

import com.feng.entity.Film;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Film360PageProcessor implements PageProcessor {
    private final String baseUrl = "https://www.360kan.com/";
    private static final String spiderUrl = "https://www.360kan.com/dianying/list*";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex(spiderUrl).all());
        if (page.getUrl().regex(spiderUrl).match()) {
            Html html = page.getHtml();
            List<Selectable> liSelectables = html.$(".s-tab-main .list.g-clear").xpath("//li[@class='item']").nodes();
            List<Film> filmList = new ArrayList<>();
            for (Selectable liSelectable : liSelectables) {
                Film film = resolve(liSelectable);
                filmList.add(film);
            }
            page.putField("filmList", filmList);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    private Film resolve(Selectable li) {
        System.out.println(li.toString());
        String url = baseUrl + li.xpath("//a[@class='js-tongjic']/@href").toString();
        url = checkHttpsPrefixAndAdd(url);
        String title = li.xpath("//div[@class='detail']//span/text()").toString();
        String imgUrl = li.xpath("//div[@class='cover']//img/@src").toString();
        String score = li.xpath("//span[@class='point']/text()").toString();
        String actor = li.xpath("//p[@class='star']/text()").toString();
        if (StringUtils.isEmpty(score)) {
            score = " ";
        }
        Film film = new Film(title, url, imgUrl, score);
        film.setActor(actor);
        film.setUrlSource("360影视");
        film.setType("电影");
        log.info("film:{}****************", film.toString());
        return film;
    }

    private String checkHttpsPrefixAndAdd(String url) {
        if (url != null && !url.startsWith("https:")) {
            url = "https:" + url;
        }
        return url;
    }
}
