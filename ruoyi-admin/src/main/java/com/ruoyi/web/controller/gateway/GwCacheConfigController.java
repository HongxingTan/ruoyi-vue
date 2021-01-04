package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GwCacheConfig;
import com.ruoyi.system.service.IGwCacheConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 结果缓存配置Controller
 * 
 * @author ruoyi
 * @date 2020-12-29
 */
@RestController
@RequestMapping("/gateway/cacheFilter")
public class GwCacheConfigController extends BaseController
{
    @Autowired
    private IGwCacheConfigService gwCacheConfigService;

    /**
     * 查询结果缓存配置列表
     */
    @PreAuthorize("@ss.hasPermi('gateway:cacheFilter:list')")
    @GetMapping("/list")
    public TableDataInfo list(GwCacheConfig gwCacheConfig)
    {
        startPage();
        List<GwCacheConfig> list = gwCacheConfigService.selectGwCacheConfigList(gwCacheConfig);
        return getDataTable(list);
    }

    /**
     * 导出结果缓存配置列表
     */
    @PreAuthorize("@ss.hasPermi('gateway:cacheFilter:export')")
    @Log(title = "结果缓存配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GwCacheConfig gwCacheConfig)
    {
        List<GwCacheConfig> list = gwCacheConfigService.selectGwCacheConfigList(gwCacheConfig);
        ExcelUtil<GwCacheConfig> util = new ExcelUtil<GwCacheConfig>(GwCacheConfig.class);
        return util.exportExcel(list, "cacheFilter");
    }

    /**
     * 获取结果缓存配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('gateway:cacheFilter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(gwCacheConfigService.selectGwCacheConfigById(id));
    }

    /**
     * 新增结果缓存配置
     */
    @PreAuthorize("@ss.hasPermi('gateway:cacheFilter:add')")
    @Log(title = "结果缓存配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GwCacheConfig gwCacheConfig)
    {
        return toAjax(gwCacheConfigService.insertGwCacheConfig(gwCacheConfig));
    }

    /**
     * 修改结果缓存配置
     */
    @PreAuthorize("@ss.hasPermi('gateway:cacheFilter:edit')")
    @Log(title = "结果缓存配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GwCacheConfig gwCacheConfig)
    {
        return toAjax(gwCacheConfigService.updateGwCacheConfig(gwCacheConfig));
    }

    /**
     * 删除结果缓存配置
     */
    @PreAuthorize("@ss.hasPermi('gateway:cacheFilter:remove')")
    @Log(title = "结果缓存配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(gwCacheConfigService.deleteGwCacheConfigByIds(ids));
    }

//    @PreAuthorize("@ss.hasPermi('gateway:cacheFilter:remove')")
//    @Log(title = "结果缓存配置", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{id}")
//    public AjaxResult remove(@PathVariable Integer id)
//    {
//        return toAjax(gwCacheConfigService.deleteGwCacheConfigById(id));
//    }
}
