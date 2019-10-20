package com.feng.webmagic.urlData;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rf on 2019/6/30.
 */
@Slf4j
public class Film360UrlUtil {
    /**
     * 获取爱奇艺好评榜电影url
     *
     * @return
     */
    public static String[] get360DianyingUrls() {
        List<String> urls = get360DianyingUrlList();
        return urls.toArray(new String[urls.size()]);
    }
    /**
     * 获取爱奇艺好评榜电影url
     *
     * @return
     */
    private static  List<String> get360DianyingUrlList() {
        List<String> urls = new ArrayList<>();
        String detailUrl = "https://www.360kan.com/dianying/list?rank=rankhot&cat=all&area=all&act=all&year=all&pageno=1";
        for (int i = 11; i <= 25; i++) {
            String newUrl = detailUrl.replaceAll("pageno=1", "pageno="+i );
            log.debug("{}", newUrl);
            urls.add(newUrl);
        }
        return urls;
    }


}
