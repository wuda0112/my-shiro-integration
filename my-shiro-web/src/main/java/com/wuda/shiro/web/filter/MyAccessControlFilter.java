package com.wuda.shiro.web.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 在这里,根据rquest/response pair获取用户登录信息,然后{@link org.apache.shiro.subject.Subject}登录.
 *
 * @author wuda
 */
public class MyAccessControlFilter extends AccessControlFilter {

    public final static String name = "MyAccessControlFilter";

    public String getName() {
        return name;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("【MyAccessControlFilter】只是一个让流程通畅的占位符," +
                "请自定义org.apache.shiro.web.filter.AccessControlFilter." +
                "当前Subject.session=" + subject.getSession(false));
        return true;
    }
}
