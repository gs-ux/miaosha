package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class MiaoshaUserService {
    @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(Long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(LoginVO loginVO){
        if(loginVO==null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        //验证登录首先查询对应的数据库的dpPass
        MiaoshaUser user = getById(Long.parseLong(loginVO.getMobile()));

        if(user==null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        log.info("数据库的user: "+user);
        //dbPass
        String dbPass = user.getPassword();
        log.info("dbPass: "+dbPass);

        //form表单里的pass
        String formPass = loginVO.getPassword();
        //MD5后的pass，也即计算出来的pass
        String calcPass = MD5Util.formPassToDBPass(formPass,user.getSalt());
        log.info("calcPass: "+calcPass);

        //比较两者是否相等，不相等，则异常往外抛
        if (!Objects.equals(calcPass,dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return true;
    }
}
