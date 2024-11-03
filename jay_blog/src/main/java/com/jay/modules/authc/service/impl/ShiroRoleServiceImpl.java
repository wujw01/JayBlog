package com.jay.modules.authc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jay.modules.authc.dao.ShiroRoleMapper;
import com.jay.modules.authc.entity.ShiroRole;
import com.jay.modules.authc.service.ShiroRolePermissionService;
import com.jay.modules.authc.service.ShiroRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@Service
public class ShiroRoleServiceImpl extends ServiceImpl<ShiroRoleMapper, ShiroRole> implements ShiroRoleService {
    @Autowired
    private ShiroRolePermissionService shiroRolePermissionService;

    @Override
    public Map<Long, ShiroRole> findByIds(Set<Long> ids) {
        List<ShiroRole> list = baseMapper.selectBatchIds(ids);
        Map<Long, ShiroRole> ret = new HashMap<>();
        for (ShiroRole shiroRole : list) {
            shiroRole.setPermissions(shiroRolePermissionService.findPermission(shiroRole.getId()));

            ret.put(shiroRole.getId(), shiroRole);
        }
        return null;
    }
}
