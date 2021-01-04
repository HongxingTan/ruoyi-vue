package com.ruoyi.domain.mapper;

import com.ruoyi.domain.entity.RouteFilter;

import java.util.List;

public interface RouteFilterMapper {

    /**
     * 新增filter信息配置
     * @param routeFilters
     */
    void insertRouteFilter(List<RouteFilter> routeFilters);

    /**
     * 删除所有filter
     * @param id
     */
    void deleteForRouteInfoId(Long id);

    /**
     * 查询所有filter
     * @param id
     * @return
     */
    List<RouteFilter> selectRouteFilterByRouteInfoId(Long id);
}
