package com.zk.shortlink.admin.test;

public class UserTableShardingTest {
    public static final String SQL = "create table t_link_goto_%d\n" +
            "(\n" +
            "    id              bigint auto_increment comment 'ID'\n" +
            "        primary key,\n" +
            "    full_short_url  varchar(128)                null comment '完整短链接',\n" +
            "    gid             varchar(32)                 null comment '分组标识'\n" +
            ")\n" +
            "    charset = utf8mb4;\n" +
            "\n";
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(String.format(SQL, i));
        }
    }
}
