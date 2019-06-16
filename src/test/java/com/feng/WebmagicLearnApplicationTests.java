package com.feng;

import com.feng.entity.Blog;
import com.feng.servcie.BlogService;
import com.feng.webmagic.spiderStart.BlogSpiderStart;
import com.feng.webmagic.spiderStart.FilmSpiderStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebmagicLearnApplicationTests {
    @Autowired
    private BlogService blogService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void add() {
        blogService.add(new Blog("test", "content", "11", "11", "0"));
    }

}
