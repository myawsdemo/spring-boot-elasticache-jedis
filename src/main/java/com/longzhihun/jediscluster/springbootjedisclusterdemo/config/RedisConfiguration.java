package com.longzhihun.jediscluster.springbootjedisclusterdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisConnectionFactory JedisClusterConfig() {
        //配置信息放配置文件
        List<String> nodes = Collections.singletonList("hostxxxxxxxx:6379");
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(nodes);
        //建议结合SecretManager来存储password
        clusterConfiguration.setPassword("12345678!!!!!!!!");
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(10000));
        jedisClientConfiguration.useSsl();
        jedisClientConfiguration.usePooling();
        return new JedisConnectionFactory(clusterConfiguration, jedisClientConfiguration.build());
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

}
