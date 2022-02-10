package com.jingbo.kongzhong.business.eumus;

import lombok.Getter;

/**
 * @author wangqiang
 * @Date: 2022/1/21 13:51
 * 吊车的类型枚举类
 */
@Getter
public enum CraneType {

    DRAGONCRANE(1,"龙门吊"),
    FRONTCRANE(2,"正面吊"),
    FORKLIFT(3,"叉车"),
    GONDOLA(4,"敞车"),
    SCOOTER(5,"板车"),

    ;

    private Integer code;
    private String value;
    private CraneType(Integer code, String value){
        this.code=code;
        this.value=value;
    }
}
