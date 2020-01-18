package com.feng.webmagic.urlData;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BlogUrlData {
    public static final String baseUrl = "https://blog.csdn.net/";
    public static final String accountName = "qq_27520051";
    public static final String detailUrl = baseUrl + accountName + "/article/details/\\d+";
    public static final String listUrl = baseUrl + accountName + "/article/list/\\d+?";

    public static String[] getSpiderUrl() {
        int pageNum = 2;
        String spiderUrls[] = new String[pageNum];
        String startUrl = baseUrl + accountName + "/article/list/1?";
        for (int i = 0; i < pageNum; i++) {
            String url = startUrl.replaceAll("1\\?", i + 1 + "?");
            log.info(url);
            spiderUrls[i] = url;
        }
        return spiderUrls;
    }
}
