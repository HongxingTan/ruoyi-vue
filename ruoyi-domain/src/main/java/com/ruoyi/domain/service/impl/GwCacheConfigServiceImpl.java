package com.ruoyi.system.service.impl;

import java.util.List;

import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.google.gson.Gson;
import com.ruoyi.common.utils.apollo.ApolloUtils;
import com.ruoyi.domain.dto.GwCacheConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GwCacheConfigMapper;
import com.ruoyi.system.domain.GwCacheConfig;
import com.ruoyi.system.service.IGwCacheConfigService;

/**
 * 结果缓存配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-12-29
 */
@Service
public class GwCacheConfigServiceImpl implements IGwCacheConfigService 
{
    @Autowired
    private GwCacheConfigMapper gwCacheConfigMapper;

    /**
     * 查询结果缓存配置
     * 
     * @param id 结果缓存配置ID
     * @return 结果缓存配置
     */
    @Override
    public GwCacheConfig selectGwCacheConfigById(Integer id)
    {
        return gwCacheConfigMapper.selectGwCacheConfigById(id);
    }

    /**
     * 查询结果缓存配置列表
     * 
     * @param gwCacheConfig 结果缓存配置
     * @return 结果缓存配置
     */
    @Override
    public List<GwCacheConfig> selectGwCacheConfigList(GwCacheConfig gwCacheConfig)
    {
        return gwCacheConfigMapper.selectGwCacheConfigList(gwCacheConfig);
    }

    /**
     * 新增结果缓存配置
     * 
     * @param gwCacheConfig 结果缓存配置
     * @return 结果
     */
    @Override
    public int insertGwCacheConfig(GwCacheConfig gwCacheConfig)
    {
        GwCacheConfig cacheConfigResult = gwCacheConfigMapper.selectGwCacheConfigByURI(gwCacheConfig);
        if(cacheConfigResult == null) {
            int result = gwCacheConfigMapper.insertGwCacheConfig(gwCacheConfig);
            updateApolloConfig();
            return result;
        }
        return 0;
    }

    /**
     * 修改结果缓存配置
     * 
     * @param gwCacheConfig 结果缓存配置
     * @return 结果
     */
    @Override
    public int updateGwCacheConfig(GwCacheConfig gwCacheConfig)
    {
        int result = gwCacheConfigMapper.updateGwCacheConfig(gwCacheConfig);
        updateApolloConfig();
        return result;
    }

    /**
     * 批量删除结果缓存配置
     * 
     * @param ids 需要删除的结果缓存配置ID
     * @return 结果
     */
    @Override
    public int deleteGwCacheConfigByIds(Integer[] ids)
    {
        int result = gwCacheConfigMapper.deleteGwCacheConfigByIds(ids);
        updateApolloConfig();
        return result;
    }

    /**
     * 删除结果缓存配置信息
     * 
     * @param id 结果缓存配置ID
     * @return 结果
     */
    @Override
    public int deleteGwCacheConfigById(Integer id)
    {
        int result = gwCacheConfigMapper.deleteGwCacheConfigById(id);
        updateApolloConfig();
        return result;
    }

    private void updateApolloConfig() {
        List<GwCacheConfigDto> gwCacheConfigList = gwCacheConfigMapper.selectApolloList();
        if (gwCacheConfigList != null && gwCacheConfigList.size() > 0) {
            OpenItemDTO openItemDTO = new OpenItemDTO();
            openItemDTO.setKey("catchFilter");
            openItemDTO.setValue(new Gson().toJson(gwCacheConfigList));
            openItemDTO.setDataChangeCreatedBy("apollo");
            ApolloUtils.createOrUpdateItem(openItemDTO);
        }
    }
}
