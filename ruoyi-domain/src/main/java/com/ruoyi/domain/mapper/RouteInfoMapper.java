package com.ruoyi.domain.mapper;

import com.ruoyi.domain.entity.RouteInfo;

import java.util.List;

/**
 * 路由信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-12-28
 */
public interface RouteInfoMapper 
{
    /**
     * 查询路由信息
     * 
     * @param id 路由信息ID
     * @return 路由信息
     */
    public RouteInfo selectRouteInfoById(Long id);

    /**
     * 查询路由信息列表
     * 
     * @param routeInfo 路由信息
     * @return 路由信息集合
     */
    public List<RouteInfo> selectRouteInfoList(RouteInfo routeInfo);

    /**
     * 新增路由信息
     * 
     * @param routeInfo 路由信息
     * @return 结果
     */
    public int insertRouteInfo(RouteInfo routeInfo);

    /**
     * 修改路由信息
     * 
     * @param routeInfo 路由信息
     * @return 结果
     */
    public int updateRouteInfo(RouteInfo routeInfo);

    /**
     * 删除路由信息
     * 
     * @param id 路由信息ID
     * @return 结果
     */
    public int deleteRouteInfoById(Long id);

    /**
     * 批量删除路由信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRouteInfoByIds(Long[] ids);

    /**
     * 查询总数
     * @return
     */
    int selectRouteInfoTotal();

    /**
     * 查询最大的下标路由信息
     * @param id
     * @return
     */
    RouteInfo selectRouteInfoByMaxNum(Long id);
}
