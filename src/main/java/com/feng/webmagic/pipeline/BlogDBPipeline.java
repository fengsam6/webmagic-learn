package com.feng.webmagic.pipeline;

import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import com.feng.util.CpuNumUtils;
import com.feng.util.ThreadPoolUtils;
import com.feng.util.ThreadSleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.concurrent.*;

/**
 * 将网页爬虫数据保存数据库中
 */
@Component
@Slf4j
public class BlogDBPipeline implements Pipeline {
    @Autowired
    private BlogService blogService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Blog blog = resultItems.get("blog");
        if(blog==null){
           log.debug("一条数据爬虫失败");
           return;
        }
        ExecutorService executorService = ThreadPoolUtils.creatIOsThreadPool();
        log.info("**********************blog:{}*********************************", blog.toString());
        executorService.execute(()->{
            try {
                blogService.add(blog);
            } catch (Exception e) {
                //数据重复异常，只打印一条错误提示
                if (e instanceof DataIntegrityViolationException || e instanceof ConstraintViolationException) {
                    log.error("这条数据已经存在，插入忽略");
                } else {
                    e.printStackTrace();
                }
            }
        });

        ThreadSleepUtils.sleepNsAndYield(4);
    }
}
