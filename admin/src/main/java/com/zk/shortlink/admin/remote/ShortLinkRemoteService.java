package com.zk.shortlink.admin.remote;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.remote.dto.req.*;
import com.zk.shortlink.admin.remote.dto.resp.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ShortLinkRemoteService {
    /**
     * 创建短链接
     * @return 短链接创建信息
     */
    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam) {
        String resultStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 分页查询短链接
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回结果
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gid", requestParam.getGid());
        paramMap.put("current", requestParam.getCurrent());
        paramMap.put("size", requestParam.getSize());
        String resultStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page", paramMap);
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 查询短链接分组内数量
     * @param requestParam 短链接分组内数量请求参数
     * @return 短链接分组内数量列表
     */
    default Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(List<String> requestParam) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("requestParam", requestParam);
        String resultStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count", paramMap);
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }


    /**
     * 修改短链接
     * @param requestParam 短链接修改请求参数
     */
    default Result<Void> updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        String resultStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/update", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 根据url获取title
     * @param requestParam url链接
     * @return title
     */
    default Result<String> getTitleByUrl(String requestParam) {
        String resultStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/urlTitle?url=" + requestParam);
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }


    /**
     * 保存回收站
     * @param requestParam 保存回收站请求实体
     * @return
     */
    default Result<Void> saveRecycleBin(RecycleBinSaveReqDTO requestParam) {
        String resultStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/save", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 分页查询回收站短链接
     */
    default Result<IPage<ShortLinkPageRespDTO>> recycleBinPageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("gidList", requestParam.getGidList());
        paramMap.put("current", requestParam.getCurrent());
        paramMap.put("size", requestParam.getSize());
        String resultStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/page", paramMap);
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 恢复短链接
     */
    default Result<Void> recoverRecycleBin(RecycleBinRecoverReqDTO requestParam) {
        String resultStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/recover", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 移除短链接
     */
    default Result<Void> removeRecycleBin(RecycleBinRemoveReqDTO requestParam) {
        String resultStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/remove", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultStr, new TypeReference<>() {
        });
    }

    /**
     * 访问单个短链接指定时间内监控数据
     *
     * @param requestParam 访问短链接监控请求参数
     * @return 短链接监控信息
     */
    default Result<ShortLinkStatsRespDTO> oneShortLinkStats(ShortLinkStatsReqDTO requestParam) {
        String resultBodyStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/stats", BeanUtil.beanToMap(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {
        });
    }


    /**
     * 访问单个短链接指定时间内监控访问记录数据
     *
     * @param requestParam 访问短链接监控访问记录请求参数
     * @return 短链接监控访问记录信息
     */
    default Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(requestParam, false, true);
        stringObjectMap.remove("orders");
        stringObjectMap.remove("records");
        String resultBodyStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/stats/access-record", stringObjectMap);
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {
        });
    }
}
