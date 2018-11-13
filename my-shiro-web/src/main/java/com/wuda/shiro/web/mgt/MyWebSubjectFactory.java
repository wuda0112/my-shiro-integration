package com.wuda.shiro.web.mgt;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 目的是为了禁止创建session,但是必须配合自定义的filter才有效,因为shiro自带的filter显示的使用了session.
 *
 * @author wuda
 */
public class MyWebSubjectFactory extends DefaultWebSubjectFactory {

    public Subject createSubject(SubjectContext context) {
        // 不创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }

}
