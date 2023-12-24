package com.zk.shortlink.admin.dto.response;

import lombok.Data;

/**
 * 短链接分组返回实体对象
 */
@Data
public class ShortLinkGroupRespDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;


    /**
     * 分组排序
     */
    private Integer sortOrder;

    /**
     * 组内短链接数量
     */
    private Integer shortLinkCount;
}
