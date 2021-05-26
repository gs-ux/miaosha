package com.imooc.miaosha.redis;

//项目中一些热点key的前缀
public class KeyPrefix {
    //用户token缓存前缀
    public static final String USER_TOKEN = "MiaoshaUserKey:tk:";

    //用户user缓存前缀
    public static final String USER_ID = "MiaoshaUserKey:id:";

    //商品列表详情页面缓存
    public static final String GOODS_LIST = "GoodsKey:gl";
}
