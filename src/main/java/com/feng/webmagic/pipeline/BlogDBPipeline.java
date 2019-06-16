package com.feng.webmagic.pipeline;

import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by rf on 2019/4/3.
 */
@Component
@Slf4j
public class BlogDBPipeline implements Pipeline {
    @Autowired
    private BlogService blogService;
    @Override
    public void process(ResultItems resultItems, Task task) {
      Blog blog = resultItems.get("blog");
        log.info("**********************blog:{}*********************************", blog.toString());
        blogService.add(blog);
    }
}
