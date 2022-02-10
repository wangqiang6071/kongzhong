package com.jingbo.kongzhong.shiro.realm;

import java.util.*;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.ObjectUtil;
import com.jingbo.kongzhong.dao.UserMapper;
import com.jingbo.kongzhong.entity.User;
import com.jingbo.kongzhong.shiro.token.EasyTypeToken;
import com.jingbo.kongzhong.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * shiro身份校验核心类
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**
	 * 密码加密盐
	 */
	@Value("${shiro.password.salt}")
	private String salt;

	@Autowired
	private UserMapper userMapper;

	@Override
	public String getName() {
		return "MyRealm";
	}
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 用户信息认证.(身份验证) 是用来验证用户身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/**
		 * 用户名：Principal 密码：Credentials
		 */
		EasyTypeToken EasyToken = (EasyTypeToken) token;
		String account=EasyToken.getUsername();
		/**
		 * 登陆的账号是唯一的
		 */
		User users=userMapper.selectUserByAccount(account);
		if(ObjectUtil.isNull(users)){
			throw new UnknownAccountException("账号不存在");
		}
		if(users.getStatus().equals(UserEmus.forbidden.getMsg())){
			throw new DisabledAccountException("帐号已被禁止登录");
		}
		/**
		 * 身份认证成功：登录成功
		 * 参数1：数据库中的对象，参数2:数据库的密码，参数3:密码加密的加盐值，参数4:MyRealm名字
		 */
		return new SimpleAuthenticationInfo(users, users.getPassword(),new ByteSourceUtils(salt), getName());
	}
}