package com.feng.init;

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
    private boolean isSpiderNow=false;
    @Override
    public void run(String... args)  {
        //如果isSpiderNow为false，不启动爬虫
        if(!isSpiderNow){
            return;
        }
        try {
            //休息3s，等待Tomcat容器启动完成
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(()->{
            //将电影数据插入数据库中
            filmSpiderStart.IQIYIStart();
            film360SpiderStart.film360Start();
            //图片是js渲染，需要动态解析
            filmSpiderStart.start();
            blogSpiderStart.start();
        });

    }
}
