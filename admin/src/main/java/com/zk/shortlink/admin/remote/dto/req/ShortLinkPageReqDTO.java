package com.zk.shortlink.admin.remote.dto.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 短链接分页请求实体
 */
@Data
public class ShortLinkPageReqDTO extends Page {
    /**
     * 分组标识
     */
    private String gid;
}
