package com.zk.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zk.shortlink.admin.common.convention.exception.ClientException;
import com.zk.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.zk.shortlink.admin.dao.entity.UserDO;
import com.zk.shortlink.admin.dao.mapper.UserMapper;
import com.zk.shortlink.admin.dto.request.UserLoginReqDTO;
import com.zk.shortlink.admin.dto.request.UserRegisterReqDTO;
import com.zk.shortlink.admin.dto.request.UserUpdateReqDTO;
import com.zk.shortlink.admin.dto.response.UserLoginRespDTO;
import com.zk.shortlink.admin.dto.response.UserRespDTO;
import com.zk.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.zk.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.zk.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static com.zk.shortlink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

/**
 * 用户接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper =
                Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParams) {
        if (!hasUsername(requestParams.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParams.getUsername());
        try {
            if (lock.tryLock()) {
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParams, UserDO.class));
                if (inserted < 1) {
                    throw new ClientException(USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParams.getUsername());
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParams) {
        //TODO 验证当前用户名是否为登录用户   防止通过自己的TOKEN恶意串改他人信息
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, requestParams.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParams, UserDO.class),updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("用户不存在！");
        }
        String key = "login_" + requestParam.getUsername();
        if (stringRedisTemplate.hasKey(key)) {
            throw new ClientException("用户已登录！");
        }
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put(key, uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire(key, 30L, TimeUnit.DAYS);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String token, String username) {
        return stringRedisTemplate.opsForHash().get("login_" + username, token) != null;
    }

    @Override
    public void logout(String token, String username) {
        if (checkLogin(token, username)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException("用户Token不存在或用户未登录");
    }
}
