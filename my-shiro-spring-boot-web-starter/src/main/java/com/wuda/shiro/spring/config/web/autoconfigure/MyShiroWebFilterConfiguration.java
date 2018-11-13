package com.wuda.shiro.spring.config.web.autoconfigure;

import com.wuda.shiro.web.filter.MyAccessControlFilter;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;


/**
 * 主要就是为了注入{@link MyAccessControlFilter},因为找了很久,实在没有找到其他好的方式来注入.
 *
 * @author wuda
 */
@Configuration
public class MyShiroWebFilterConfiguration extends ShiroWebFilterConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Override
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = super.shiroFilterFactoryBean();
        shiroFilterFactoryBean.getFilters().putAll(appendShiroPathFilters());
        return shiroFilterFactoryBean;
    }

    @Bean(name = "appendShiroPathFilters")
    @ConditionalOnMissingBean(name = "appendShiroPathFilters")
    public Map<String, Filter> appendShiroPathFilters() {
        Map<String, Filter> filterMap = new HashMap<>(1);
        MyAccessControlFilter filter = new MyAccessControlFilter();
        filterMap.put(filter.getName(), filter);
        return filterMap;
    }
}
