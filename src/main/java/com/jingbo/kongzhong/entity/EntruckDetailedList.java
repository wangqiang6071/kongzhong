package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 装车清单对象
 * @author wangqiang
 * @Date: 2022/2/8 15:34
 */
@Data
@TableName("entruck_detailedlist")
public class EntruckDetailedList extends Model<EntruckDetailedList> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车次
     */
    private String trainOrder;

    /**
     * 车型
     */
    private String trainModel;

    /**
     * 车号
     */
    private String licenseNo;
    /**
     * 集装箱号
     */
    private String contenerNo;

    /**
     * 单个集装箱重量
     */
    private BigDecimal containerWeight;

    /**
     * 是否合格 0合格 1不合格
     */
    private Integer good;

    /**
     * 物资
     */
    private String goodsName;


    /**
     * 报警信息
     */
    private String alarmMsg;

    /**
     * 是否人工处理 0 已处理 -1未处理
     */
    private int manualProcessing;

    /**
     * 装车开始时间
     */
    private Date startTime;
    /**
     * 卸车开始时间
     */
    private Date endTime;

    /**
     * 单次装车用时 单位/秒
     */
    private Integer totalTime;

}
