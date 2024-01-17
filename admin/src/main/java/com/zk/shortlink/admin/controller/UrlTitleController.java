package com.zk.shortlink.admin.controller;

import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.remote.ShortLinkRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Url标题控制层
 */
@RestController
@RequiredArgsConstructor
public class UrlTitleController {
    private final ShortLinkRemoteService shortLinkService = new ShortLinkRemoteService() {
    };

    /**
     * 根据url获取title
     * @param requestParam url链接
     * @return title
     */
    @GetMapping("/api/short-link/admin/v1/urlTitle")
    public Result<String> getTitleByUrl(@RequestParam("url") String requestParam) {
        return shortLinkService.getTitleByUrl(requestParam);
    }
}
