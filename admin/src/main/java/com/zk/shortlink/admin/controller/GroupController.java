package com.zk.shortlink.admin.controller;

import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.common.convention.result.Results;
import com.zk.shortlink.admin.dto.request.ShortLinkGroupReqDTO;
import com.zk.shortlink.admin.dto.request.ShortLinkGroupUpdateReqDTO;
import com.zk.shortlink.admin.dto.response.ShortLinkGroupRespDTO;
import com.zk.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     *  新增短链接分组
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> saveGroup(@RequestBody ShortLinkGroupReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     *  查询短链接分组
     */
    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     *  修改短连接分组
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }
}
