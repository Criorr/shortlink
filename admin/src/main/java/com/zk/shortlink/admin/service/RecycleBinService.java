package com.zk.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;


/**
 * 回收站接口层
 */
public interface RecycleBinService {
    /**
     * 分页查询短链接
     *
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回结果
     */
    Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
