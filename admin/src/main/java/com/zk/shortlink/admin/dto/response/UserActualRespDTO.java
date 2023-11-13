package com.zk.shortlink.admin.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zk.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;
import lombok.Data;

/**
 * 用户返回参数响应
 */
@Data
public class UserActualRespDTO {
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
