package com.feng.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by rf on 2019/6/19.
 */

@Configuration
public class DruidConfiguration {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }
}
