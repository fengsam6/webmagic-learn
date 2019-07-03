package com.feng.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * Created by rf on 2019/7/3.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(JedisPoolProperties.class)
public class RedisSchedulerConf {
    @Autowired
    private JedisPoolProperties jedisPoolProperties;
    @Bean
    public RedisScheduler redisScheduler(@Qualifier("jedisPool") JedisPool jedisPool){
        return new RedisScheduler(jedisPool);
    }

    @Bean
    public JedisPool jedisPool()  throws Exception{
        JedisPoolConfig jedisPoolConf = new JedisPoolConfig();
        jedisPoolConf.setMaxIdle(jedisPoolProperties.getMaxIdle());
        jedisPoolConf.setMinIdle(jedisPoolProperties.getMinIdle());
        jedisPoolConf.setMaxTotal(jedisPoolProperties.getMaxTotal());
        return new JedisPool(jedisPoolConf, jedisPoolProperties.getHost(), jedisPoolProperties.getPort(), 2000, jedisPoolProperties.getPassword());
    }
}
