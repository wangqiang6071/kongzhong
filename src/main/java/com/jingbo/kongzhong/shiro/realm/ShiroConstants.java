package com.jingbo.kongzhong.shiro.realm;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
public class ShiroConstants {
    /**
     * 系统活跃用户缓存
     */
    public static final String SYS_USERCACHE = "sys-userCache";
    /**
     * 用户登录是否被锁定：redisKey前缀：锁定时间1小时
     */
    public static final String SHIRO_IS_LOCK = "shiro_is_lock_";
    /**
     * 用户登录次数计数:redisKey前缀: 大于5次被锁定
     */
    public static final  String SHIRO_LOGIN_COUNT = "shiro_login_count_";
}
