package com.ruoyi.domain.service;

import com.ruoyi.domain.entity.GatewayFlowRule;

import java.util.List;

/**
 * 网关限流Service接口
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
public interface IGatewayFlowRuleService 
{
    /**
     * 查询网关限流
     * 
     * @param gatewayFlowRuleId 网关限流ID
     * @return 网关限流
     */
    public GatewayFlowRule selectGatewayFlowRuleById(Long gatewayFlowRuleId);

    /**
     * 查询网关限流列表
     * 
     * @param gatewayFlowRule 网关限流
     * @return 网关限流集合
     */
    public List<GatewayFlowRule> selectGatewayFlowRuleList(GatewayFlowRule gatewayFlowRule);

    /**
     * 新增网关限流
     * 
     * @param gatewayFlowRule 网关限流
     * @return 结果
     */
    public int insertGatewayFlowRule(GatewayFlowRule gatewayFlowRule);

    /**
     * 修改网关限流
     * 
     * @param gatewayFlowRule 网关限流
     * @return 结果
     */
    public int updateGatewayFlowRule(GatewayFlowRule gatewayFlowRule);

    /**
     * 批量删除网关限流
     * 
     * @param gatewayFlowRuleIds 需要删除的网关限流ID
     * @return 结果
     */
    public int deleteGatewayFlowRuleByIds(Long[] gatewayFlowRuleIds);

    /**
     * 删除网关限流信息
     * 
     * @param gatewayFlowRuleId 网关限流ID
     * @return 结果
     */
    public int deleteGatewayFlowRuleById(Long gatewayFlowRuleId);
}
