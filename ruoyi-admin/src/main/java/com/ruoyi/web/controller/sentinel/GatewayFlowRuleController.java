package com.ruoyi.web.controller.sentinel;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.config.ApolloConfig;
import com.ruoyi.domain.entity.GatewayFlowRule;
import com.ruoyi.domain.service.IGatewayFlowRuleService;
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
 * 网关限流Controller
 *
 * @author tanhongxing
 * @date 2020-12-29
 */
@RestController
@RequestMapping("/gatewayflowrule/gatewayflowrule")
public class GatewayFlowRuleController extends BaseController {
    @Autowired
    private IGatewayFlowRuleService gatewayFlowRuleService;

    private final String key = "sentinel.gatewayFlowRules";

    private static final Gson gson = new Gson();

    /**
     * 查询网关限流列表
     */
    @PreAuthorize("@ss.hasPermi('gatewayflowrule:gatewayflowrule:list')")
    @GetMapping("/list")
    public TableDataInfo list(GatewayFlowRule gatewayFlowRule) {
        startPage();
        List<GatewayFlowRule> list = gatewayFlowRuleService.selectGatewayFlowRuleList(gatewayFlowRule);
        return getDataTable(list);
    }

    /**
     * 导出网关限流列表
     */
    @PreAuthorize("@ss.hasPermi('gatewayflowrule:gatewayflowrule:export')")
    @Log(title = "网关限流", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GatewayFlowRule gatewayFlowRule) {
        List<GatewayFlowRule> list = gatewayFlowRuleService.selectGatewayFlowRuleList(gatewayFlowRule);
        ExcelUtil<GatewayFlowRule> util = new ExcelUtil<GatewayFlowRule>(GatewayFlowRule.class);
        return util.exportExcel(list, "gatewayflowrule");
    }

    /**
     * 获取网关限流详细信息
     */
    @PreAuthorize("@ss.hasPermi('gatewayflowrule:gatewayflowrule:query')")
    @GetMapping(value = "/{gatewayFlowRuleId}")
    public AjaxResult getInfo(@PathVariable("gatewayFlowRuleId") Long gatewayFlowRuleId) {
        return AjaxResult.success(gatewayFlowRuleService.selectGatewayFlowRuleById(gatewayFlowRuleId));
    }

