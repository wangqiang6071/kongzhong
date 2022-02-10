package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jingbo.kongzhong.business.eumus.AlarmLevel;
import lombok.Data;

import java.util.Date;

/**
 * 报警对象
 * @author wangqiang
 * @Date: 2022/2/8 19:15
 */
@Data
@TableName("alarms")
public class Alarms extends Model<Alarms> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报警名称
     */
    private String alarmName;
    /**
     * 司机风险等级
     */
    private AlarmLevel driverLevel;
    /**
     * 值班室风险等级
     */
    private AlarmLevel dutyRoomLevel;
    /**
     * 处理意见
     */
    private String suggestion;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

}
