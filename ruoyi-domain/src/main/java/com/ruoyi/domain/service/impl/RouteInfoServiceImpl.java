package com.ruoyi.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.domain.entity.RouteFilter;
import com.ruoyi.domain.entity.RouteInfo;
import com.ruoyi.domain.entity.RoutePredicate;
import com.ruoyi.domain.mapper.RouteFilterMapper;
import com.ruoyi.domain.mapper.RouteInfoMapper;
import com.ruoyi.domain.mapper.RoutePredicateMapper;
import com.ruoyi.domain.service.IRouteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 路由信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-12-28
 */
@Service
@Transactional
public class RouteInfoServiceImpl implements IRouteInfoService
{
    @Autowired
    private RouteInfoMapper routeInfoMapper;
    @Autowired
    private RoutePredicateMapper routePredicateMapper;
    @Autowired
    private RouteFilterMapper routeFilterMapper;

    /**
     * 查询路由信息
     * 
     * @param id 路由信息ID
     * @return 路由信息
     */
    @Override
    public RouteInfo selectRouteInfoById(Long id)
    {
        RouteInfo routeInfo = routeInfoMapper.selectRouteInfoById(id);
        //添加断言
        List<RoutePredicate> routePredicates = routePredicateMapper.selectRoutePredicateByRouteInfoId(id);
        routeInfo.setRoutePredicates(CollectionUtils.isEmpty(routePredicates)?new ArrayList<>():routePredicates);
        //添加filter
        List<RouteFilter> routeFilters = routeFilterMapper.selectRouteFilterByRouteInfoId(id);
        routeInfo.setRouteFilters(CollectionUtils.isEmpty(routeFilters)?new ArrayList<>():routeFilters);
        return routeInfo;
    }

    /**
     * 查询路由信息列表
     * 
     * @param routeInfo 路由信息
     * @return 路由信息
     */
    @Override
    public List<RouteInfo> selectRouteInfoList(RouteInfo routeInfo)
    {
        List<RouteInfo> routeInfos = routeInfoMapper.selectRouteInfoList(routeInfo);
        return routeInfos;
    }

    @Override
    public int selectRouteInfoTotal()
    {
        return routeInfoMapper.selectRouteInfoTotal();
    }

    /**
     * 新增路由信息
     * 
     * @param routeInfo 路由信息
     * @return 结果
     */
    @Override
    public int insertRouteInfo(RouteInfo routeInfo)
    {

        //查询当前有多少条配置
        int total = selectRouteInfoTotal();

        routeInfo.setCreateTime(DateUtils.getNowDate());
        routeInfo.setUpdateTime(DateUtils.getNowDate());
        routeInfo.setSerialNumber(total);
        //新增基本信息配置
        int flag = routeInfoMapper.insertRouteInfo(routeInfo);

        if(flag > 0){

            if(!CollectionUtils.isEmpty(routeInfo.getRoutePredicates())){
                for(int i = 0; i< routeInfo.getRoutePredicates().size(); i++){
                    routeInfo.getRoutePredicates().get(i).setRouteInfoId(routeInfo.getId());
                    routeInfo.getRoutePredicates().get(i).setSerialNumber(i);
                }
                //新增断言信息配置
                routePredicateMapper.insertRoutePredicate(routeInfo.getRoutePredicates());
            }

            if(!CollectionUtils.isEmpty(routeInfo.getRouteFilters())){
                for(int i = 0; i< routeInfo.getRouteFilters().size(); i++){
                    routeInfo.getRouteFilters().get(i).setRouteInfoId(routeInfo.getId());
                    routeInfo.getRouteFilters().get(i).setSerialNumber(i);
                }
                //新增filter信息配置
                routeFilterMapper.insertRouteFilter(routeInfo.getRouteFilters());
            }

            //修改apollo配置
            ApolloOpenApiClient client = getApolloOpenApiClient();
            //根据前台参数生成ItemList
            List<OpenItemDTO> itemDTOS = getCreateOpenItemDTOs(routeInfo,total);

            for (OpenItemDTO itemDTO : itemDTOS){
                //创建item
                client.createItem(appId,env,clusterName,namespaceName,itemDTO);
            }

            //发布更新
            publishNamespace();
        }
        return flag;
    }

