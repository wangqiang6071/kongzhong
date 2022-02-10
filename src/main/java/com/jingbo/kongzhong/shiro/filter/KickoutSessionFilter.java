package com.jingbo.kongzhong.shiro.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jingbo.kongzhong.dao.UserMapper;
import com.jingbo.kongzhong.entity.User;
import lombok.Data;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 登录帐号控制过滤器
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */

@Data
public class KickoutSessionFilter extends AccessControlFilter {

    /**
     * 同一个用户最大会话数 -1 不限制
     **/
    @Value("${shiro.password.maxSession}")
    private int maxSession;

    /**
     * 踢出之前登录的/之后登录的用户 默认false踢出之前登录的用户
     **/
    @Value("${shiro.password.kickoutAfter}")
    private boolean kickoutAfter;

    /**
     * 踢出后到的地址
     **/
    @Value("${shiro.password.address}")
    private String kickoutUrl;

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    /**
     * 设置Cache的key的前缀
     */
    public void setCacheManager(CacheManager cacheManager) {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache("login-session");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Autowired
    private UserMapper userListMapper;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered() || maxSession == -1) {
            /**
             * 如果没有登录或用户最大会话数为-1，直接进行之后的流程
             */
            return true;
        }
        try {
            Session session = subject.getSession();
            // 当前登录用户
            Map<String,Object>map=new HashMap<>();
            map.put("username",subject.getPrincipal());
            List<User> srcUserLists = userListMapper.selectByMap(map);
            if(srcUserLists.get(0)==null){
                throw new ExecutorException("账号不存在");
            }
            String loginName = srcUserLists.get(0).getAccount();
            Serializable sessionId = session.getId();
            // 读取缓存用户 没有就存入
            Deque<Serializable> deque = cache.get(loginName);
            System.err.println("sessionId===>"+deque.getFirst());
            if (deque == null) {
                // 初始化队列
                deque = new ArrayDeque<Serializable>();
            }

            // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
            if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
                // 将sessionId存入队列
                deque.push(sessionId);
                // 将用户的sessionId队列缓存
                cache.put(loginName, deque);
            }

            // 如果队列里的sessionId数超出最大会话数，开始踢人
            while (deque.size() > maxSession) {
                Serializable kickoutSessionId = null;
                // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
                if (kickoutAfter) {
                    // 踢出后者
                    kickoutSessionId = deque.removeFirst();
                } else {
                    // 踢出前者
                    kickoutSessionId = deque.removeLast();
                }
                // 踢出后再更新下缓存队列
                cache.put(loginName, deque);
                try {
                    // 获取被踢出的sessionId的session对象
                    Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                    if (null != kickoutSession) {
                        // 设置会话的kickout属性表示踢出了
                        kickoutSession.setAttribute("kickout", true);
                    }
                } catch (Exception e) {
                    // 面对异常，我们选择忽略
                }
            }

            // 如果被踢出了，(前者或后者)直接退出，重定向到踢出后的地址
            if ((Boolean) session.getAttribute("kickout") != null && (Boolean) session.getAttribute("kickout") == true) {
                // 退出登录
                subject.logout();
                saveRequest(request);
                return isAjaxResponse(request, response);
            }
            return true;
        } catch (Exception e) {
            return isAjaxResponse(request, response);
        }
    }
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private boolean isAjaxResponse(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (isAjaxRequest(req)) {
            renderString(res, objectMapper.writeValueAsString("您已在别处登录，请您修改密码或重新登录"));
        } else {
            WebUtils.issueRedirect(request, response, kickoutUrl);
        }
        return false;
    }

    /**
     * 将字符串渲染到客户端
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 是否是Ajax异步请求
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }
        String uri = request.getRequestURI();
        if (inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }
        String ajax = request.getParameter("__ajax");
        if (inStringIgnoreCase(ajax, "json", "xml")) {
            return true;
        }
        return false;
    }
    /**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 去空格
     */
    private static String trim(String str) {
        return (str == null ? "" : str.trim());
    }
}
