package com.ruoyi.domain.service.impl;

import java.util.List;

import com.ruoyi.domain.entity.DegradeRule;
import com.ruoyi.domain.mapper.DegradeRuleMapper;
import com.ruoyi.domain.service.IDegradeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 熔断降级Service业务层处理
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
@Service
public class DegradeRuleServiceImpl implements IDegradeRuleService
{
    @Autowired
    private DegradeRuleMapper degradeRuleMapper;

    /**
     * 查询熔断降级
     * 
     * @param degradeRuleId 熔断降级ID
     * @return 熔断降级
     */
    @Override
    public DegradeRule selectDegradeRuleById(Long degradeRuleId)
    {
        return degradeRuleMapper.selectDegradeRuleById(degradeRuleId);
    }

    /**
     * 查询熔断降级列表
     * 
     * @param degradeRule 熔断降级
     * @return 熔断降级
     */
    @Override
    public List<DegradeRule> selectDegradeRuleList(DegradeRule degradeRule)
    {
        return degradeRuleMapper.selectDegradeRuleList(degradeRule);
    }

    /**
     * 新增熔断降级
     * 
     * @param degradeRule 熔断降级
     * @return 结果
     */
    @Override
    public int insertDegradeRule(DegradeRule degradeRule)
    {
        return degradeRuleMapper.insertDegradeRule(degradeRule);
    }

    /**
     * 修改熔断降级
     * 
     * @param degradeRule 熔断降级
     * @return 结果
     */
    @Override
    public int updateDegradeRule(DegradeRule degradeRule)
    {
        return degradeRuleMapper.updateDegradeRule(degradeRule);
    }

    /**
     * 批量删除熔断降级
     * 
     * @param degradeRuleIds 需要删除的熔断降级ID
     * @return 结果
     */
    @Override
    public int deleteDegradeRuleByIds(Long[] degradeRuleIds)
    {
        return degradeRuleMapper.deleteDegradeRuleByIds(degradeRuleIds);
    }

    /**
     * 删除熔断降级信息
     * 
     * @param degradeRuleId 熔断降级ID
     * @return 结果
     */
    @Override
    public int deleteDegradeRuleById(Long degradeRuleId)
    {
        return degradeRuleMapper.deleteDegradeRuleById(degradeRuleId);
    }
}
