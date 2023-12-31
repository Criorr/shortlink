package com.zk.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zk.shortlink.project.dao.entity.ShortLinkDO;
import com.zk.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.zk.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.zk.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

/**
 * 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * 分页查询短链接
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询短链接分组内数量
     * @param requestParam 短链接分组内数量请求参数
     * @return 短链接分组内数量列表
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    /**
     * 修改短链接
     * @param requestParam 短链接修改请求参数
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);
}
