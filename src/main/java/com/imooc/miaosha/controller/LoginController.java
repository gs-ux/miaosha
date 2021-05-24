package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    //定位到login页面
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(LoginVO loginVO){
        log.info(loginVO.toString());
        //验证登录首先查询对应的数据库的dpPass
        MiaoshaUser user = miaoshaUserService.getById(Long.parseLong(loginVO.getMobile()));

        log.info("数据库的user: "+user);
        //dbPass
        String dbPass = user.getPassword();
        log.info("dbPass: "+dbPass);

        //form表单里的pass
        String formPass = loginVO.getPassword();
        //MD5后的pass，也即计算出来的pass
        String calcPass = MD5Util.formPassToDBPass(formPass,user.getSalt());
        log.info("calcPass: "+calcPass);

        //比较两者是否相等
        if (Objects.equals(calcPass,dbPass)) {
            return Result.success(true);
        }else{
            //不相等
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
    }
}
