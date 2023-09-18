package com.wypaperplane.shiroapi.shiro;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

public class AdminSessionManager extends DefaultWebSessionManager {

    public AdminSessionManager() {
        setGlobalSessionTimeout(MILLIS_PER_HOUR * 20);
        setSessionIdCookieEnabled(false);
        setSessionValidationSchedulerEnabled(true); // 启用会话验证调度器
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader("admin-token");
        if (ObjectUtils.isEmpty(sessionId)) {
            return super.getSessionId(request, response);
        } else {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
    }
}
