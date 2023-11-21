package com.zk.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zk.shortlink.admin.dao.entity.GroupDO;
import com.zk.shortlink.admin.dto.request.ShortLinkGroupUpdateReqDTO;
import com.zk.shortlink.admin.dto.response.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增短链接分组
     * @param groupName 短链接分组名
     */
    void saveGroup(String groupName);

    /**
     * 查询短链接用户分组集合
     * @return 短链接用户分组集合
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * 修改短链接分组
     * @param requestParam 短链接修改参数
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);
}
