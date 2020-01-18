package com.feng.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
/**
 *webclient 模拟浏览器进行解析页面
 */
public class HtmlUnitUtils {
    /**
     * 获取页面文档字串(等待异步JS执行)
     *
     * @param url 页面URL
     * @return
     * @throws Exception
     */
    public static HtmlPage getHtmlPage(String url) {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME );
        HtmlPage page = null;

        webClient.getOptions().setTimeout(15000);//设置网页响应时间
        webClient.getOptions().setUseInsecureSSL(true);//是否
        webClient.getOptions().setRedirectEnabled(true);//是否自动加载重定向
        webClient.getOptions().setThrowExceptionOnScriptError(false);//是否抛出页面javascript错误
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//是否抛出response的错误
        webClient.getOptions().setJavaScriptEnabled(false);// HtmlUnit对JavaScript的支持不好，关闭之
        webClient.getOptions().setCssEnabled(false);// HtmlUnit对CSS的支持不好，关闭之


        try {
            page = webClient.getPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webClient.closeAllWindows();
        }
        return page;
    }

    public static String getHtmlPageResponse(String url){
        HtmlPage page = getHtmlPage(url);
        return page.asXml();
    }
}
