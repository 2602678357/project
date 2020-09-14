package com.bhsoftware.projectserver.config;

import com.bhsoftware.projectserver.filter.CustomerUserFilter;
import com.bhsoftware.projectserver.shiro.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm); //将Realm注入到SecurityManager中。
        securityManager.setCacheManager(ehCacheManager()); //注入缓存对象。
        securityManager.setRememberMeManager(cookieRememberMeManager());//注入rememberMeManager;
        return securityManager;
    }

//    过滤链配置
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
//        //设定用户没有登录认证时的跳转链接、没有授权时的跳转链接
////        shiroFilter.setLoginUrl("/login");
////        shiroFilter.setUnauthorizedUrl("/");
        Map <String, Filter> filters = shiroFilter.getFilters();
        filters.put("customerUser", new CustomerUserFilter());
        //过滤器链配置
        Map<String, String> filterMap = new LinkedHashMap();
        filterMap.put("/api/sms/*", "anon");
        filterMap.put("/api/login", "anon");
        filterMap.put("/api/*", "anon");
        filterMap.put("/api/categories/*", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/**","customerUser");
////        filterMap.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }


    //关于Shiro的Bean生命周期的管理
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * cookie对象;
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        return manager;
    }


}
