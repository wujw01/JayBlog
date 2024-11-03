package com.jay.modules.user.service;

import com.jay.modules.user.entity.MtoNotify;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author jay.xiang
 * @since 2018-10-13
 */
public interface MtoNotifyService extends IService<MtoNotify> {

    /**
     * 未读消息
     * @param ownId
     * @return
     */
    int unreadForMe(long ownId);
}
