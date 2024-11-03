package com.jay.config;

import com.jay.modules.authc.OAuth2Filter;
import com.jay.modules.authc.OAuth2Realm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xiang.wei on 2018/10/11
 *
 * @author xiang.wei
 */
@Configuration
public class ShiroConfig {
    @Bean("sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(OAuth2Realm oAuth2Realm, CookieRememberMeManager rememberMeManager,  SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
//        securityManager.setCacheManager(cacheShiroManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);

        return securityManager;
    }

    /**
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

//        默认登录的url
        shiroFilter.setLoginUrl("/login");
//        登录成功后跳转的url
        shiroFilter.setSuccessUrl("/");
//        没有权限跳转的url
        shiroFilter.setUnauthorizedUrl("/error/reject.html");

        /**
         * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
         */
//        oauth过滤
        HashMap<String, Filter> filters = new HashMap<>();
        filters.put("authc", new OAuth2Filter());
        shiroFilter.setFilters(filters);



        /**
         *
         *    TODO 待修改
         *    配置shiro拦截器链
         *    过滤的路径
         *   anon 不需要认证
         *   authc 需要认证
         *   test 验证通过或RememberMe登录的都可以
         *
         * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
         *
         * 顺序从上到下,优先级依次降低
         *
         *
         */
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/test*", "authc");
        filterMap.put("/test/**", "authc");
        filterMap.put("/post/**", "authc");

        filterMap.put("/admin", "authc,perms[admin]");
        filterMap.put("/admin/**", "authc,perms[admin]");

        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     * @param rememberMeCookie
     * @return
     */
    @Bean("rememberMeManager")
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7*24*60*60); //7天
        return simpleCookie;
    }

    //    @Bean
//    public CacheManager getCacheShiroManager(EhCacheManagerFactoryBean ehCache) {
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManager(ehCache.getObject());
//        return ehCacheManager;
//    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * Shiro 生命周期处理器：
     * 用于在实现Initializable接口的Shiro bean初始化时调用Initializable接口回调（例如：UserRealm）
     * 在实现了Destroyable接口的Shiro bena销毁时调用Destroyable 接口回调(例如:DefaultSecurityManager)
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 必须要
     * 启用shiro授权注解拦截方式，AOP式方法级权限检查
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
