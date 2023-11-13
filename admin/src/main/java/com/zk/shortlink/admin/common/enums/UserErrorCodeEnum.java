package com.zk.shortlink.admin.common.enums;
import com.zk.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * 用户错误码枚举
 */
public enum UserErrorCodeEnum implements IErrorCode {
    USER_NULL("B000200", "用户记录不存在"),
    USER_EXIST("B000200", "用户记录不存在");

    private String code;
    private String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}