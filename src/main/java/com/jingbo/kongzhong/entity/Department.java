package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * @author wangqiang
 * @Date: 2022/2/10 11:11
 */
@Data
@TableName("department")
public class Department extends Model<Department> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名字
     */
    private String deptName;
    /**
     * 所属公司
     */
    private String companyName;

    /**
     * 创建时间
     */
    private Date createTime;
}
