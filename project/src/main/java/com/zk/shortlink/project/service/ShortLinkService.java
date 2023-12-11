package com.zk.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zk.shortlink.project.dao.entity.ShortLinkDO;
import com.zk.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
