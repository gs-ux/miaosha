package com.imooc.miaosha;

import com.imooc.miaosha.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class MiaoshaApplicationTests {
    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        redisService.set("test","hello",30, TimeUnit.SECONDS);
    }

}
