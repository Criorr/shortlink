package com.zk.shortlink.admin.controller;

import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.common.convention.result.Results;
import com.zk.shortlink.admin.dto.request.ShortLinkGroupReqDTO;
import com.zk.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     *  创建分组
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }
}
