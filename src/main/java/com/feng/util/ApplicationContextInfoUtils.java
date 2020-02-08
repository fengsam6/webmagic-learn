package com.feng.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;

public class ApplicationContextInfoUtils {
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextInfoUtils.class);
    public static void printSystemInfo(ApplicationContext applicationContext) throws Exception {
        Environment environment = applicationContext.getEnvironment();
        String port = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
//        HttpServletRequest request = HttpServletRequestUtils.getHttpServletRequest();
        String ip = "localhost";
        String baseUrl = "http://"+ip+":"+port;
        String indexUrl = baseUrl;
        if(contextPath!=null){
            indexUrl = baseUrl+contextPath;
        }
        logger.info("欢迎访问爬虫系统，系统首页访问url：{}",indexUrl);
        logger.info("后端 druid 监控中心url：{},用户名 admin，密码 admin",indexUrl+"/druid");

    }
}
