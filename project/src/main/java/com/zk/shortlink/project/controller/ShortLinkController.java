package com.zk.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zk.shortlink.project.common.convention.result.Result;
import com.zk.shortlink.project.common.convention.result.Results;
import com.zk.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.zk.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.zk.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.zk.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     * @return
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 修改短链接
     * @param requestParam 短链接修改请求参数
     */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 查询短链接分页
     * @param requestParam 短链接请求参数
     * @return 短链接分页
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * 查询短链接分组内数量
     * @param requestParam 短链接分组内数量请求参数
     * @return 短链接分组内数量列表
     */
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }
}
