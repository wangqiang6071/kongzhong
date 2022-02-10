package com.jingbo.kongzhong.business.eumus;

import lombok.Getter;

/**
 * @author wangqiang
 * @Date: 2022/1/21 13:51
 * 吊车的动作枚举类
 */
@Getter
public enum Actions {

    ENTRUNK(1,"装车"),
    UNLOAD(2,"卸车"),
    GROUNDTRANSLATION(3,"地面平移"),
    CARRIAGETRANSLATION(4,"车厢平移"),
    RESULTSUCCESS(5,"操作成功"),
    RESULTFAILLED(6,"操作失败"),
    ;

    private Integer code;
    private String value;
    private Actions(Integer code,String value){
        this.code=code;
        this.value=value;
    }
}
