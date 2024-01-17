package com.zk.shortlink.project.controller;

import com.zk.shortlink.project.common.convention.result.Result;
import com.zk.shortlink.project.common.convention.result.Results;
import com.zk.shortlink.project.service.UrlTitleService;
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
    private final UrlTitleService urlTitleService;

    /**
     * 根据url获取title
     * @param requestParam url链接
     * @return title
     */
    @GetMapping("/api/short-link/urlTitle")
    public Result<String> getTitleByUrl(@RequestParam("url") String requestParam) {
        return Results.success(urlTitleService.getTitleByUrl(requestParam));
    }
}
