package com.jay.modules.authc;

import com.alibaba.fastjson.JSON;
import com.jay.util.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Formatter;

/**
 * oauth2过滤器
 * Created by xiang.wei on 2018/10/11
 *
 * @author xiang.wei
 */
public class OAuth2Filter extends OncePerRequestFilter {

    private static final String JS = "<script type='text/javascript'>var wp=window.parent; if(wp!=null){while(wp.parent&&wp.parent!==wp){wp=wp.parent;}wp.location.href='%1$s';}else{window.location.href='%1$s';}</script>";
    private String loginUrl = "/login";


    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        //如果通过认证
        /*
        * Returns true if this Subject/user proved their identity during their current session by providing valid
        * credentials matching those known to the system, false otherwise.
        *
        * 如果此主题/用户在当前会话期间通过提供与系统已知的证书匹配的有效凭证来证明其身份，则返回true，否则为false。
        * */
        if (subject.isAuthenticated()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            identifyGuest(servletRequest, servletResponse);
        }
    }


    protected void identifyGuest(ServletRequest request,ServletResponse response) throws IOException {
        redirectLogin(request,response);
    }

    /**
     * 重定向到登录页
     * @param request
     * @param response
     */
    protected void redirectLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.saveRequest(request);
        //获取请求路径
        String path = WebUtils.getContextPath((HttpServletRequest) request);
        String url = loginUrl;
        if (StringUtils.isNotBlank(path) && path.length() > 1) {
            url = path + url;
        }
        if (isAjaxRequest((HttpServletRequest) request)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(JSON.toJSONString(Data.failure("您还没有登录！")));
        } else {
            response.getWriter().write(new Formatter().format(JS, url).toString());
        }
    }

    /**
     * 判断是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header)) {
            return true;
        } else {
            return false;
        }
    }

}
