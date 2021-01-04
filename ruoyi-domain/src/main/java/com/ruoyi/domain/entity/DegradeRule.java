package com.ruoyi.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 熔断降级对象 degrade_rule
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
public class DegradeRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long degradeRuleId;

    /** 资源名 */
    @Excel(name = "资源名")
    private String resource;

    /** 熔断策略 */
    @Excel(name = "熔断策略")
    private Long grade;

    /** 阈值/慢调用临界响应时间 */
    @Excel(name = "阈值/慢调用临界响应时间")
    private Double count;

    /** 熔断时长 */
    @Excel(name = "熔断时长")
    private Long timeWindow;

    /** 最小请求数 */
    @Excel(name = "最小请求数")
    private Long minRequestAmount;

    /** 统计时长 */
    @Excel(name = "统计时长")
    private Long statIntervalMs;

    /** 慢调用比例阈值 */
    @Excel(name = "慢调用比例阈值")
    private Double slowRatioThreshold;

    public void setDegradeRuleId(Long degradeRuleId) 
    {
        this.degradeRuleId = degradeRuleId;
    }

    public Long getDegradeRuleId() 
    {
        return degradeRuleId;
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
    public void setCount(Double  count)
    {
        this.count = count;
    }

    public Double getCount()
    {
        return count;
    }
    public void setTimeWindow(Long timeWindow) 
    {
        this.timeWindow = timeWindow;
    }

    public Long getTimeWindow() 
    {
        return timeWindow;
    }
    public void setMinRequestAmount(Long minRequestAmount) 
    {
        this.minRequestAmount = minRequestAmount;
    }

    public Long getMinRequestAmount() 
    {
        return minRequestAmount;
    }
    public void setSlowRatioThreshold(Double slowRatioThreshold)
    {
        this.slowRatioThreshold = slowRatioThreshold;
    }

    public Double getSlowRatioThreshold()
    {
        return slowRatioThreshold;
    }
    public void setStatIntervalMs(Long statIntervalMs)
    {
        this.statIntervalMs = statIntervalMs;
    }

    public Long getStatIntervalMs()
    {
        return statIntervalMs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("degradeRuleId", getDegradeRuleId())
            .append("resource", getResource())
            .append("grade", getGrade())
            .append("count", getCount())
            .append("timeWindow", getTimeWindow())
            .append("minRequestAmount", getMinRequestAmount())
            .append("slowRatioThreshold", getSlowRatioThreshold())
            .append("statIntervalMs", getStatIntervalMs())
            .toString();
    }
}
