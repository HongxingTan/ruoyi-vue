package com.ruoyi.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class RoutePredicate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 路由信息表id */
    private Long routeInfoId;

    /** 断言类型 */
    @Excel(name = "断言类型")
    private String predicateType;

    /** 断言规则 */
    @Excel(name = "断言规则")
    private String predicateRule;

    /** 第几个 */
    @Excel(name = "序号")
    private Integer serialNumber;

    public Long getRouteInfoId() {
        return routeInfoId;
    }

    public void setRouteInfoId(Long routeInfoId) {
        this.routeInfoId = routeInfoId;
    }

    public String getPredicateType() {
        return predicateType;
    }

    public void setPredicateType(String predicateType) {
        this.predicateType = predicateType;
    }

    public String getPredicateRule() {
        return predicateRule;
    }

    public void setPredicateRule(String predicateRule) {
        this.predicateRule = predicateRule;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
}
