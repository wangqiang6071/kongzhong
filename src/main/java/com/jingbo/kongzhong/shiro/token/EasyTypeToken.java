package com.jingbo.kongzhong.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
public class EasyTypeToken extends UsernamePasswordToken{

	private static final long serialVersionUID = 7103399585505230877L;
    /**
     * 自定义的枚举类
     * 密码类型标记
     */
	@Getter
	@Setter
	private ShiroLoginType type;
	
    public EasyTypeToken() {
        super();
    }

    public EasyTypeToken(String username, String password, ShiroLoginType type, boolean rememberMe,  String host) {
        super(username, password, rememberMe,  host);
        this.type = type;
    }
    /**1免密登录**/
    public EasyTypeToken(String username) {
        super(username, "", false, null);
        this.type = ShiroLoginType.NOPASSWD;
    }
    /**2账号密码登录*/
    public EasyTypeToken(String username, String password) {
        super(username, password, false, null);
        this.type = ShiroLoginType.PASSWORD;
    }
}
