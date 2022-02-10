package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 卸车清单对象
 * @author wangqiang
 * @Date: 2022/2/8 15:31
 */
@Data
@TableName("unload_detailedlist")
public class UnloadDetailedList extends Model<UnloadDetailedList> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车型
     */
    private String trainModel;

    /**
     * 设备名字
     */
    private String deviceName;

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
     * 装车开始时间
     */
    private Date startTime;

    /**
     * 卸车开始时间
     */
    private Date endTime;
}
