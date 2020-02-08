package com.feng.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IPUtilsTest {
    @Autowired
    HttpServletRequest request;

    @Test
    public void getIpAddr() throws Exception {
     String ip =   IPUtils.getIpAddr(request);
     System.out.print(ip);
    }
}