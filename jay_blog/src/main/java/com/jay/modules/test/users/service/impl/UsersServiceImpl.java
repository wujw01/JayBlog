package com.jay.modules.test.users.service.impl;

import com.jay.modules.test.entity.Users;
import com.jay.modules.test.dao.UsersMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jay.modules.test.users.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-09-19
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
