package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车型载重管理
 * @author wangqiang
 * @Date: 2022/2/8 19:37
 */
@Data
@TableName("vehicle_load")
public class VehicleLoad extends Model<VehicleLoad> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属部门id
     */
    private Integer deptId;

    /**
     * 车型
     */
    private String trainType;

    /**
     * 车型名字
     */
    private String trainName;

    /**
     * 车型
     */
    private String trainModel;

    /**
     * 载重值
     */
    private String loadValue;

    /**
     * 开始号
     */
    private BigDecimal startNo;
    /**
     * 结束号
     */
    private BigDecimal endNo;
    /**
     * 换长
     */
    private BigDecimal changeLength;
    /**
     * 自重
     */
    private BigDecimal selfValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;
}
