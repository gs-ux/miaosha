package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String k1,String v1){
        stringRedisTemplate.opsForValue().set(k1,v1);
    }

    public void set(String k1, String v1, long time, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(k1,v1,time,timeUnit);
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}
