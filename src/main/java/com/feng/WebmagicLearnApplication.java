package com.feng;

import com.feng.util.ApplicationContextInfoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class WebmagicLearnApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(WebmagicLearnApplication.class, args);
		ApplicationContextInfoUtils.printSystemInfo(applicationContext);
	}

}
