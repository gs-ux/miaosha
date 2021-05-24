package com.imooc.miaosha.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MiaoshaUser {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
