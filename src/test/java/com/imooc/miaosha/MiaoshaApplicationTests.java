package com.imooc.miaosha;

import com.alibaba.fastjson.JSON;
import com.imooc.miaosha.domain.MiaoshaUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MiaoshaApplicationTests {

    @Test
    void contextLoads() {
        MiaoshaUser user = new MiaoshaUser();
        user.setId(17367105077L);
        String userString = JSON.toJSONString(user);

        System.out.println(userString);

        System.out.println("---------");

        MiaoshaUser user1 = JSON.toJavaObject(JSON.parseObject(userString), MiaoshaUser.class);
        System.out.println(user1);
    }

}
