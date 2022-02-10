package com.jingbo.kongzhong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.util.Date;
/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Data
@TableName("permission")
public class Permission extends Model<Permission> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名字
     */
    private String permissionName;

    /**
     * 权限关键值
     */
    private String permissionKey;

    /**
     * 权限url
     */
    private String permissionUrl;

    /**
     * 权限状态 0 可用 1不可用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

}