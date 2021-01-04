package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GwCacheConfig;

/**
 * 结果缓存配置Service接口
 * 
 * @author ruoyi
 * @date 2020-12-29
 */
public interface IGwCacheConfigService 
{
    /**
     * 查询结果缓存配置
     * 
     * @param id 结果缓存配置ID
     * @return 结果缓存配置
     */
    public GwCacheConfig selectGwCacheConfigById(Integer id);

    /**
     * 查询结果缓存配置列表
     * 
     * @param gwCacheConfig 结果缓存配置
     * @return 结果缓存配置集合
     */
    public List<GwCacheConfig> selectGwCacheConfigList(GwCacheConfig gwCacheConfig);

    /**
     * 新增结果缓存配置
     * 
     * @param gwCacheConfig 结果缓存配置
     * @return 结果
     */
    public int insertGwCacheConfig(GwCacheConfig gwCacheConfig);

    /**
     * 修改结果缓存配置
     * 
     * @param gwCacheConfig 结果缓存配置
     * @return 结果
     */
    public int updateGwCacheConfig(GwCacheConfig gwCacheConfig);

    /**
     * 批量删除结果缓存配置
     * 
     * @param ids 需要删除的结果缓存配置ID
     * @return 结果
     */
    public int deleteGwCacheConfigByIds(Integer[] ids);

    /**
     * 删除结果缓存配置信息
     * 
     * @param id 结果缓存配置ID
     * @return 结果
     */
    public int deleteGwCacheConfigById(Integer id);
}
