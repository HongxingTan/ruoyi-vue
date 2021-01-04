package com.ruoyi.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class RouteFilter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 路由信息表id */
    private Long routeInfoId;

    /** filter类型 */
    @Excel(name = "断言类型")
    private String filterType;

    /** filter规则 */
    @Excel(name = "断言规则")
    private String filterRule;

    /** 第几个 */
    @Excel(name = "序号")
    private Integer serialNumber;

    public Long getRouteInfoId() {
        return routeInfoId;
    }

    public void setRouteInfoId(Long routeInfoId) {
        this.routeInfoId = routeInfoId;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterRule() {
        return filterRule;
    }

    public void setFilterRule(String filterRule) {
        this.filterRule = filterRule;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
}
