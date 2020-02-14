package com.feng.conf;

import com.feng.util.CpuNumUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        任务开启一个线程就够了
        int n = 1;
        //当然了，这里设置的线程池是corePoolSize也是很关键了，自己根据业务需求设定
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(n));

    }
}
