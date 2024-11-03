package com.jay.modules.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jay.lang.Consts;
import com.jay.modules.user.entity.MtoNotify;
import com.jay.modules.user.dao.MtoNotifyMapper;
import com.jay.modules.user.service.MtoNotifyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
@Service
public class MtoNotifyServiceImpl extends ServiceImpl<MtoNotifyMapper, MtoNotify> implements MtoNotifyService {

    @Override
    @Transactional(readOnly = true)
    public int unreadForMe(long ownId) {
        return baseMapper.selectCount(new EntityWrapper<MtoNotify>().where("own_id={0} AND status={1}",ownId, Consts.UNREAD));
    }
}
