package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.service.MiaoshaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    /**
    @RequestMapping("/to_list")
    public String toList(Model model,
                         @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String cookieToken,
                         @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN,required = false)String paramToken){
        //参数判断
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        MiaoshaUser user = miaoshaUserService.getByToken(token);
        model.addAttribute("user",user);
        return "goods_list";
    }
     **/
    @RequestMapping("/to_list")
    public String toList(Model model,MiaoshaUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }
}
