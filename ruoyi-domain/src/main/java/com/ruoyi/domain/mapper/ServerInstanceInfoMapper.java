package com.ruoyi.domain.mapper;


import com.ruoyi.domain.entity.ServerInstanceInfo;

import java.util.List;

/**
 * 服务实例信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-25
 */
public interface ServerInstanceInfoMapper
{
    /**
     * 查询服务实例信息
     *
     * @param id 服务实例信息ID
     * @return 服务实例信息
     */
    public ServerInstanceInfo selectServerInstanceInfoById(Long id);

    /**
     * 查询服务实例信息列表
     *
     * @param serverInstanceInfo 服务实例信息
     * @return 服务实例信息集合
     */
    public List<ServerInstanceInfo> selectServerInstanceInfoList(ServerInstanceInfo serverInstanceInfo);

    /**
     * 新增服务实例信息
     *
     * @param serverInstanceInfo 服务实例信息
     * @return 结果
     */
    public int insertServerInstanceInfo(ServerInstanceInfo serverInstanceInfo);

    /**
     * 修改服务实例信息
     *
     * @param serverInstanceInfo 服务实例信息
     * @return 结果
     */
    public int updateServerInstanceInfo(ServerInstanceInfo serverInstanceInfo);

    /**
     * 删除服务实例信息
     *
     * @param id 服务实例信息ID
     * @return 结果
     */
    public int deleteServerInstanceInfoById(Long id);

    /**
     * 批量删除服务实例信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteServerInstanceInfoByIds(Long[] ids);
}
