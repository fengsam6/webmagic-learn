package com.feng;

import com.feng.webmagic.spiderStart.FilmSpiderStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by rf on 2019/6/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmSpiderTest {
    @Autowired
    private FilmSpiderStart filmSpiderStart;

    @Test
    public void FilmSpiderStartTest() {
        filmSpiderStart.start();
    }
}
