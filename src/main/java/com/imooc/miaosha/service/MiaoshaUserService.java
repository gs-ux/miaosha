package com.imooc.miaosha.service;

import com.alibaba.fastjson.JSON;
import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.redis.KeyPrefix;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.util.UUIDUtil;
import com.imooc.miaosha.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service
@Slf4j
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getById(Long id){
        //取缓存
        String userString = redisService.get(KeyPrefix.USER_ID + id);
        System.out.println(KeyPrefix.USER_ID + id);
        MiaoshaUser user = JSON.toJavaObject(JSON.parseObject(userString), MiaoshaUser.class);
        if(user!=null){
            return user;
        }
        //取数据库
        user = miaoshaUserDao.getById(id);
        if(user!=null){
            redisService.set(KeyPrefix.USER_ID + id,JSON.toJSONString(user));
        }
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginVO loginVO){
        if(loginVO==null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        //验证登录首先查询对应的数据库的dpPass
        MiaoshaUser user = getById(Long.parseLong(loginVO.getMobile()));

        if(user==null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //dbPass
        String dbPass = user.getPassword();

        //form表单里的pass
        String formPass = loginVO.getPassword();

        System.out.println(user);

        //MD5后的pass，也即计算出来的pass
        String calcPass = MD5Util.formPassToDBPass(formPass,user.getSalt());

        //比较两者是否相等，不相等，则异常往外抛
        if (!Objects.equals(calcPass,dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookies
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return true;
    }

    //实现复用，还有后面获取后增加cookie的时间
    public void addCookie(HttpServletResponse response,String token,MiaoshaUser user){

        String val = JSON.toJSONString(user);
        redisService.set(KeyPrefix.USER_TOKEN +token,val);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(3600*24*2);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        String val = redisService.get(KeyPrefix.USER_TOKEN+token);
        //将val转换成user对象
        MiaoshaUser user = JSON.toJavaObject(JSON.parseObject(val), MiaoshaUser.class);
        //延长有效期
        if(user!=null){
            addCookie(response,token,user);
        }
        return user;
    }
}
