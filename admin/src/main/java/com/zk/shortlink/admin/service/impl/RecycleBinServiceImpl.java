package com.zk.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zk.shortlink.admin.common.biz.user.UserContext;
import com.zk.shortlink.admin.common.convention.exception.ServiceException;
import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.dao.entity.GroupDO;
import com.zk.shortlink.admin.dao.mapper.GroupMapper;
import com.zk.shortlink.admin.remote.ShortLinkRemoteService;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.zk.shortlink.admin.service.RecycleBinService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 短链接回收站接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {
    private final GroupMapper groupMapper;
    private final ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException("用户无分组信息");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).collect(Collectors.toList()));
        return shortLinkRemoteService.recycleBinPageShortLink(requestParam);
    }
}
