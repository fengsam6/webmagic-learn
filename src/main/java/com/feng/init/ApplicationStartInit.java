package com.feng.init;

import com.feng.webmagic.spiderStart.BlogSpiderStart;
import com.feng.webmagic.spiderStart.Film360SpiderStart;
import com.feng.webmagic.spiderStart.FilmSpiderStart;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public void run(String... args)  {
        try {
            //休息1s，等待Tomcat容器启动完成
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(()->{
            //将电影数据插入数据库中
            filmSpiderStart.IQIYIStart();
            film360SpiderStart.film360Start();
            //图片是js渲染，需要动态解析
            //todo 可能用htmlUnit解析，这个好像不稳定
            filmSpiderStart.start();
//            blogSpiderStart.start();
        });

    }
}
