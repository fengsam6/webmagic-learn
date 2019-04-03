package com.feng.webmagic.pipeline;

import com.feng.entity.Blog;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * Created by rf on 2019/4/3.
 */
@Slf4j
public class BlogModelPipeline implements PageModelPipeline<Blog>{
    @Override
    public void process(Blog blog, Task task) {
        log.info("BlogModelPipeline:{}",blog);
    }
}
