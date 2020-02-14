package com.feng.init;

import com.feng.util.ApplicationContextInfoUtils;
import com.feng.util.CpuNumUtils;
import com.feng.util.IPUtils;
import com.feng.util.ThreadSleepUtils;
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

    @Override
    public void run(String... args) {
        ApplicationContextInfoUtils.printSystemInfo(environment);
        ThreadSleepUtils.sleepNs(6);
        log.info(">>>>>>>>>>>开始启动爬虫>>>>>>>>>>>>>>>>>>>>>>");
        //如果isSpiderNow为false，不启动爬虫
        if (!isSpiderNow) {
            return;
        }

        int n = CpuNumUtils.getCpuNum();
        ExecutorService executorService = Executors.newFixedThreadPool(n );
        executorService.execute(() -> {
            //将电影数据插入数据库中
            filmSpiderStart.IQIYIStart();
            ThreadSleepUtils.sleepNsAndYield(15);
            filmSpiderStart.start();
            ThreadSleepUtils.sleepNsAndYield(25);
            film360SpiderStart.film360Start();
            //爬取csdn博客数据
            ThreadSleepUtils.sleepNsAndYield(35);
            blogSpiderStart.start();
        });

    }


}
