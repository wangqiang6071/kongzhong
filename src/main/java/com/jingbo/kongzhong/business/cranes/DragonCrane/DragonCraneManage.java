package com.jingbo.kongzhong.business.cranes.DragonCrane;

import com.alibaba.fastjson.JSONObject;
import com.jingbo.kongzhong.business.Factory.CraneFactory;
import com.jingbo.kongzhong.business.entity.Model.SuperModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangqiang
 * @Date: 2022/1/21 13:48
 * 龙门吊业务处理类
 */
@Slf4j
@Component
public class DragonCraneManage extends CraneFactory {
    /**
     * 装车
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    @Override
    public void entrunk(JSONObject model){
        DragonCraneModel craneModel = SuperModel.changeObj(model, DragonCraneModel.class);
        log.info("龙门吊的装车==》"+JSONObject.toJSONString(craneModel));
    }

    /**
     * 卸车
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    @Override
    public void unload(JSONObject model){
        DragonCraneModel craneModel = SuperModel.changeObj(model, DragonCraneModel.class);
    }

    /**
     * 地面平移
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    @Override
    public void groundtranslation(JSONObject model){
        DragonCraneModel craneModel = SuperModel.changeObj(model, DragonCraneModel.class);
    }

    /**
     * 车厢平移
     * @author ：wangqiang
     * @date ：2022-01-21 15:13
     */
    @Override
    public void carriagetranslation(JSONObject model){
        DragonCraneModel craneModel = SuperModel.changeObj(model, DragonCraneModel.class);
    }


    /**
     * 装车清单
     */
    @Override
    public void entruckDetailedList() {}

    /**
     * 卸车清单
     */
    @Override
    public void unloadDetailedList() {}
}
