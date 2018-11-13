# 简介
Shiro自带的Web，是按照session的模式传递Subject，但是在实际中，应该已经很少使用这种方式了吧，尤其是微服务环境下，只能使用无状态的方式管理登录信息，因此，基于已有的Shiro Springboot，再一次整合，达到使用无状态的方式管理登录信息的目的。

# 使用步骤

- 导入my-shiro-spring-boot-web-starter依赖
- implements AuthenticationToken

```
token其实就好像一个参数，Realm使用token到数据源中查询用户以及权限信息
```


- 自定义Realm
```
@Bean
public Realm realm() {
  realm.setAuthenticationTokenClass(MyAuthenticationToken.class);
  ...
}
```

- 自定义AccessControlFilter extends AccessControlFilter

```
1. 根据request/response生成AuthenticationToken
2. Subject.login(token)
```

- 注册AccessControllerFilter到shiro环境中,shiro自带的其他Filter依然可用

```
// 追加 name/filter pair
@Bean(name = "appendShiroPathFilters")
public Map<String, Filter> appendShiroPathFilters() {
    Map<String, Filter> filterMap = new HashMap<>(1);
    MyAccessControlFilter filter = new MyAccessControlFilter();
    filterMap.put(filter.getName(), filter);
    return filterMap;
}
```

- 使用自定义的AccessControlFilter处理给定模式的路径

```
@Bean
public ShiroFilterChainDefinition shiroFilterChainDefinition() {
    DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

    chainDefinition.addPathDefinition("/**", MyAccessControlFilter.name);
    return chainDefinition;
}
```

# Shiro Springboot

[shiro springboot](https://shiro.apache.org/spring-boot.html)
