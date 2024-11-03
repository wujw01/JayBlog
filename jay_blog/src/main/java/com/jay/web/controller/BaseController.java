package com.jay.web.controller;

import com.jay.util.MD5;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xiang.wei on 2018/10/14
 *
 * @author xiang.wei
 */
@Component
public class BaseController {

    @Value("${site.theme:default}")
    private String theme;
    /**
     * 创建token
     * @param username
     * @param password
     * @return
     */
    protected AuthenticationToken createToken(String username, String password) {
        return new UsernamePasswordToken(username, MD5.md5(password));
    }

    /**
     * @param view
     * @return
     */
    protected String view(String view) {
        return "/" + theme + view;
    }
}
