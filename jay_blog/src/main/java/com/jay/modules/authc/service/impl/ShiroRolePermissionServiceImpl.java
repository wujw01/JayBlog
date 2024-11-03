package com.jay.modules.authc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jay.modules.authc.dao.ShiroPermissionMapper;
import com.jay.modules.authc.entity.ShiroPermission;
import com.jay.modules.authc.entity.ShiroRolePermission;
import com.jay.modules.authc.dao.ShiroRolePermissionMapper;
import com.jay.modules.authc.service.ShiroRolePermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色-权限值 服务实现类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@Service
public class ShiroRolePermissionServiceImpl extends ServiceImpl<ShiroRolePermissionMapper, ShiroRolePermission> implements ShiroRolePermissionService {

    @Autowired
    private ShiroPermissionMapper shiroPermissionMapper;
    @Override
    public List<ShiroPermission> findPermission(long roleId) {
        List<ShiroRolePermission> rps = baseMapper.selectList(new EntityWrapper<ShiroRolePermission>().in("role_id", String.valueOf(roleId)));
        if (rps == null) {
            return null;
        }
        List<ShiroPermission> rets = null;
        Set<Long> pids = new HashSet<>();
        rps.forEach(rp -> pids.add(rp.getPermissionId()));
        rets = shiroPermissionMapper.selectBatchIds(pids);
        return rets;
    }
}
