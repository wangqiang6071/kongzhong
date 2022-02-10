package com.jingbo.kongzhong.business.eumus;

import lombok.Getter;

/**
 * 报警等级
 * @author wangqiang
 * @Date: 2022/2/8 17:20
 */
@Getter
public enum AlarmLevel {

    LEVELHIGH(7,"高"),
    LEVELMIDDLE(7,"中"),
    LEVELLOW(9,"低"),
    ;

    private Integer code;
    private String value;
    private AlarmLevel(Integer code, String value){
        this.code=code;
        this.value=value;
    }
}
