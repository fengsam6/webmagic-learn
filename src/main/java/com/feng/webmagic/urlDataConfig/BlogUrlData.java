package com.feng.webmagic.urlDataConfig;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class BlogUrlData {
    public static final String baseUrl = "https://blog.csdn.net/";
    public static final String accountName = "csdnnews";
    public static final String detailUrlReg = "https://blog\\.csdn\\.net/\\S+/article/details/\\d+";
    public static final String listUrlReg = "https://blog\\.csdn\\.net/\\S+/article/list/\\d+?";
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

//todo 根据账号获取爬虫页面
    public static int getPageNum(String accountName) {
        return 10;
    }

    public static String[] getSpiderUrl() {
        return getSpiderUrl(accountName);
    }

    public static List<String> getSpiderUrlList() {
        List<String> blogUrlList = new ArrayList<>();
        for (String account : accountNames) {
            String[] url = getSpiderUrl(account);
            List<String> urlList = Arrays.asList(url);
            blogUrlList.addAll(urlList);
        }
        return blogUrlList;
    }

    public static String[] getSpiderUrls() {
        List<String> blogUrlList = getSpiderUrlList();
        return blogUrlList.toArray(new String[blogUrlList.size()]);
    }
}
