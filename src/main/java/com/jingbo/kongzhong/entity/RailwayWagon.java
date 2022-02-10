package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jingbo.kongzhong.business.eumus.Actions;
import com.jingbo.kongzhong.business.eumus.CraneType;
import lombok.Data;

import java.util.Date;

/**
 * 车皮对象
 * @author wangqiang
 * @Date: 2022/2/9 11:18
 */
@Data
@TableName("railway_wagon")
public class RailwayWagon extends Model<RailwayWagon> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 所属机构
     */
    private Long deptId;

    /**
     * 车次
     */
    private String trainNo;

    /**
     * 到场日期
     */
    private Date arrivalDate;

    /**
     * 车号
     */
    private String truckNo;

    /**
     * 车型
     */
    private CraneType trainType;

    /**
     * 装卸类型:装车/卸车
     */
    private Actions actionType;

    /**
     * 股道号
     */
    private String trackNo;

    /**
     * 备注
     */
    private String remark;

}
