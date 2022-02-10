package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jingbo.kongzhong.business.eumus.CraneType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 吊车对象
 * @author wangqiang
 * @Date: 2022/2/8 19:20
 */
@Data
@TableName("cranes_device")
public class CranesDevice extends Model<CranesDevice> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属部门
     */
    private Integer deptId;

    /**
     * 设备类型:1=正面吊,2=龙门吊,3=叉车
     */
    private CraneType craneType;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备内码
     */
    private String deviceCode;

    /**
     * 设备ip
     */
    private String deviceIp;

    /**
     * 吊具重量最大值
     */
    private BigDecimal slingWeightMax;

    /**
     * 吊具重量最小值
     */
    private BigDecimal slingWeightMin;

    /**
     * 雷达最大距离
     */
    private BigDecimal radarMax;

    /**
     * 雷达最小值
     */
    private BigDecimal radarMin;

    /**
     * 扣重
     */
    private BigDecimal buckleWeight;

    /**
     * 序号
     */
    private Integer orderNo;

    /**
     * 状态：正常 暂停
     */
    private String status;

    /**
     * 触发重量
     */
    private BigDecimal triggerWeight;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

}
