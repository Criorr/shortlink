package com.zk.shortlink.project.dto.resp;

import lombok.Data;

/**
 * 统计短链接组内数量返回实体
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组内短链接数量
     */
    private Integer shortLinkCount;
}
