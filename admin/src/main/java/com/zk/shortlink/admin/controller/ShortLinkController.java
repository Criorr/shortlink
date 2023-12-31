package com.zk.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接控制层
 */
@RestController
public class ShortLinkController {
    /**
     * TODO 后续重构为SpringCloud Feign 调用
     */
    private final ShortLinkRemoteService shortLinkService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkService.createShortLink(requestParam);
    }

    /**
     * 查询短链接分页
     * @param requestParam 短链接请求参数
     * @return 短链接分页
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkService.pageShortLink(requestParam);
    }


    /**
     * 修改短链接
     * @param requestParam 短链接修改请求参数
     */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        return shortLinkService.updateShortLink(requestParam);
    }
}
