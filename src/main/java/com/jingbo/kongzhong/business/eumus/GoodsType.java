package com.jingbo.kongzhong.business.eumus;

import lombok.Getter;

/**
 * @author wangqiang
 * @Date: 2022/2/8 18:23
 * 货物类型
 */
@Getter
public enum GoodsType {

    /**
     * 货物类型: TODO 待补全
     */
    PETROLEUM(1,"石油"),
    FOODSTUFF(2,"粮食"),
    STEELCOIL(3,"钢卷"),
    ;

    private Integer code;
    private String value;
    private GoodsType(Integer code, String value){
        this.code=code;
        this.value=value;
    }
}
