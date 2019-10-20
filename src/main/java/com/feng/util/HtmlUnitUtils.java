//package com.feng.util;
//
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//
//import java.io.IOException;
///**
// *webclient 模拟浏览器进行解析页面
// */
//public class HtmlUnitUtils {
//    /**
//     * 获取页面文档字串(等待异步JS执行)
//     *
//     * @param url 页面URL
//     * @return
//     * @throws Exception
//     */
//    public static HtmlPage getHtmlPage(String url) {
//        final WebClient webClient = new WebClient();
//        HtmlPage page = null;
//        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
//        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常
//        webClient.getOptions().setActiveXNative(false);
//        webClient.getOptions().setCssEnabled(false);//是否启用CSS
//        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
////        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，本页面不需要AJAX
//
//        webClient.getOptions().setTimeout(30000);//设置“浏览器”的请求超时时间
//
//        try {
//            page = webClient.getPage(url);
//            webClient.setJavaScriptTimeout(30000);//设置JS执行的超时时间
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            webClient.close();
//        }
//        return page;
//    }
//
//    public static String getHtmlPageResponse(String url){
//        HtmlPage page = getHtmlPage(url);
//        return page.asXml();
//    }
//}
