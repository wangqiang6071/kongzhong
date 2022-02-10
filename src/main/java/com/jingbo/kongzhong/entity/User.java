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
@TableName("user")
public class User extends Model<User> {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String account;

    private String password;

    private String phone;

    private String email;

    private Integer status;

    private String headUrl;

    private Integer sex;

    private Date createTime;

    public User(){}

    public User(String account,String password,String userName){
        this.account=account;
        this.password=password;
        this.userName=userName;
    }
}