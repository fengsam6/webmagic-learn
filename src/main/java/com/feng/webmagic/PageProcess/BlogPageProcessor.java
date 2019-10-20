package com.feng.webmagic.PageProcess;

import com.feng.entity.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 解析博客页面
 */
//TODO 之前解析可以，现在不能解析，有时间解决
@Slf4j
@Component
public class BlogPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    ;
    private static final String detailUrl = "https://blog\\.csdn\\.net/\\S+/article/details/\\d+";

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex(detailUrl).all());

        if (page.getUrl().regex(detailUrl).match()) {
            String url = page.getUrl().toString();
//        HtmlPage htmlPage = HtmlUnitUtils.getHtmlPage(url);
//         String content =   HtmlUnitUtils.getHtmlPageResponse(url);
           Html html = page.getHtml();
            String title = html.xpath("//h1[@class='title-article']/text()").toString();
            String publishTime = html.xpath("//div[@class='article-bar-top']/span[@class='time']/text()").get();
            String author = html.xpath("//a[@class='follow-nickName']/text()").toString();
           String sourceUrl = page.getUrl().toString();
           String readNum = html.xpath("//span[@class='read-count']/text()").toString();
            Blog blog    = new Blog(title, author, publishTime,sourceUrl,readNum);
            page.putField("blog", blog);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


}
