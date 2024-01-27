package com.zk.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.remote.ShortLinkRemoteService;
import com.zk.shortlink.admin.remote.dto.req.*;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.zk.shortlink.admin.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecycleBinController {
    /**
     * TODO 后续重构为SpringCloud Feign 调用
     */
    private final ShortLinkRemoteService shortLinkService = new ShortLinkRemoteService() {
    };

    private final RecycleBinService recycleBinService;


    /**
     * 保存回收站
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        return shortLinkService.saveRecycleBin(requestParam);
    }

    /**
     * 分页查询回收站短链接
     */
    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return recycleBinService.pageShortLink(requestParam);
    }

    /**
     * 恢复短链接
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        return shortLinkService.recoverRecycleBin(requestParam);
    }

    /**
     * 移除短链接
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        return shortLinkService.removeRecycleBin(requestParam);
    }
}
