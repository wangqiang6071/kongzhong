package com.jingbo.kongzhong.business.Strategy;

import com.alibaba.fastjson.JSONObject;
import com.jingbo.kongzhong.business.Factory.CraneFactory;
import com.jingbo.kongzhong.business.entity.Model.SuperModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangqiang
 * @Date: 2022/1/21 14:55
 * 吊车的策略方式：根据不同的吊车产生不同的行为数据
 */
@Slf4j
@Component
public class CraneStrategy {

    /**
     * 装车
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    private void entrunk(JSONObject json){
        CraneFactory.getPlugStrategy(getModel(json).getCraneType()).entrunk(json);
    }

    /**
     * 卸车
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    private void unload(JSONObject json){
        CraneFactory.getPlugStrategy(getModel(json).getCraneType()).unload(json);
    }

    /**
     * 地面平移
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */

    private void groundtranslation(JSONObject json){
        CraneFactory.getPlugStrategy(getModel(json).getCraneType()).groundtranslation(json);
    }

    /**
     * 车厢平移
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    private void carriagetranslation(JSONObject json){
        CraneFactory.getPlugStrategy(getModel(json).getCraneType()).carriagetranslation(json);
    }


    /**
     * 根据不同的行为执行不同的动作
     */
    public void chooseAction(JSONObject json){
        switch(getModel(json).getAction()){
            case ENTRUNK:
                entrunk(json);
                break;
            case UNLOAD:
                unload(json);
                break;
            case GROUNDTRANSLATION:
                groundtranslation(json);
                break;
            case CARRIAGETRANSLATION:
                carriagetranslation(json);
                break;
        }
    }

    /**
     * 获取当前json数据中吊车的动作
     * 获取当前json数据中吊车类型
     * TODO 根据实际的逻辑 判断当前的吊车类型和动作
     */
    public SuperModel getModel(JSONObject json){
        return JSONObject.parseObject(json.toString(), SuperModel.class);
    }
}
