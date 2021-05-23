package com.imooc.miaosha.controller;

import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/redis/set")
    public Result<Boolean> set(){
        redisService.set("k1","v1");
        return Result.success(true);
    }

    @GetMapping("/redis/get")
    public Result<String> get(){
        String res = redisService.get("k1");
        return Result.success(res);
    }
}
