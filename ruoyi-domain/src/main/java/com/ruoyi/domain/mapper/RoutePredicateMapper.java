package com.ruoyi.domain.mapper;

import com.ruoyi.domain.entity.RoutePredicate;

import java.util.List;

public interface RoutePredicateMapper {

    /**
     * 新增断言
     * @param routePredicates
     */
    void insertRoutePredicate(List<RoutePredicate> routePredicates);

    /**
     * 根据路由信息id查断言
     * @param id
     * @return
     */
    List<RoutePredicate> selectRoutePredicateByRouteInfoId(Long id);

    /**
     * 删除所有断言
     * @param id
     */
    void deleteForRouteInfoId(Long id);
}
