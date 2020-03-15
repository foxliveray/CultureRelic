package com.edu.zju.culture.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author y4oung
 * @date 2020/3/14 12:53 AM
 * @description
 */
public class WebUtils {
    /**
     * 得到requset
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     * 得到session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }
}
