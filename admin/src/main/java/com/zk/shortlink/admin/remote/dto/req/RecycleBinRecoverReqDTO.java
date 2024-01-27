package com.zk.shortlink.admin.remote.dto.req;


import lombok.Data;

/**
 * 回收站回复请求实体
 */
@Data
public class RecycleBinRecoverReqDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}
