package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaUserService {
    @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(Long id){
        return miaoshaUserDao.getById(id);
    }
}