    /**
     * 根据前台参数生成ItemList
     * @param routeInfo
     * @param total
     * @return
     */
    private List<OpenItemDTO> getCreateOpenItemDTOs(RouteInfo routeInfo,int total){
        List<OpenItemDTO> itemDTOS = new ArrayList<>();
        OpenItemDTO idItem = new OpenItemDTO();
        idItem.setKey("spring.cloud.gateway.routes[" + total + "].id");
        idItem.setValue(routeInfo.getRouteId());
        idItem.setDataChangeCreatedBy(operator);
        idItem.setDataChangeLastModifiedBy(operator);
        itemDTOS.add(idItem);

        OpenItemDTO uriItem = new OpenItemDTO();
        uriItem.setKey("spring.cloud.gateway.routes[" + total + "].uri");
        uriItem.setValue(routeInfo.getUri());
        uriItem.setDataChangeCreatedBy(operator);
        uriItem.setDataChangeLastModifiedBy(operator);
        itemDTOS.add(uriItem);

        OpenItemDTO orderItem = new OpenItemDTO();
        orderItem.setKey("spring.cloud.gateway.routes[" + total + "].order");
        orderItem.setValue(routeInfo.getOrder() == null ? "0" : routeInfo.getOrder().toString());
        orderItem.setDataChangeCreatedBy(operator);
        orderItem.setDataChangeLastModifiedBy(operator);
        itemDTOS.add(orderItem);

        //断言
        if(!CollectionUtils.isEmpty(routeInfo.getRoutePredicates())){
            for(RoutePredicate routePredicate : routeInfo.getRoutePredicates()){
                OpenItemDTO predicateItem = new OpenItemDTO();
                predicateItem.setKey("spring.cloud.gateway.routes[" + total + "].predicates[" + routePredicate.getSerialNumber() + "]");
                predicateItem.setValue(routePredicate.getPredicateType() + "=" + routePredicate.getPredicateRule());
                predicateItem.setDataChangeCreatedBy(operator);
                itemDTOS.add(predicateItem);
            }
        }

        //过滤器
        if(!CollectionUtils.isEmpty(routeInfo.getRouteFilters())){
            for(RouteFilter routeFilter : routeInfo.getRouteFilters()){
                OpenItemDTO filterItem = new OpenItemDTO();
                filterItem.setKey("spring.cloud.gateway.routes[" + total + "].filter[" + routeFilter.getSerialNumber() + "]");
                filterItem.setValue(routeFilter.getFilterType() + "=" + routeFilter.getFilterRule());
                filterItem.setDataChangeCreatedBy(operator);
                itemDTOS.add(filterItem);
            }
        }

        return itemDTOS;
    }

    /**
     * 修改路由信息
     * 
     * @param routeInfo 路由信息
     * @return 结果
     */
    @Override
    public int updateRouteInfo(RouteInfo routeInfo)
    {

        //修改数据库配置
        routeInfo.setUpdateTime(DateUtils.getNowDate());
        routeInfoMapper.updateRouteInfo(routeInfo);
        //删除所有断言，从新添加断言
        deletePredicate(routeInfo);
        if(!CollectionUtils.isEmpty(routeInfo.getRoutePredicates())){
            for(int i = 0; i< routeInfo.getRoutePredicates().size(); i++){
                routeInfo.getRoutePredicates().get(i).setSerialNumber(i);
                routeInfo.getRoutePredicates().get(i).setRouteInfoId(routeInfo.getId());
            }
            //新增断言信息配置
            routePredicateMapper.insertRoutePredicate(routeInfo.getRoutePredicates());
        }
        //删除所有filter，从新添加filter
        deleteFilter(routeInfo);
        if(!CollectionUtils.isEmpty(routeInfo.getRouteFilters())){
            for(int i = 0; i< routeInfo.getRouteFilters().size(); i++){
                routeInfo.getRoutePredicates().get(i).setSerialNumber(i);
                routeInfo.getRoutePredicates().get(i).setRouteInfoId(routeInfo.getId());
            }
            //新增filter信息配置
            routeFilterMapper.insertRouteFilter(routeInfo.getRouteFilters());
        }
        //修改apollo配置
        ApolloOpenApiClient client = getApolloOpenApiClient();

        List<OpenItemDTO> itemDTOS = getCreateOpenItemDTOs(routeInfo,routeInfo.getSerialNumber());
        for (OpenItemDTO itemDTO : itemDTOS){

            if(StringUtils.isEmpty(itemDTO.getDataChangeLastModifiedBy())){
                //创建断言，filter
                client.createItem(appId,env,clusterName,namespaceName,itemDTO);
            }else{
                //修改路由
                client.updateItem(appId,env,clusterName,namespaceName,itemDTO);
            }

        }

        //发布更新
        publishNamespace();
        return 1;
    }

    /**
     * 删除断言
     * @param routeInfo
     */
    private void deletePredicate(RouteInfo routeInfo){
        ApolloOpenApiClient client = getApolloOpenApiClient();
        //查询当前所有断言
        List<RoutePredicate> routePredicates = routePredicateMapper.selectRoutePredicateByRouteInfoId(routeInfo.getId());
        routePredicateMapper.deleteForRouteInfoId(routeInfo.getId());
        //删除断言  apollo
        for(RoutePredicate routePredicate : routePredicates){
            client.removeItem(appId,env,clusterName,namespaceName,"spring.cloud.gateway.routes[" + routeInfo.getSerialNumber() + "].predicates[" + routePredicate.getSerialNumber() + "]",operator);
        }
    }

