package com.jingbo.kongzhong.shiro.cache;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import java.io.Serializable;

/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
public class WebseionDao extends CachingSessionDAO {

    public WebseionDao(CacheManager cachManager){
        setCacheManager(cachManager);
    }
    /**
     *  更新session
     */
    @Override
    protected void doUpdate(Session session) {

    }
    /**
     * 删除session
     */
    @Override
    protected void doDelete(Session session) {

    }
    /**
     * 生成sessionID
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }
    /**
     * 读取session
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }
}
