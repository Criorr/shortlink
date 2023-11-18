package com.zk.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zk.shortlink.admin.database.BaseDO;
import lombok.Data;

/**
 * 用户实体类
 */
@TableName("t_user")
@Data
public class UserDO extends BaseDO {
    /**
     *  ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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

    /**
     * 注销时间
     */
    private Long deletionTime;


}
