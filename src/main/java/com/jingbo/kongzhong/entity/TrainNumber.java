package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jingbo.kongzhong.business.eumus.CraneType;
import com.jingbo.kongzhong.business.eumus.GoodsType;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车次管理对象
 * @author wangqiang
 * @Date: 2022/2/8 14:55
 */
@Data
@TableName("train_number")
public class TrainNumber extends Model<TrainNumber> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流水号
     **/
    private String serialNo;

    /**
     * 车次
     */
    private String trainOrder;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 是否被删除 0未删除 1已删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;


}
