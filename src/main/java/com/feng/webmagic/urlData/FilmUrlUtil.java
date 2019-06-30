package com.feng.webmagic.urlData;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rf on 2019/6/30.
 */
@Slf4j
public class FilmUrlUtil {
    /**
     * 获取爱奇艺好评榜电影url
     *
     * @return
     */
    public static String[] getHighScoreUrls() {
        List<String> urls = getHighScoreUrlList();
        return urls.toArray(new String[urls.size()]);
    }
    /**
     * 获取爱奇艺好评榜电影url
     *
     * @return
     */
    private static  List<String> getHighScoreUrlList() {
        List<String> urls = new ArrayList<>();
        String detailUrl = "https://list.iqiyi.com/www/1/-------------8-10-1-iqiyi--.html";
        for (int i = 11; i <= 20; i++) {
            String newUrl = detailUrl.replaceAll("10", i + "");
            log.debug("{}", newUrl);
            urls.add(newUrl);
        }
        return urls;
    }

    /**
     * 获取爱奇艺热播榜电影url
     *
     * @return
     */
    public static String[] getHotFilmUrls() {
        List<String> urls = getHotFilmUrlList();
        return urls.toArray(new String[urls.size()]);
    }
    /**
     * 获取爱奇艺热播榜电影url
     *
     * @return
     */
    private static List<String> getHotFilmUrlList() {
        List<String> urls = new ArrayList<>();
        String detailUrl = "https://list.iqiyi.com/www/1/-------------11-10-1-iqiyi--.html";
        for (int i = 1; i <= 19; i++) {
            String newUrl = detailUrl.replaceAll("10", i + "");
            log.debug("{}", newUrl);
            urls.add(newUrl);
        }
        return urls;
    }

    /**
     * 获取爱奇艺新上线电影url
     *
     * @return
     */
    public static String[] getRecentPublishUrls() {
        List<String> urls = getRecentPublishUrlList();
        return urls.toArray(new String[urls.size()]);
    }
    /**
     * 获取爱奇艺新上线电影url
     *
     * @return
     */
    private static List<String> getRecentPublishUrlList() {
        List<String> urls = new ArrayList<>();
        String detailUrl = "https://list.iqiyi.com/www/1/-------------4-10-1-iqiyi--.html";
        for (int i = 1; i <= 19; i++) {
            String newUrl = detailUrl.replaceAll("10", i + "");
            log.debug("{}", newUrl);
            urls.add(newUrl);
        }
        return urls;
    }
    /**
     * 获取爱奇艺综合排序线电影url
     *
     * @return
     */
    public static String[] getHighValueUrls() {
        List<String> urls = getHighValueUrlList();
        return urls.toArray(new String[urls.size()]);
    }
    /**
     * 获取爱奇艺综合排序线电影url
     *
     * @return
     */
    private static List<String> getHighValueUrlList() {
        List<String> urls = new ArrayList<>();
        String detailUrl = "https://list.iqiyi.com/www/1/-------------24-10-1-iqiyi--.html";
        for (int i = 1; i <= 19; i++) {
            String newUrl = detailUrl.replaceAll("10", i + "");
            log.debug("{}", newUrl);
            urls.add(newUrl);
        }
        return urls;
    }

    public static String[] getAllUrl() {
        List<String> urls = new ArrayList<>();
        urls.addAll(getHotFilmUrlList());
        urls.addAll(getHighScoreUrlList());
        urls.addAll(getRecentPublishUrlList());
        return urls.toArray(new String[urls.size()]);
    }
}
