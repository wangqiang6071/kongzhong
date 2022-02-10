package com.jingbo.kongzhong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingbo.kongzhong.entity.User;

import java.util.Map;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
public interface IUserService extends IService<User> {
    /**
     * 账号登陆
     * @param account 账号
     * @param password 密码
     */
    public Map<String, Object> userLogin(String account, String password);

    /**
     * 账号注册
     * @param account
     */
    public String UserRegister(User account);

    /**
     * 账号退出
     */
    public String userLoginOut();
}
