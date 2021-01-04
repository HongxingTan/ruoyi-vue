package com.ruoyi.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 路由信息对象 route_info
 * 
 * @author ruoyi
 * @date 2020-12-28
 */
public class RouteInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** route_id */
    @Excel(name = "route_id")
    private String routeId;

    /** route_desc */
    @Excel(name = "route描述")
    private String routeDesc;

    /** 目标地址 */
    @Excel(name = "目标地址")
    private String uri;

    /** 第几个 */
    @Excel(name = "序号")
    private Integer serialNumber;

    /** 排序 */
    @Excel(name = "排序")
    private Integer order;

    /** 断言 */
    private List<RoutePredicate> routePredicates;

    /** 过滤器 */
    private List<RouteFilter> routeFilters;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRouteId(String routeId) 
    {
        this.routeId = routeId;
    }

    public String getRouteId() 
    {
        return routeId;
    }
    public void setUri(String uri) 
    {
        this.uri = uri;
    }

    public String getUri() 
    {
        return uri;
    }
    public void setOrder(Integer order) 
    {
        this.order = order;
    }

    public Integer getOrder() 
    {
        return order;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc;
    }

    public List<RoutePredicate> getRoutePredicates() {
        return routePredicates;
    }

    public void setRoutePredicates(List<RoutePredicate> routePredicates) {
        this.routePredicates = routePredicates;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<RouteFilter> getRouteFilters() {
        return routeFilters;
    }

    public void setRouteFilters(List<RouteFilter> routeFilters) {
        this.routeFilters = routeFilters;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("routeId", getRouteId())
            .append("routeDesc", getRouteDesc())
            .append("uri", getUri())
            .append("order", getOrder())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
