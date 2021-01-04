package com.ruoyi.domain.service;

import java.util.List;

import com.ruoyi.domain.entity.RouteInfo;

/**
 * 路由信息Service接口
 * 
 * @author ruoyi
 * @date 2020-12-28
 */
public interface IRouteInfoService 
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
     * 查询总数
     * @return
     */
    public int selectRouteInfoTotal();

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
     * 批量删除路由信息
     * 
     * @param ids 需要删除的路由信息ID
     * @return 结果
     */
    public int deleteRouteInfoByIds(Long[] ids);

    /**
     * 删除路由信息信息
     * 
     * @param id 路由信息ID
     * @return 结果
     */
    public int deleteRouteInfoById(Long id);
}
