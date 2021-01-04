package com.ruoyi.domain.mapper;

import com.ruoyi.domain.entity.GatewayFlowRule;

import java.util.List;

/**
 * 网关限流Mapper接口
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
public interface GatewayFlowRuleMapper 
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
     * 删除网关限流
     * 
     * @param gatewayFlowRuleId 网关限流ID
     * @return 结果
     */
    public int deleteGatewayFlowRuleById(Long gatewayFlowRuleId);

    /**
     * 批量删除网关限流
     * 
     * @param gatewayFlowRuleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGatewayFlowRuleByIds(Long[] gatewayFlowRuleIds);
}
