package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jingbo.kongzhong.business.eumus.Actions;
import com.jingbo.kongzhong.business.eumus.AlarmLevel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 动作对象
 * @author wangqiang
 * @Date: 2022/2/8 15:28
 */
@Data
@TableName("cranes_actions")
public class CranesActions extends Model<CranesActions> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联车次的流水号
     */
    private String serialNo;

    /**
     * 车次
     */
    private String trainOrder;

    /**
     * 设备号
     */
    private String deviceNo;

    /**
     * 动作类型
     */
    private Actions action;

    /**
     * 动作结果
     */
    private Actions result;

    /**
     * 报警等级
     */
    private AlarmLevel alarmLevel;

    /**
     * 报警信息
     */
    private String alarmMsg;

    /**
     * 报警信息是否推送
     */
    private Boolean push;

    /**
     * 是否人工处理 0 已处理 -1未处理
     */
    private int manualProcessing;

    /**
     * 雷达距离1
     */
    private BigDecimal radarDistanceOne;

    /**
     * 雷达距离2
     */
    private BigDecimal radarDistanceTwo;

    /**
     * 抓取指令
     */
    private Integer upOrder;

    /**
     * 放下指令
     */
    private Integer downOrder;

    /**
     * 车皮载重
     */
    private BigDecimal trainWeight;

    /**
     * 装车开始时间:抓取时间
     */
    private Date startTime;

    /**
     * 装车结束时间:放下时间
     */
    private Date endTime;

    /**
     * 原集装箱号
     */
    private String originalContainerNo;

    /**
     * 校正后集装箱号
     */
    private String calibrationContainerNo;

    /**
     * 第一张集装箱图片
     */
    private String containerPicOne;
    /**
     * 第二张集装箱图片
     */
    private String containerPicTwo;

    /**
     * 集装箱重量
     */
    private BigDecimal containerWeight;

    /**
     * 原第一次车号
     */
    private String trainNoOneOld;

    /**
     * 原第二次车号
     */
    private String trainNoTwoOld;

    /**
     * 校正后第一次车号
     */
    private String trainNoOneCalibration;

    /**
     * 校正后第二次车号
     */
    private String trainNoTwoCalibration;

    /**
     * 原第一次车型
     */
    private String trainTypeOneOld;
    /**
     * 原第二次车型
     */
    private String trainTypeTwoOld;

    /**
     * 校正后第一次车型
     */
    private String trainTypeOneCalibration;

    /**
     * 校正后第二次车型
     */
    private String trainTypeTwoCalibration;
    
    /**
     * 第一次车号图片地址1
     */
    private String trainNoOnePicOne;
    /**
     * 第一次车号图片地址2
     */
    private String trainNoOnePicTwo;
    /**
     * 第一次车号图片地址3
     */
    private String trainNoOnePicThree;
    /**
     * 第一次车号图片地址4
     */
    private String trainNoOnePicFour;

    /**
     * 第二次车号图片地址1
     */
    private String trainNoTwoPicOne;
    /**
     * 第二次车号图片地址2
     */
    private String trainNoTwoPicTwo;
    /**
     * 第二次车号图片地址3
     */
    private String trainNoTwoPicThree;
    /**
     * 第二次车号图片地址4
     */
    private String trainNoTwoPicFour;

    /**
     * 创建时间
     */
    private Date createTime;
}
