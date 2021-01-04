package com.ruoyi.domain.service;

import com.ruoyi.domain.entity.DegradeRule;

import java.util.List;

/**
 * 熔断降级Service接口
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
public interface IDegradeRuleService 
{
    /**
     * 查询熔断降级
     * 
     * @param degradeRuleId 熔断降级ID
     * @return 熔断降级
     */
    public DegradeRule selectDegradeRuleById(Long degradeRuleId);

    /**
     * 查询熔断降级列表
     * 
     * @param degradeRule 熔断降级
     * @return 熔断降级集合
     */
    public List<DegradeRule> selectDegradeRuleList(DegradeRule degradeRule);

    /**
     * 新增熔断降级
     * 
     * @param degradeRule 熔断降级
     * @return 结果
     */
    public int insertDegradeRule(DegradeRule degradeRule);

    /**
     * 修改熔断降级
     * 
     * @param degradeRule 熔断降级
     * @return 结果
     */
    public int updateDegradeRule(DegradeRule degradeRule);

    /**
     * 批量删除熔断降级
     * 
     * @param degradeRuleIds 需要删除的熔断降级ID
     * @return 结果
     */
    public int deleteDegradeRuleByIds(Long[] degradeRuleIds);

    /**
     * 删除熔断降级信息
     * 
     * @param degradeRuleId 熔断降级ID
     * @return 结果
     */
    public int deleteDegradeRuleById(Long degradeRuleId);
}
