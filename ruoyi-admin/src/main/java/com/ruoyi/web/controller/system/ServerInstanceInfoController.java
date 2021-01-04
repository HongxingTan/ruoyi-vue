package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.domain.entity.ServerInstanceInfo;
import com.ruoyi.domain.service.IServerInstanceInfoService;
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
 * 服务实例信息Controller
 *
 * @author ruoyi
 * @date 2020-12-25
 */
@RestController
@RequestMapping("/system/info")
public class ServerInstanceInfoController extends BaseController
{
    @Autowired
    private IServerInstanceInfoService serverInstanceInfoService;

    /**
     * 查询服务实例信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServerInstanceInfo serverInstanceInfo)
    {
        startPage();
        List<ServerInstanceInfo> list = serverInstanceInfoService.selectServerInstanceInfoList(serverInstanceInfo);
        return getDataTable(list);
    }

    /**
     * 导出服务实例信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "服务实例信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ServerInstanceInfo serverInstanceInfo)
    {
        List<ServerInstanceInfo> list = serverInstanceInfoService.selectServerInstanceInfoList(serverInstanceInfo);
        ExcelUtil<ServerInstanceInfo> util = new ExcelUtil<ServerInstanceInfo>(ServerInstanceInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取服务实例信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(serverInstanceInfoService.selectServerInstanceInfoById(id));
    }

    /**
     * 新增服务实例信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "服务实例信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServerInstanceInfo serverInstanceInfo)
    {
        return toAjax(serverInstanceInfoService.insertServerInstanceInfo(serverInstanceInfo));
    }

    /**
     * 修改服务实例信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "服务实例信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServerInstanceInfo serverInstanceInfo)
    {
        return toAjax(serverInstanceInfoService.updateServerInstanceInfo(serverInstanceInfo));
    }

    /**
     * 删除服务实例信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "服务实例信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serverInstanceInfoService.deleteServerInstanceInfoByIds(ids));
    }

    @Log(title = "服务实例信息", businessType = BusinessType.UPDATE)
	@GetMapping("/stopInfo/{id}")
    public AjaxResult stopInfo(@PathVariable Long id)
    {
        return toAjax(serverInstanceInfoService.stopInfo(id));
    }
}
