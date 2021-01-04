package com.ruoyi.web.controller.gateway;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.domain.entity.RouteInfo;
import com.ruoyi.domain.entity.RoutePredicate;
import com.ruoyi.domain.service.IRouteInfoService;
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
 * 路由信息Controller
 * 
 * @author ruoyi
 * @date 2020-12-28
 */
@RestController
@RequestMapping("/gateway/routeInfo")
public class RouteInfoController extends BaseController
{
    @Autowired
    private IRouteInfoService routeInfoService;

    /**
     * 查询路由信息列表
     */
    @PreAuthorize("@ss.hasPermi('gateway:routeInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(RouteInfo routeInfo)
    {
        startPage();
        List<RouteInfo> list = routeInfoService.selectRouteInfoList(routeInfo);
        return getDataTable(list);
    }

    /**
     * 导出路由信息列表
     */
    @PreAuthorize("@ss.hasPermi('gateway:routeInfo:export')")
    @Log(title = "路由信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RouteInfo routeInfo)
    {
        List<RouteInfo> list = routeInfoService.selectRouteInfoList(routeInfo);
        ExcelUtil<RouteInfo> util = new ExcelUtil<RouteInfo>(RouteInfo.class);
        return util.exportExcel(list, "routeInfo");
    }

    /**
     * 获取路由信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('gateway:getPredicateType:query')")
    @GetMapping(value = "/getPredicateType")
    public AjaxResult getPredicateType()
    {
        List<RoutePredicate> list = new ArrayList<>();
        RoutePredicate routePredicate = new RoutePredicate();
        routePredicate.setPredicateType("query");
        routePredicate.setRouteInfoId(1L);
        list.add(routePredicate);
        return AjaxResult.success(list);
    }

    /**
     * 获取路由信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('gateway:routeInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(routeInfoService.selectRouteInfoById(id));
    }

    /**
     * 新增路由信息
     */
    @PreAuthorize("@ss.hasPermi('gateway:routeInfo:add')")
    @Log(title = "路由信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RouteInfo routeInfo)
    {
        return toAjax(routeInfoService.insertRouteInfo(routeInfo));
    }

    /**
     * 修改路由信息
     */
    @PreAuthorize("@ss.hasPermi('gateway:routeInfo:edit')")
    @Log(title = "路由信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RouteInfo routeInfo)
    {
        return toAjax(routeInfoService.updateRouteInfo(routeInfo));
    }

    /**
     * 删除路由信息
     */
    @PreAuthorize("@ss.hasPermi('gateway:routeInfo:remove')")
    @Log(title = "路由信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(routeInfoService.deleteRouteInfoByIds(ids));
    }
}
