package com.zk.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zk.shortlink.admin.common.convention.result.Result;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.zk.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.zk.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

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
}
