package com.longzhihun.jediscluster.springbootjedisclusterdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/test")
    public String getNodes() {
        redisTemplate.opsForValue().set("test", "value");
        return (String) redisTemplate.opsForValue().get("test");
    }
}
