package com.jay.modules.authc.service;

import com.jay.modules.authc.entity.ShiroRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
public interface ShiroRoleService extends IService<ShiroRole> {

    Map<Long, ShiroRole> findByIds(Set<Long> ids);

}
