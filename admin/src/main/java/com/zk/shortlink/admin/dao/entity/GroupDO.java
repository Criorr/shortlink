package com.zk.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 短链接分组实体类
 */

@TableName("t_group")
@Data
public class GroupDO {
    /**
     * ID
     */
    private Long id;

    /**
     * 分组标识
     */
    private Long gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识 0:未删除 1：已删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean delFlag;
}
