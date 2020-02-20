package com.feng.init;

import com.feng.servcie.TableOptService;
import com.feng.util.*;
import com.feng.webmagic.spiderStart.BlogSpiderStart;
import com.feng.webmagic.spiderStart.Film360SpiderStart;
import com.feng.webmagic.spiderStart.FilmSpiderStart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * spring容器加载完，tomcat启动后,执行run方法
 */
@Component
@Slf4j
public class ApplicationStartInit implements CommandLineRunner {
    @Autowired
    private FilmSpiderStart filmSpiderStart;
    @Autowired
    private Film360SpiderStart film360SpiderStart;
    @Autowired
    private BlogSpiderStart blogSpiderStart;
    @Autowired
    private Environment environment;
    @Value("${system.isSpiderNow}")
    private boolean isSpiderNow = false;
    @Value("${system.spider.cleanTableOldData}")
    private boolean cleanTableOldData = false;
    @Autowired
private TableOptService tableOptService;
    @Override
    public void run(String... args) {
        ApplicationContextInfoUtils.printSystemInfo(environment);

        //如果isSpiderNow为false，不启动爬虫
        if (!isSpiderNow) {
            return;
        }

        //清空表中数据
        if(cleanTableOldData){
            log.info(">>>>>>>>>>>cleanTableOldData=true,清空爬虫旧数据>>>>>>>>>>>>>>>>>>>>>>");
            tableOptService.cleanTableData("tb_film");
            tableOptService.cleanTableData("tb_blog");
        }else {
            log.info(">>>>>>>>>>>cleanTableOldData=true,不清空爬虫旧数据>>>>>>>>>>>>>>>>>>>>>>");
        }

        log.info(">>>>>>>>>>>准备启动爬虫>>>>>>>>>>>>>>>>>>>>>>");
        ThreadSleepUtils.sleepNs(6);

        ExecutorService executorService = ThreadPoolUtils.creatCpusThreadPool(4);
        executorService.execute(() -> {
            //将电影数据插入数据库中
            filmSpiderStart.IQIYIStart();
        });
        executorService.execute(() -> {
            //将电影数据插入数据库中
            ThreadSleepUtils.sleepNs(100);
            filmSpiderStart.start();
        });
        executorService.execute(() -> {
            //将电影数据插入数据库中
            ThreadSleepUtils.sleepNsAndYield(250);
            film360SpiderStart.film360Start();
        });
        executorService.execute(() -> {
            ThreadSleepUtils.sleepNsAndYield(350);
            blogSpiderStart.start();
        });
        executorService.shutdown();

    }


}
