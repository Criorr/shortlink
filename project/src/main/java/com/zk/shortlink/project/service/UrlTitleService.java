package com.zk.shortlink.project.service;

/**
 * Url标题接口层
 */
public interface UrlTitleService {
    /**
     * 根据URl获取Title
     * @param requestParam url
     * @return Title
     */
    String getTitleByUrl(String requestParam);
}
