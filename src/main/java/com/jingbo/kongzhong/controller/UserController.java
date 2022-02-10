package com.jingbo.kongzhong.controller;

import com.jingbo.kongzhong.common.ServerResponse;
import com.jingbo.kongzhong.entity.User;
import com.jingbo.kongzhong.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wangqiang
 * @Date: 2022/2/8 10:58
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;
    /**
     * @author ：wangqiang
     * @param uer : user参数
     * @date ：2022-02-08 11:11
     */
    @PostMapping("register")
    public ServerResponse<String> UserRegister(@RequestBody User uer){
        return ServerResponse.createBySuccess(userService.UserRegister(uer));
    }
    /**
     * @author ：wangqiang
     * @param account : 账号
     * @param password : 密码
     * @date ：2022-02-08 11:13
     */
    @PostMapping("login")
    public ServerResponse<Map<String, Object>> UserRegister(@RequestParam("account") String account, @RequestParam("password") String password){
        return ServerResponse.createBySuccess(userService.userLogin(account,password));
    }

    /**
     * 账号退出
     * @author ：wangqiang
     * @date ：2022-02-08 11:44
     */
    @GetMapping("login_out")
    public ServerResponse<Map<String, Object>> UserLoginOut(){
        return ServerResponse.createBySuccess(userService.userLoginOut());
    }
}
