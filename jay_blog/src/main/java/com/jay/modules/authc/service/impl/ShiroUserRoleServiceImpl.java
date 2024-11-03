package com.jay.modules.authc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jay.modules.authc.entity.ShiroRole;
import com.jay.modules.authc.entity.ShiroUserRole;
import com.jay.modules.authc.dao.ShiroUserRoleMapper;
import com.jay.modules.authc.service.ShiroRoleService;
import com.jay.modules.authc.service.ShiroUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@Service
public class ShiroUserRoleServiceImpl extends ServiceImpl<ShiroUserRoleMapper, ShiroUserRole> implements ShiroUserRoleService {
    @Autowired
    private ShiroUserRoleMapper shiroUserRoleMapper;
    @Autowired
    private ShiroRoleService shiroRoleService;


    @Override
    public List<Long> listRoleIds(long userId) {
        List<ShiroUserRole> list = shiroUserRoleMapper.selectList(new EntityWrapper<ShiroUserRole>().eq("user_id", userId));
        if (list == null) {
            return null;
        }
        List<Long> roleIds = new ArrayList<>();
        list.forEach(po -> roleIds.add(po.getRoleId()));
        return roleIds;
    }

    @Override
    public List<ShiroRole> listRoles(long userId) {
        List<Long> roleIds = listRoleIds(userId);
        return new ArrayList<>(shiroRoleService.selectBatchIds(roleIds));
    }

}
