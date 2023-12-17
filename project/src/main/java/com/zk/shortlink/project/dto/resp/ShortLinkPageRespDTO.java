package com.zk.shortlink.project.dto.resp;

import lombok.Data;

import java.util.Date;

/**
 * 短链接分页返回实体
 */
@Data
public class ShortLinkPageRespDTO{
    /**
     * ID
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 网站图标
     */
    private String favicon;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 有效期类型 0：永久有效 1：用户自定义
     */
    private Integer validDateType;

    /**
     * 有效期
     */
    private Date validDate;
}
