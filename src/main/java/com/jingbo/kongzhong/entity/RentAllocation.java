package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 配组表对象
 * @author wangqiang
 * @Date: 2022/2/9 10:29
 */
@Data
@TableName("rent_allocation")
public class RentAllocation extends Model<RentAllocation> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 封号
     */
    private String packageNo;

    /**
     * 集装箱号(唯一)
     */
    private String containerNo;

    /**
     * 车型
     */
    private String trainType;

    /**
     * 车号
     */
    private String licenseNo;

    /**
     * 标重
     */
    private BigDecimal trainWeight;

    /**
     * 配租序号
     */
    private Integer groupNo;

    /**
     * 货物名字
     */
    private String goodName;
    /**
     * 货物数量
     */
    private Integer goodCount;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 备注
     */
    private String remark ;

    /**
     * 股道
     */
    private String trackNo;

    /**
     * 客户名字
     */
    private String customerName;

    /**
     * 创建时间
     */
    private String createTime;

}
