package com.zk.shortlink.admin.dto;

import lombok.Data;

/**
 * 用户返回参数响应
 */
@Data
public class UserRespDTO {
    /**
     *  ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

}
