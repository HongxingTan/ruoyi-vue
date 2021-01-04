package com.ruoyi.domain.service.impl;

import java.util.List;

import com.ruoyi.domain.entity.GatewayFlowRule;
import com.ruoyi.domain.mapper.GatewayFlowRuleMapper;
import com.ruoyi.domain.service.IGatewayFlowRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 网关限流Service业务层处理
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
@Service
public class GatewayFlowRuleServiceImpl implements IGatewayFlowRuleService
{
    @Autowired
    private GatewayFlowRuleMapper gatewayFlowRuleMapper;

    /**
     * 查询网关限流
     * 
     * @param gatewayFlowRuleId 网关限流ID
     * @return 网关限流
     */
    @Override
    public GatewayFlowRule selectGatewayFlowRuleById(Long gatewayFlowRuleId)
    {
        return gatewayFlowRuleMapper.selectGatewayFlowRuleById(gatewayFlowRuleId);
    }

    /**
     * 查询网关限流列表
     * 
     * @param gatewayFlowRule 网关限流
     * @return 网关限流
     */
    @Override
    public List<GatewayFlowRule> selectGatewayFlowRuleList(GatewayFlowRule gatewayFlowRule)
    {
        return gatewayFlowRuleMapper.selectGatewayFlowRuleList(gatewayFlowRule);
    }

    /**
     * 新增网关限流
     * 
     * @param gatewayFlowRule 网关限流
     * @return 结果
     */
    @Override
    public int insertGatewayFlowRule(GatewayFlowRule gatewayFlowRule)
    {
        return gatewayFlowRuleMapper.insertGatewayFlowRule(gatewayFlowRule);
    }

    /**
     * 修改网关限流
     * 
     * @param gatewayFlowRule 网关限流
     * @return 结果
     */
    @Override
    public int updateGatewayFlowRule(GatewayFlowRule gatewayFlowRule)
    {
        return gatewayFlowRuleMapper.updateGatewayFlowRule(gatewayFlowRule);
    }

    /**
     * 批量删除网关限流
     * 
     * @param gatewayFlowRuleIds 需要删除的网关限流ID
     * @return 结果
     */
    @Override
    public int deleteGatewayFlowRuleByIds(Long[] gatewayFlowRuleIds)
    {
        return gatewayFlowRuleMapper.deleteGatewayFlowRuleByIds(gatewayFlowRuleIds);
    }

    /**
     * 删除网关限流信息
     * 
     * @param gatewayFlowRuleId 网关限流ID
     * @return 结果
     */
    @Override
    public int deleteGatewayFlowRuleById(Long gatewayFlowRuleId)
    {
        return gatewayFlowRuleMapper.deleteGatewayFlowRuleById(gatewayFlowRuleId);
    }
}
