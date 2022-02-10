package com.jingbo.kongzhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingbo.kongzhong.dao.UserMapper;
import com.jingbo.kongzhong.entity.User;
import com.jingbo.kongzhong.exception.StringException;
import com.jingbo.kongzhong.service.IUserService;
import com.jingbo.kongzhong.shiro.token.EasyTypeToken;
import com.jingbo.kongzhong.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户密码加密次数
     */
    @Value("${shiro.password.random}")
    private int randoms;
    /**
     * 用户密码加密盐值
     */
    @Value("${shiro.password.salt}")
    private String salt;

    @Override
    public Map<String, Object> userLogin(String account, String password) {
        EasyTypeToken token=new EasyTypeToken(account,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException("账号错误");
        } catch (IncorrectCredentialsException e) {
            throw new IncorrectCredentialsException("密码错误");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (subject.isAuthenticated()) {
            map.put("authToken", subject.getSession().getId());
            return  map;
        }
        return map;
    }

    @Override
    public String UserRegister(User account) {
        if(StringUtils.isBlank(account.getAccount())){
            throw new StringException("账号不能为空");
        }
        if(StringUtils.isBlank(account.getPassword())){
            throw new StringException("密码不能为空");
        }
        if(!Utils.validateStrEnglish(account.getAccount())){
            throw new StringException("账号只能为字母和数字,不能使用汉字特殊字符以及空格");
        };
        if(account.getAccount().length() >= 10){
            throw new StringException("账号长度不能超过10");
        };
        if(account.getAccount().contains("system")){
            throw new StringException("system不能作为账号");
        };
        if(!Utils.validateStrEnglish(account.getPassword())){
            throw new StringException("密码只能为字母和数字,不能使用汉字特殊字符以及空格");
        }
        User user=new User(account.getAccount(),Utils.addMd5Hash(account.getPassword(),randoms,salt),account.getUserName());
        this.baseMapper.insert(user);
        return"用户注册成功！";
    }

    @Override
    public String userLoginOut() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "账号退出成功";
    }
}
