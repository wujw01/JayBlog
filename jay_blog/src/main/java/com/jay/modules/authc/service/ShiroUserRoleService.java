package com.jay.modules.authc.service;

import com.baomidou.mybatisplus.service.IService;
import com.jay.modules.authc.entity.ShiroRole;
import com.jay.modules.authc.entity.ShiroUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
public interface ShiroUserRoleService extends IService<ShiroUserRole> {

    /**
     * 查询用户已有的角色Id
     * @param userId
     * @return
     */
    List<Long> listRoleIds(long userId);

    /**
     * 查询用户已有的角色和权限
     * @param userId
     * @return
     */
    List<ShiroRole> listRoles(long userId);
}
