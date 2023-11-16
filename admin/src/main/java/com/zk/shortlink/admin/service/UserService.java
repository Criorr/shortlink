package com.zk.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zk.shortlink.admin.dao.entity.UserDO;
import com.zk.shortlink.admin.dto.request.UserLoginReqDTO;
import com.zk.shortlink.admin.dto.request.UserRegisterReqDTO;
import com.zk.shortlink.admin.dto.request.UserUpdateReqDTO;
import com.zk.shortlink.admin.dto.response.UserLoginRespDTO;
import com.zk.shortlink.admin.dto.response.UserRespDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     *  查看用户名是否存在
     *
     * @param username 用户名
     * @return 已存在返回 False 否则返回 True
     */
    Boolean hasUsername(String username);

    /**
     *  用户注册
     *
     * @param requestParams 注册请求参数
     */
    void register(UserRegisterReqDTO requestParams);

    /**
     *  修改用户信息
     * @param requestParams 修改用户信息请求参数
     */
    void update(UserUpdateReqDTO requestParams);

    /**
     * 用户登录
     * @param requestParam 用户登录请求参数
     * @return 用户登录返回参数 Token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     * @param token 用户Token
     * @param username 用户名
     * @return 是否登录
     */
    Boolean checkLogin(String token, String username);
}
