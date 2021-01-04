package com.ruoyi.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 网关限流对象 gateway_flow_rule
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
public class GatewayFlowRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long gatewayFlowRuleId;

    /** RouteID */
    @Excel(name = "RouteID")
    private String resource;

    /** 阈值类型 */
    @Excel(name = "阈值类型")
    private Long grade;

    /** 阈值 */
    @Excel(name = "阈值")
    private Long count;

    /** 间隔（秒） */
    @Excel(name = "间隔", readConverterExp = "秒=")
    private Long intervalSec;

    /** 流控方式 */
    @Excel(name = "流控方式")
    private Long controlBehavior;

    /** 突发请求数目 */
    @Excel(name = "突发请求数目")
    private Long burst;

    /** 最长排队时间 */
    @Excel(name = "最长排队时间")
    private Long maxQueueingTimeoutMs;

    public void setGatewayFlowRuleId(Long gatewayFlowRuleId) 
    {
        this.gatewayFlowRuleId = gatewayFlowRuleId;
    }

    public Long getGatewayFlowRuleId() 
    {
        return gatewayFlowRuleId;
    }
    public void setResource(String resource) 
    {
        this.resource = resource;
    }

    public String getResource() 
    {
        return resource;
    }
    public void setGrade(Long grade) 
    {
        this.grade = grade;
    }

    public Long getGrade() 
    {
        return grade;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setIntervalSec(Long intervalSec) 
    {
        this.intervalSec = intervalSec;
    }

    public Long getIntervalSec() 
    {
        return intervalSec;
    }
    public void setControlBehavior(Long controlBehavior) 
    {
        this.controlBehavior = controlBehavior;
    }

    public Long getControlBehavior() 
    {
        return controlBehavior;
    }
    public void setBurst(Long burst) 
    {
        this.burst = burst;
    }

    public Long getBurst() 
    {
        return burst;
    }
    public void setMaxQueueingTimeoutMs(Long maxQueueingTimeoutMs) 
    {
        this.maxQueueingTimeoutMs = maxQueueingTimeoutMs;
    }

    public Long getMaxQueueingTimeoutMs() 
    {
        return maxQueueingTimeoutMs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gatewayFlowRuleId", getGatewayFlowRuleId())
            .append("resource", getResource())
            .append("grade", getGrade())
            .append("count", getCount())
            .append("intervalSec", getIntervalSec())
            .append("controlBehavior", getControlBehavior())
            .append("burst", getBurst())
            .append("maxQueueingTimeoutMs", getMaxQueueingTimeoutMs())
            .toString();
    }
}
