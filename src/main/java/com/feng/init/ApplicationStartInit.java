package com.feng.init;

import com.feng.webmagic.spiderStart.FilmSpiderStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * spring容器加载完，tomcat启动后,执行run方法
 */
@Component
public class ApplicationStartInit implements CommandLineRunner {
    @Autowired
    private FilmSpiderStart filmSpiderStart;
    @Override
    public void run(String... args)  {
        try {
            //休息1s，等待Tomcat容器启动完成
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //将电影数据插入数据库中
        filmSpiderStart.start();
        filmSpiderStart.IQIYIStart();
    }
}
