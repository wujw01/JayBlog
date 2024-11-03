package com.jay.modules.authc.service.impl;

import com.jay.modules.authc.entity.ShiroPermission;
import com.jay.modules.authc.dao.ShiroPermissionMapper;
import com.jay.modules.authc.service.ShiroPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限值表 服务实现类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@Service
public class ShiroPermissionServiceImpl extends ServiceImpl<ShiroPermissionMapper, ShiroPermission> implements ShiroPermissionService {

}
