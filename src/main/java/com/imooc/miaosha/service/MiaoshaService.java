package com.imooc.miaosha.service;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVO goods) {
        //减库存、下订单、生成订单
        goodsService.reduceStock(goods);

        return orderService.createOrder(user,goods);
    }
}
