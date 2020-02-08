package com.feng.init;

import com.feng.util.CpuNumUtils;
import com.feng.util.IPUtils;
import com.feng.webmagic.spiderStart.BlogSpiderStart;
import com.feng.webmagic.spiderStart.Film360SpiderStart;
import com.feng.webmagic.spiderStart.FilmSpiderStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * spring容器加载完，tomcat启动后,执行run方法
 */
@Component
public class ApplicationStartInit implements CommandLineRunner {
    @Autowired
    private FilmSpiderStart filmSpiderStart;
    @Autowired
    private Film360SpiderStart film360SpiderStart;
    @Autowired
    private BlogSpiderStart blogSpiderStart;
    @Value("${system.isSpiderNow}")
    private boolean isSpiderNow = false;

    @Override
    public void run(String... args) {
        //如果isSpiderNow为false，不启动爬虫
        if (!isSpiderNow) {
            return;
        }
        sleepNs(3);

        int n = CpuNumUtils.getCpuNum();
        ExecutorService executorService = Executors.newFixedThreadPool(n + 1);
        executorService.execute(() -> {
            //将电影数据插入数据库中
            filmSpiderStart.IQIYIStart();
            sleepNsAndYield(5);
            film360SpiderStart.film360Start();
            sleepNsAndYield(15);
            filmSpiderStart.start();
            //爬取csdn博客数据
            sleepNsAndYield(5);
            blogSpiderStart.start();
        });

    }


    private void sleepNs(int n) {
        try {
            //休息3s，等待Tomcat容器启动完成
            Thread.sleep(n * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void sleepNsAndYield(int n) {
        Thread.yield();
        sleepNs(n);
    }
}
