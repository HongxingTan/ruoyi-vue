package com.ruoyi.domain.mapper;

import com.ruoyi.domain.entity.DegradeRule;

import java.util.List;

/**
 * 熔断降级Mapper接口
 * 
 * @author tanhongxing
 * @date 2020-12-29
 */
public interface DegradeRuleMapper 
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
     * 删除熔断降级
     * 
     * @param degradeRuleId 熔断降级ID
     * @return 结果
     */
    public int deleteDegradeRuleById(Long degradeRuleId);

    /**
     * 批量删除熔断降级
     * 
     * @param degradeRuleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDegradeRuleByIds(Long[] degradeRuleIds);
}
