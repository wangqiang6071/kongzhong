package com.jingbo.kongzhong.shiro.realm;

import lombok.Getter;

/**
 * @author wangqiang
 * @Date: 2022/2/8 10:00
 */
@Getter
public enum UserEmus {
    //可用
    allow("可用",0),
    //禁用
    forbidden("禁用",1);

    String msg;
    Integer code;

    UserEmus(String msg,Integer code) {
        this.code = code;
        this.msg = msg;
    }
}
