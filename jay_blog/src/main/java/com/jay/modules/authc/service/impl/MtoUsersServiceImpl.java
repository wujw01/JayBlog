package com.jay.modules.authc.service.impl;

import com.jay.modules.authc.entity.MtoUsers;
import com.jay.modules.authc.dao.MtoUsersMapper;
import com.jay.modules.authc.service.MtoUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jay.modules.authc.vo.AccountProfile;
import com.jay.modules.authc.vo.BadgesCount;
import com.jay.modules.user.service.MtoNotifyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@Service
@Transactional
public class MtoUsersServiceImpl extends ServiceImpl<MtoUsersMapper, MtoUsers> implements MtoUsersService {
    @Autowired
    private MtoNotifyService mtoNotifyService;
    @Override
    public AccountProfile login(String username, String password) {
//       查询用户
        MtoUsers user = new MtoUsers();
        user.setUsername(username);
        user = baseMapper.selectOne(user);

        AccountProfile profile = new AccountProfile(user.getId(), user.getUsername());;
        Assert.notNull(user, "账户不存在");

        Assert.state(!StringUtils.equals(password, user.getPassword()), "密码错误");

        user.setLastLogin(new Date());
        baseMapper.updateById(user);

        BeanUtils.copyProperties(user,profile);

//        通知
        BadgesCount badgesCount = new BadgesCount();

        badgesCount.setNotifies(mtoNotifyService.unreadForMe(user.getId()));

        profile.setBadgesCount(badgesCount);
        return profile;
    }
}
