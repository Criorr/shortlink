package com.zk.shortlink.admin.dto.request;

import lombok.Data;

/**
 * 短链接分组创建参数
 */
@Data
public class ShortLinkGroupReqDTO {
    /**
     * 分组名
     */
    private String name;
}
