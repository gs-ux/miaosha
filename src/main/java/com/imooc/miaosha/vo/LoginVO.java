package com.imooc.miaosha.vo;

import com.imooc.miaosha.validator.IsMobile;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    private String password;
}
