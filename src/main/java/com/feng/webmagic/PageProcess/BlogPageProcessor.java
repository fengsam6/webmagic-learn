package com.feng.webmagic.PageProcess;

import com.feng.entity.Blog;
import com.feng.webmagic.urlDataConfig.BlogUrlData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 解析博客页面
 */
@Slf4j
@Component
public class BlogPageProcessor implements PageProcessor {
    //    设置爬虫间隔时间
    @Value("${system.spiderIntervalSecond}")
    private int spiderIntervalSecond = 2;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(spiderIntervalSecond)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
            .setCharset("UTF-8");
    private static final String detailUrl = "https://blog\\.csdn\\.net/\\S+/article/details/\\d+";

    @Override
    public void process(Page page) {
        String pageUrl = page.getUrl().toString();
        //从blog列表页面，提取具体文字url
        if (page.getUrl().regex(BlogUrlData.listUrlReg).match()) {
            Html html = page.getHtml();
            List<Selectable> divListSelectable = html.xpath("div[@class='article-list']/div[@class='article-item-box']").nodes();
            for (Selectable selectable : divListSelectable) {
                String url = selectable.xpath("//h4/a/@href").toString();
                page.addTargetRequest(url);
            }
        }

        if (page.getUrl().regex(BlogUrlData.detailUrlReg).match()) {
            String url = page.getUrl().toString();
//        HtmlPage htmlPage = HtmlUnitUtils.getHtmlPage(url);
//         String content =   HtmlUnitUtils.getHtmlPageResponse(url);
            page.setCharset("utf-8");
            Html html = page.getHtml();
            String title = html.xpath("//h1[@class='title-article']/text()").toString();
            String publishTime = html.xpath("//div[@class='article-bar-top']/span[@class='time']/text()").get();
            String author = html.xpath("//a[@class='follow-nickName']/text()").toString();
            String sourceUrl = page.getUrl().toString();
            String readNum = html.xpath("//span[@class='read-count']/text()").toString();
            Blog blog = new Blog(title, author, publishTime, sourceUrl, readNum);
                page.putField("blog", blog);
        }
        System.out.print(pageUrl);
    }

    @Override
    public Site getSite() {
        return site;
    }


}
