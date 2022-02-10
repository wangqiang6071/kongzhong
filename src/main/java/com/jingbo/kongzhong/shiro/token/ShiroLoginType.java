package com.jingbo.kongzhong.shiro.token;

import lombok.Getter;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Getter
public enum ShiroLoginType {
    PASSWORD("password"),
    NOPASSWD("nopassword");

    private String code;

    private ShiroLoginType(String code) {
        this.code = code;
    }
}
