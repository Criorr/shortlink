package com.zk.shortlink.project.service.impl;

import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zk.shortlink.project.common.convention.exception.ServiceException;
import com.zk.shortlink.project.dao.entity.ShortLinkDO;
import com.zk.shortlink.project.dao.mapper.ShortLinkMapper;
import com.zk.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.zk.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.zk.shortlink.project.service.ShortLinkService;
import com.zk.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 短链接接口实现层
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    final RBloomFilter<String> shortLinkCreateCachePenetrationBloomFilter;


    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .fullShortUrl(fullShortUrl)
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .build();
        try {
            baseMapper.insert(shortLinkDO);
            /**
             * 布隆过滤器在并发操作下可能出现多个线程对同一个元素进行判断（因为是先判断，在添加进布隆过滤器，中间会有时间间隔）
             *  布隆过滤器误判元素
             */
        } catch (DuplicateKeyException ex) {
            //误判短链接处理
            LambdaQueryWrapper<ShortLinkDO> eq = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl,fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(eq);
            if (hasShortLinkDO != null) {
                log.warn("短链接:{}重复入库", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        shortLinkCreateCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .gid(requestParam.getGid())
                .originUrl(requestParam.getOriginUrl())
                .build();
    }
    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        Integer customGenerateCount = 0;
        String shortUri;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("短链接频繁生成，请稍后重试");
            }
            String originUrl = requestParam.getOriginUrl();
            originUrl += System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortLinkCreateCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + shortUri)) {
                break;
            }
            customGenerateCount++;
        }
        return shortUri;
    }
}
