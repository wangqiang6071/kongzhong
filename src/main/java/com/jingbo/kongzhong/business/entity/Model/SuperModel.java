package com.jingbo.kongzhong.business.entity.Model;

import com.alibaba.fastjson.JSONObject;
import com.jingbo.kongzhong.business.eumus.Actions;
import lombok.Data;
import java.io.Serializable;

/**
 * @author wangqiang
 * @Date: 2022/1/21 15:19
 * 动作数据对象
 */
@Data
public class SuperModel implements Serializable {
    /**
     * 吊车的行为
     */
    private Actions action;

    /**
     * 吊车的类型
     */
    private String craneType;


    /**
     *  JSON转类的对象
     * @param json:吊车的所有数据
     */
    public static <T> T changeObj(JSONObject json,Class<T>cls){
        return JSONObject.parseObject(json.toJSONString(), cls);
    }
}
