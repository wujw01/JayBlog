package com.jay.modules.authc.service;

import com.jay.modules.authc.entity.MtoUsers;
import com.baomidou.mybatisplus.service.IService;
import com.jay.modules.authc.vo.AccountProfile;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
public interface MtoUsersService extends IService<MtoUsers> {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    AccountProfile login(String username,String password);
}
