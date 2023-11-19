package com.zk.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zk.shortlink.admin.dao.entity.GroupDO;
import com.zk.shortlink.admin.dao.mapper.GroupMapper;
import com.zk.shortlink.admin.service.GroupService;
import com.zk.shortlink.admin.toolkit.RandomIdGenerator;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomIdGenerator.generateRandomId();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getName, null)
                //TODO 设置用户名
                .eq(GroupDO::getGid, gid);
        GroupDO groupDO = baseMapper.selectOne(queryWrapper);
        return groupDO == null;
    }
}
