package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Data
@TableName("role")
public class Role extends Model<Role> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色的名字
     */
    private String roleName;

    /**
     * 所属部门
     */
    private Integer deptId;
    /**
     * 角色的状态0 可用 1不可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色中的权限
     */
    @TableField(exist =  false)
    List<Permission> permissionList;

}