    /**
     * 新增网关限流
     */
    @PreAuthorize("@ss.hasPermi('gatewayflowrule:gatewayflowrule:add')")
    @Log(title = "网关限流", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GatewayFlowRule gatewayFlowRule) {

        Type ruleType = new TypeToken<List<GatewayFlowRule>>() {
        }.getType();

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder().withPortalUrl(ApolloConfig.getPortalUrl())
                .withToken(ApolloConfig.getToken()).build();

        OpenItemDTO openItemDTOOld = client.getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), key);

        String valueOld = openItemDTOOld.getValue();

        List<GatewayFlowRule> gatewayFlowRuleList = gson.fromJson(valueOld, ruleType);

        long existsCount = gatewayFlowRuleList.stream()
                .filter(r -> r.getResource().equals(gatewayFlowRule.getResource()))
                .count();

        if (existsCount > 0) {
            return AjaxResult.error("已存在该资源，插入失败！");
        }

        gatewayFlowRuleList.add(gatewayFlowRule);

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(key);
        openItemDTO.setValue(new Gson().toJson(gatewayFlowRuleList, ruleType));//JSONObject.toJSONString(gatewayFlowRuleoList);
        openItemDTO.setDataChangeLastModifiedBy("apollo");

        client.updateItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), openItemDTO);

        //发布
        NamespaceReleaseDTO releaseDTO = new NamespaceReleaseDTO();
        releaseDTO.setReleaseTitle("添加网关限流规则");
        releaseDTO.setReleasedBy("apollo");
        client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), releaseDTO);

        return toAjax(gatewayFlowRuleService.insertGatewayFlowRule(gatewayFlowRule));
    }

    /**
     * 修改网关限流
     */
    @PreAuthorize("@ss.hasPermi('gatewayflowrule:gatewayflowrule:edit')")
    @Log(title = "网关限流", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GatewayFlowRule gatewayFlowRule) {

        Type ruleType = new TypeToken<List<GatewayFlowRule>>() {
        }.getType();

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder().withPortalUrl(ApolloConfig.getPortalUrl())
                .withToken(ApolloConfig.getToken()).build();

        OpenItemDTO openItemDTOOld = client.getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), key);

        String valueOld = openItemDTOOld.getValue();

        List<GatewayFlowRule> gatewayFlowRuleList = gson.fromJson(valueOld, ruleType);

        GatewayFlowRule gatewayFlowRule_old = gatewayFlowRuleList.stream()
                .filter(r -> r.getResource().equals(gatewayFlowRule.getResource()))
                .findFirst().orElse(null);

        if (gatewayFlowRule_old == null) {
            return AjaxResult.error("不存在该资源，修改失败！");
        }

        //先删除Apollo上旧的该资源，
        int removeObjIndex = gatewayFlowRuleList.indexOf(gatewayFlowRule_old);
        gatewayFlowRuleList.remove(gatewayFlowRule_old);
        //再添加该资源的新值
        gatewayFlowRuleList.add(removeObjIndex,gatewayFlowRule);

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(key);
        openItemDTO.setValue(new Gson().toJson(gatewayFlowRuleList, ruleType));//JSONObject.toJSONString(gatewayFlowRuleoList);
        openItemDTO.setDataChangeLastModifiedBy("apollo");

        client.updateItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), openItemDTO);

        //发布
        NamespaceReleaseDTO releaseDTO = new NamespaceReleaseDTO();
        releaseDTO.setReleaseTitle("修改网关限流规则");
        releaseDTO.setReleasedBy("apollo");
        client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), releaseDTO);

        return toAjax(gatewayFlowRuleService.updateGatewayFlowRule(gatewayFlowRule));
    }

    /**
     * 删除网关限流
     */
    @PreAuthorize("@ss.hasPermi('gatewayflowrule:gatewayflowrule:remove')")
    @Log(title = "网关限流", businessType = BusinessType.DELETE)
    @DeleteMapping("/{gatewayFlowRuleIds}")
    public AjaxResult remove(@PathVariable Long[] gatewayFlowRuleIds) {

        Type ruleType = new TypeToken<List<GatewayFlowRule>>() {
        }.getType();

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder().withPortalUrl(ApolloConfig.getPortalUrl())
                .withToken(ApolloConfig.getToken()).build();

        OpenItemDTO openItemDTOOld = client.getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), key);

        String valueOld = openItemDTOOld.getValue();

        List<GatewayFlowRule> gatewayFlowRuleListFromApollo = gson.fromJson(valueOld, ruleType);

        for (Long id : gatewayFlowRuleIds
        ) {
            //按照id从数据库中查找该id对应的实体
            GatewayFlowRule gatewayFlowRule = gatewayFlowRuleService.selectGatewayFlowRuleById(id);

            GatewayFlowRule gatewayFlowRule_old = gatewayFlowRuleListFromApollo.stream()
                    .filter(r -> r.getResource().equals(gatewayFlowRule.getResource()))
                    .findFirst().orElse(null);

            if (gatewayFlowRule_old == null) {
                return AjaxResult.error("不存在该资源，删除失败！ 资源名称：" + gatewayFlowRule.getResource());
            }

            //先删除Apollo上旧的该资源，
            gatewayFlowRuleListFromApollo.remove(gatewayFlowRule_old);
        }

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(key);
        openItemDTO.setValue(new Gson().toJson(gatewayFlowRuleListFromApollo, ruleType));//JSONObject.toJSONString(gatewayFlowRuleoList);
        openItemDTO.setDataChangeLastModifiedBy("apollo");

        client.updateItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), openItemDTO);

        //发布
        NamespaceReleaseDTO releaseDTO = new NamespaceReleaseDTO();
        releaseDTO.setReleaseTitle("删除网关限流规则");
        releaseDTO.setReleasedBy("apollo");
        client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(),
                ApolloConfig.getNamespace(), releaseDTO);

        return toAjax(gatewayFlowRuleService.deleteGatewayFlowRuleByIds(gatewayFlowRuleIds));
    }
}
