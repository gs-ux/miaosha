package com.imooc.miaosha.vo;

import com.imooc.miaosha.domain.MiaoshaUser;
import lombok.Data;

@Data
public class GoodsDetailVO {
	private int miaoshaStatus = 0;
	private int remainSeconds = 0;
	private GoodsVO goods ;
	private MiaoshaUser user;
}
