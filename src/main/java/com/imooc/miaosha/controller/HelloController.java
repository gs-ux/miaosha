package com.imooc.miaosha.controller;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello Wrold!";
    }

    @GetMapping("/success")
    public Result<String> success(){
        return Result.success("hello,imooc!");
    }

    @GetMapping("/err")
    public Result<String> error(){
        return Result.error(CodeMsg.SERVICE_ERROR);
    }

}
