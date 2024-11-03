package com.jay.modules.authc.service;

import com.jay.modules.authc.entity.ShiroPermission;
import com.jay.modules.authc.entity.ShiroRolePermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色-权限值 服务类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
public interface ShiroRolePermissionService extends IService<ShiroRolePermission> {

    /**
     * 获取角色
     * @param roleId
     * @return
     */
    List<ShiroPermission> findPermission(long roleId);
}
