package com.ruoyi.web.controller.business;

import java.util.List;

import com.ruoyi.domain.dto.AuthFilterDTO;
import com.ruoyi.domain.entity.AuthFilter;
import com.ruoyi.domain.service.IAuthFilterService;
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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 权限拦截器Controller
 *
 * @author guokui
 * @date 2020-12-28
 */
@RestController
@RequestMapping("/gateway/authfilter")
public class AuthFilterController extends BaseController
{
    @Autowired
    private IAuthFilterService authFilterService;

    /**
     * 查询权限拦截器列表
     */
    @PreAuthorize("@ss.hasPermi('gateway:authfilter:list')")
    @GetMapping("/list")
    public TableDataInfo list(AuthFilter authFilter)
    {
        startPage();
        List<AuthFilter> list = authFilterService.selectAuthFilterList(authFilter);
        return getDataTable(list);
    }

    /**
     * 导出权限拦截器列表
     */
    @PreAuthorize("@ss.hasPermi('gateway:authfilter:export')")
    @Log(title = "权限拦截器", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AuthFilter authFilter)
    {
        List<AuthFilter> list = authFilterService.selectAuthFilterList(authFilter);
        ExcelUtil<AuthFilter> util = new ExcelUtil<AuthFilter>(AuthFilter.class);
        return util.exportExcel(list, "authfilter");
    }

    /**
     * 获取权限拦截器详细信息
     */
    @PreAuthorize("@ss.hasPermi('gateway:authfilter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(authFilterService.selectAuthFilterById(id));
    }
    @GetMapping(value = "/getAuthData/{id}")
    public AjaxResult getAuthData(@PathVariable("id") Long id)
    {
        return AjaxResult.success(authFilterService.getAuthData(id));
    }


    /**
     * 新增权限拦截器
     */
    @PreAuthorize("@ss.hasPermi('gateway:authfilter:add')")
    @Log(title = "权限拦截器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AuthFilterDTO authFilter)
    {
        return toAjax(authFilterService.insertAuthFilter(authFilter));
    }

    /**
     * 修改权限拦截器
     */
    @PreAuthorize("@ss.hasPermi('gateway:authfilter:edit')")
    @Log(title = "权限拦截器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AuthFilterDTO authFilter)
    {
        return toAjax(authFilterService.updateAuthFilter(authFilter));
    }

    /**
     * 删除权限拦截器
     */
    @PreAuthorize("@ss.hasPermi('gateway:authfilter:remove')")
    @Log(title = "权限拦截器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(authFilterService.deleteAuthFilterByIds(ids));
    }
}