    /**
     * 删除filter
     * @param routeInfo
     */
    private void deleteFilter(RouteInfo routeInfo){
        ApolloOpenApiClient client = getApolloOpenApiClient();
        //查询当前所有filter
        List<RouteFilter> routeFilters = routeFilterMapper.selectRouteFilterByRouteInfoId(routeInfo.getId());
        routeFilterMapper.deleteForRouteInfoId(routeInfo.getId());
        //删除filter  apollo
        for(RouteFilter routeFilter : routeFilters){
            client.removeItem(appId,env,clusterName,namespaceName,"spring.cloud.gateway.routes[" + routeInfo.getSerialNumber() + "].filter[" + routeFilter.getSerialNumber() + "]",operator);
        }
    }

    /**
     * 批量删除路由信息
     * 
     * @param ids 需要删除的路由信息ID
     * @return 结果
     */
    @Override
    public int deleteRouteInfoByIds(Long[] ids)
    {
        for(Long id : ids){
            deleteRouteInfoById(id);
        }
        return 1;
    }

    /**
     * 删除路由信息信息
     * 
     * @param id 路由信息ID
     * @return 结果
     */
    @Override
    public int deleteRouteInfoById(Long id)
    {

        //删除路由信息  返回被删除的数组下标
        int serialNumber = deleteRoute(id,0);
        //换位置将下表最大的移动到删除的下标位置，因为上面删除  数组不连贯了

        //查询最大的下标路由信息
        RouteInfo routeInfo = routeInfoMapper.selectRouteInfoByMaxNum(id);
        //查询断言信息
        List<RoutePredicate> routePredicates = routePredicateMapper.selectRoutePredicateByRouteInfoId(routeInfo.getId());
        if(!CollectionUtils.isEmpty(routePredicates)){
            routeInfo.setRoutePredicates(routePredicates);
        }

        if(routeInfo != null){
            //将其删除
            deleteRoute(routeInfo.getId(),1);
            //从新 新增它，下标位置为开始删除的路由下标
            addRoute(routeInfo,serialNumber);
        }
        publishNamespace();

        return serialNumber;
    }

    private void addRoute(RouteInfo routeInfo,int serialNumber){
        //修改数据库路由顺序
        RouteInfo updRoute = new RouteInfo();
        updRoute.setSerialNumber(serialNumber);
        updRoute.setId(routeInfo.getId());
        routeInfoMapper.updateRouteInfo(updRoute);

        ApolloOpenApiClient client = getApolloOpenApiClient();
        //根据前台参数生成ItemList
        List<OpenItemDTO> itemDTOS = getCreateOpenItemDTOs(routeInfo,serialNumber);

        for (OpenItemDTO itemDTO : itemDTOS){
            //创建item
            client.createItem(appId,env,clusterName,namespaceName,itemDTO);
        }
    }

    private int deleteRoute(Long id,int status){
        ApolloOpenApiClient client = getApolloOpenApiClient();

        //查询路由信息
        RouteInfo routeInfo = routeInfoMapper.selectRouteInfoById(id);
        //查询断言信息
        List<RoutePredicate> routePredicates = routePredicateMapper.selectRoutePredicateByRouteInfoId(id);

        if(status == 0){
            //删除数据库路由信息
            routeInfoMapper.deleteRouteInfoById(id);
        }
        //删除路由  apollo
        client.removeItem(appId,env,clusterName,namespaceName,"spring.cloud.gateway.routes[" + routeInfo.getSerialNumber() + "].id",operator);
        client.removeItem(appId,env,clusterName,namespaceName,"spring.cloud.gateway.routes[" + routeInfo.getSerialNumber() + "].uri",operator);
        client.removeItem(appId,env,clusterName,namespaceName,"spring.cloud.gateway.routes[" + routeInfo.getSerialNumber() + "].order",operator);
        //删除断言  apollo
        for(RoutePredicate routePredicate : routePredicates){
            client.removeItem(appId,env,clusterName,namespaceName,"spring.cloud.gateway.routes[" + routeInfo.getSerialNumber() + "].predicates[" + routePredicate.getSerialNumber() + "]",operator);
        }

        return routeInfo.getSerialNumber();
    }

    String appId = "100003171";
    String env = "dev";
    String clusterName = "default";
    String namespaceName = "gateway";
    String operator = "apollo";
    private ApolloOpenApiClient getApolloOpenApiClient(){
        //修改apollo配置
        String portalUrl = "http://123.57.129.11:8070"; // portal url
        String token = "96b9458902ff35b68abf97d718410570ce2a29d8"; // 申请的token
        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
                .withPortalUrl(portalUrl)
                .withToken(token)
                .build();
        return client;
    }

    private void publishNamespace(){
        ApolloOpenApiClient client = getApolloOpenApiClient();
        //发布更新
        NamespaceReleaseDTO releaseDTO = new NamespaceReleaseDTO();
        releaseDTO.setReleaseTitle("更新路由规则");
        releaseDTO.setReleasedBy(operator);
        client.publishNamespace(appId,env,clusterName,namespaceName,releaseDTO);
    }
}
