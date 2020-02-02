package com.feng.webmagic.urlDataConfig;

import com.feng.conf.SpiderBlogUserConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class BlogUrlData {
    public static final String baseUrl = "https://blog.csdn.net/";
    public static final String accountName = "csdnnews";
    public static final String detailUrl = baseUrl + accountName + "/article/details/\\d+";
    public static final String listUrl = baseUrl + accountName + "/article/list/\\d+?";

    private static String[] accountNames = SpiderBlogUserConfig.SpiderBlogUser;

    public static String[] getSpiderUrl(String accountName) {
        int pageNum = getPageNum(accountName);
        String spiderUrls[] = new String[pageNum];
        String startUrl = baseUrl + accountName + "/article/list/1?";
        for (int i = 0; i < pageNum; i++) {
            String url = startUrl.replaceAll("1\\?", i + 1 + "?");
            log.info(url);
            spiderUrls[i] = url;
        }
        return spiderUrls;
    }
    public static int getPageNum(String accountName) {
        return 10;
    }
    public static String[] getSpiderUrl(){
        return getSpiderUrl(accountName);
    }
///todo
    public static List<String> getSpiderUrls() {
        for (String account : accountNames) {
            String url[] = getSpiderUrl( accountName);
        }
        return null;
    }
}
