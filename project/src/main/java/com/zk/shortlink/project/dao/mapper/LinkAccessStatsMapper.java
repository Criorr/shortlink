package com.zk.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zk.shortlink.project.dao.entity.LinkAccessStatsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 短链接基础访问监控持久层
 */
public interface LinkAccessStatsMapper extends BaseMapper<LinkAccessStatsDO> {
    /**
     * 记录基础访问监控
     */
    @Insert("INSERT INTO t_link_access_stats ( full_short_url, gid, DATE, pv, uv, uip, HOUR, weekday, create_time, update_time, del_flag )" +
            "VALUES" +
            "( #{linkAccessStats.fullShortUrl}, #{linkAccessStats.gid}, #{linkAccessStats.date},#{linkAccessStats.pv} , #{linkAccessStats.uv}, #{linkAccessStats.uip}, #{linkAccessStats.hour}, #{linkAccessStats.weekday}, NOW(), NOW(), 0 ) \n" +
            "ON DUPLICATE KEY UPDATE pv = #{linkAccessStats.pv} + pv," +
            "uv = #{linkAccessStats.uv} + uv," +
            "uip = #{linkAccessStats.uip} + uip;")
    void shortLinkStats(@Param("linkAccessStats") LinkAccessStatsDO linkAccessStatsDO);
}
