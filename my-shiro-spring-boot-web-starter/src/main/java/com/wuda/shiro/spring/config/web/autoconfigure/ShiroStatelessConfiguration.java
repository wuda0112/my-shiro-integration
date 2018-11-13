package com.wuda.shiro.spring.config.web.autoconfigure;

import com.wuda.shiro.web.filter.MyAccessControlFilter;
import com.wuda.shiro.web.mgt.MyWebSubjectFactory;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroStatelessConfiguration {

    @Bean(name = "subjectFactory")
    public SubjectFactory getSubjectFactory() {
        return new MyWebSubjectFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/**", MyAccessControlFilter.name);
        return chainDefinition;
    }
}
