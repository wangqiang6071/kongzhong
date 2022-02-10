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
@TableName("user_role")
public class UserRole extends Model<UserRole> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private Integer roleId;

    private Date createTime;

}