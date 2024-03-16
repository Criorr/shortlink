package com.zk.shortlink.admin.test;

public class UserTableShardingTest {
    public static final String SQL = "CREATE TABLE `t_link_%d`\n" +
            "(\n" +
            "    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "    `domain`          varchar(128)                                   DEFAULT NULL COMMENT '域名',\n" +
            "    `short_uri`       varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '短链接',\n" +
            "    `full_short_url`  varchar(128)                                   DEFAULT NULL COMMENT '完整短链接',\n" +
            "    `origin_url`      varchar(1024)                                  DEFAULT NULL COMMENT '原始链接',\n" +
            "    `click_num`       int(11) DEFAULT '0' COMMENT '点击量',\n" +
            "    `gid`             varchar(32)                                    DEFAULT 'default' COMMENT '分组标识',\n" +
            "    `favicon`         varchar(256)                                   DEFAULT NULL COMMENT '网站图标',\n" +
            "    `enable_status`   tinyint(1) DEFAULT NULL COMMENT '启用标识 0：启用 1：未启用',\n" +
            "    `created_type`    tinyint(1) DEFAULT NULL COMMENT '创建类型 0：接口创建 1：控制台创建',\n" +
            "    `valid_date_type` tinyint(1) DEFAULT NULL COMMENT '有效期类型 0：永久有效 1：自定义',\n" +
            "    `valid_date`      datetime                                       DEFAULT NULL COMMENT '有效期',\n" +
            "    `describe`        varchar(1024)                                  DEFAULT NULL COMMENT '描述',\n" +
            "    `total_pv`        int(11) DEFAULT NULL COMMENT '历史PV',\n" +
            "    `total_uv`        int(11) DEFAULT NULL COMMENT '历史UV',\n" +
            "    `total_uip`       int(11) DEFAULT NULL COMMENT '历史UIP',\n" +
            "    `create_time`     datetime                                       DEFAULT NULL COMMENT '创建时间',\n" +
            "    `update_time`     datetime                                       DEFAULT NULL COMMENT '修改时间',\n" +
            "    `del_time`        bigint(20) DEFAULT '0' COMMENT '删除时间戳',\n" +
            "    `del_flag`        tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',\n" +
            "    PRIMARY KEY (`id`),\n" +
            "    UNIQUE KEY `idx_unique_full-short-url` (`full_short_url`,`del_time`) USING BTREE\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(String.format(SQL, i));
        }
    }
}
