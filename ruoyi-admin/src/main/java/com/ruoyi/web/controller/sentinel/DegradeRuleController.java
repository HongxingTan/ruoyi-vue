package com.ruoyi.web.controller.sentinel;

import java.lang.reflect.Type;
import java.util.List;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.config.ApolloConfig;
import com.ruoyi.domain.entity.DegradeRule;
import com.ruoyi.domain.entity.DegradeRule;
import com.ruoyi.domain.service.IDegradeRuleService;
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
 * 熔断降级Controller
 *
 * @author tanhongxing
 * @date 2020-12-29
 */
@RestController
@RequestMapping("/degraderule/degraderule")
public class DegradeRuleController extends BaseController {
    @Autowired
    private IDegradeRuleService degradeRuleService;

    private final String key = "sentinel.degradeRules";

    private static final Gson gson = new Gson();

    /**
     * 查询熔断降级列表
     */
    @PreAuthorize("@ss.hasPermi('degraderule:degraderule:list')")
    @GetMapping("/list")
    public TableDataInfo list(DegradeRule degradeRule) {
        startPage();
        List<DegradeRule> list = degradeRuleService.selectDegradeRuleList(degradeRule);
        return getDataTable(list);
    }

    /**
     * 导出熔断降级列表
     */
    @PreAuthorize("@ss.hasPermi('degraderule:degraderule:export')")
    @Log(title = "熔断降级", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DegradeRule degradeRule) {
        List<DegradeRule> list = degradeRuleService.selectDegradeRuleList(degradeRule);
        ExcelUtil<DegradeRule> util = new ExcelUtil<DegradeRule>(DegradeRule.class);
        return util.exportExcel(list, "degraderule");
    }

    /**
     * 获取熔断降级详细信息
     */
    @PreAuthorize("@ss.hasPermi('degraderule:degraderule:query')")
    @GetMapping(value = "/{degradeRuleId}")
    public AjaxResult getInfo(@PathVariable("degradeRuleId") Long degradeRuleId) {
        return AjaxResult.success(degradeRuleService.selectDegradeRuleById(degradeRuleId));
    }

    /**
     * 新增熔断降级
     */
    @PreAuthorize("@ss.hasPermi('degraderule:degraderule:add')")
    @Log(title = "熔断降级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DegradeRule degradeRule) {
        Type ruleType = new TypeToken<List<DegradeRule>>() {
        }.getType();

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder().withPortalUrl(ApolloConfig.getPortalUrl())
                .withToken(ApolloConfig.getToken()).build();

        OpenItemDTO openItemDTOOld = client.getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), key);

        String valueOld = openItemDTOOld.getValue();

        List<DegradeRule> degradeRuleList = gson.fromJson(valueOld, ruleType);

        long existsCount = degradeRuleList.stream()
                .filter(r -> r.getResource().equals(degradeRule.getResource()))
                .count();

        if (existsCount > 0) {
            return AjaxResult.error("已存在该资源，插入失败！");
        }

        degradeRuleList.add(degradeRule);

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(key);
        openItemDTO.setValue(new Gson().toJson(degradeRuleList, ruleType));//JSONObject.toJSONString(degradeRuleoList);
        openItemDTO.setDataChangeLastModifiedBy("apollo");

        client.updateItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), openItemDTO);

        //发布
        NamespaceReleaseDTO releaseDTO = new NamespaceReleaseDTO();
        releaseDTO.setReleaseTitle("添加熔断降级规则");
        releaseDTO.setReleasedBy("apollo");
        client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), releaseDTO);

        return toAjax(degradeRuleService.insertDegradeRule(degradeRule));
    }

    /**
     * 修改熔断降级
     */
    @PreAuthorize("@ss.hasPermi('degraderule:degraderule:edit')")
    @Log(title = "熔断降级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DegradeRule degradeRule) {
        Type ruleType = new TypeToken<List<DegradeRule>>() {
        }.getType();

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder().withPortalUrl(ApolloConfig.getPortalUrl())
                .withToken(ApolloConfig.getToken()).build();

        OpenItemDTO openItemDTOOld = client.getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), key);

        String valueOld = openItemDTOOld.getValue();

        List<DegradeRule> degradeRuleList = gson.fromJson(valueOld, ruleType);

        DegradeRule degradeRule_old = degradeRuleList.stream()
                .filter(r -> r.getResource().equals(degradeRule.getResource()))
                .findFirst().orElse(null);

        if (degradeRule_old == null) {
            return AjaxResult.error("不存在该资源，修改失败！");
        }

        //先删除Apollo上旧的该资源，
        int removeObjIndex = degradeRuleList.indexOf(degradeRule_old);
        degradeRuleList.remove(degradeRule_old);

        //再添加该资源的新值
        degradeRuleList.add(removeObjIndex, degradeRule);

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(key);
        openItemDTO.setValue(new Gson().toJson(degradeRuleList, ruleType));//JSONObject.toJSONString(degradeRuleoList);
        openItemDTO.setDataChangeLastModifiedBy("apollo");

        client.updateItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), openItemDTO);

        //发布
        NamespaceReleaseDTO releaseDTO = new NamespaceReleaseDTO();
        releaseDTO.setReleaseTitle("修改熔断降级规则");
        releaseDTO.setReleasedBy("apollo");
        client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), releaseDTO);

        return toAjax(degradeRuleService.updateDegradeRule(degradeRule));
    }

    /**
     * 删除熔断降级
     */
    @PreAuthorize("@ss.hasPermi('degraderule:degraderule:remove')")
    @Log(title = "熔断降级", businessType = BusinessType.DELETE)
    @DeleteMapping("/{degradeRuleIds}")
    public AjaxResult remove(@PathVariable Long[] degradeRuleIds) {
        Type ruleType = new TypeToken<List<DegradeRule>>() {
        }.getType();

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder().withPortalUrl(ApolloConfig.getPortalUrl())
                .withToken(ApolloConfig.getToken()).build();

        OpenItemDTO openItemDTOOld = client.getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), key);

        String valueOld = openItemDTOOld.getValue();

        List<DegradeRule> degradeRuleListFromApollo = gson.fromJson(valueOld, ruleType);

        for (Long id : degradeRuleIds
        ) {
            //按照id从数据库中查找该id对应的实体
            DegradeRule degradeRule = degradeRuleService.selectDegradeRuleById(id);

            DegradeRule degradeRule_old = degradeRuleListFromApollo.stream()
                    .filter(r -> r.getResource().equals(degradeRule.getResource()))
                    .findFirst().orElse(null);

            if (degradeRule_old == null) {
                return AjaxResult.error("不存在该资源，删除失败！ 资源名称：" + degradeRule.getResource());
            }

            //先删除Apollo上旧的该资源，
            degradeRuleListFromApollo.remove(degradeRule_old);
        }

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(key);
        openItemDTO.setValue(new Gson().toJson(degradeRuleListFromApollo, ruleType));//JSONObject.toJSONString(degradeRuleoList);
        openItemDTO.setDataChangeLastModifiedBy("apollo");

        client.updateItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), openItemDTO);

        //发布
        NamespaceReleaseDTO releaseDTO = new NamespaceReleaseDTO();
        releaseDTO.setReleaseTitle("删除熔断降级规则");
        releaseDTO.setReleasedBy("apollo");
        client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), releaseDTO);

        return toAjax(degradeRuleService.deleteDegradeRuleByIds(degradeRuleIds));
    }
}
