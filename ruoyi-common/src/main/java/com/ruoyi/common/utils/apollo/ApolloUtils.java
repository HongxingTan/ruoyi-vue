package com.ruoyi.common.utils.apollo;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.*;
import com.ruoyi.common.config.ApolloConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApolloUtils {

    private static ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
            .withPortalUrl(ApolloConfig.getPortalUrl())
            .withToken(ApolloConfig.getToken())
            .build();

    /**
     * 获取最后一次发布的配置信息
     * @return OpenReleaseDTO
     */
    public static OpenReleaseDTO getLatestActiveRelease(String appId, String env, String clusterName, String namespace) {
        return client.getLatestActiveRelease(appId, env, clusterName, namespace);
    }

    /**
     * 获取最后一次发布的配置信息
     *
     * @param namespace     指定命名空间
     * @return              OpenReleaseDTO
     */
    public static OpenReleaseDTO getLatestActiveRelease(String namespace) {
        return getLatestActiveRelease(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(), namespace);
    }

    /**
     * 获取最后一次发布的配置信息
     *
     * @param env           指定环境
     * @param namespace     指定命名空间
     * @return              OpenReleaseDTO
     */
    public static OpenReleaseDTO getLatestActiveRelease(String env,String clusterName,String namespace) {
        return getLatestActiveRelease(ApolloConfig.getAppid(),env, clusterName, namespace);
    }

    /**
     * 发布名命空间
     *
     * @param namespace             名命空间
     * @param namespaceReleaseDTO   namespaceReleaseDTO
     * @return                      OpenReleaseDTO
     */
    public static OpenReleaseDTO publishNamespace(String appId,String env,String clusterName,String namespace, NamespaceReleaseDTO namespaceReleaseDTO){
        return client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), clusterName, namespace, namespaceReleaseDTO);
    }
    /**
     * 发布名命空间
     *
     * @param namespace             名命空间
     * @param namespaceReleaseDTO   namespaceReleaseDTO
     * @return                      OpenReleaseDTO
     */
    public static OpenReleaseDTO publishNamespace(String clusterName,String namespace, NamespaceReleaseDTO namespaceReleaseDTO){
        return client.publishNamespace(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), clusterName, namespace, namespaceReleaseDTO);
    }

    /**
     * 发布名命空间
     *
     * @param namespace             名命空间
     * @param namespaceReleaseDTO   namespaceReleaseDTO
     * @return                      OpenReleaseDTO
     */
    public static OpenReleaseDTO publishNamespace(String env,String clusterName,String namespace, NamespaceReleaseDTO namespaceReleaseDTO){
        return client.publishNamespace(ApolloConfig.getAppid(), env, clusterName, namespace, namespaceReleaseDTO);
    }

    /**
     * 创建或者修改某一行配置信息,存在则修改,不存在就创建
     *
     * @param openItemDTO openItemDTO
     */
    public static void createOrUpdateItem(String appIds, String env, String clusterName, String namespace, OpenItemDTO openItemDTO){
        client.createOrUpdateItem(appIds,env, clusterName, namespace,openItemDTO);
    }

    /**
     * 创建或者修改某一行配置信息,存在则修改,不存在就创建
     *
     * @param openItemDTO   openItemDTO
     */
    public static void createOrUpdateItem(OpenItemDTO openItemDTO){
        createOrUpdateItem(ApolloConfig.getAppid(),ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(), ApolloConfig.getNamespace(),openItemDTO);
    }

    /**
     * 创建或者修改某一行配置信息,存在则修改,不存在就创建
     *
     * @param namespace     指定namespace
     * @param openItemDTO   openItemDTO
     */
    public static void createOrUpdateItem(String namespace ,OpenItemDTO openItemDTO){
        createOrUpdateItem(ApolloConfig.getAppid(),ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(), namespace,openItemDTO);
    }

    /**
     * 创建或者修改某一行配置信息,存在则修改,不存在就创建
     *
     * @param namespace     指定namespace
     * @param openItemDTO   openItemDTO
     */
    public static void createOrUpdateItem(String env,String clusterName,String namespace ,OpenItemDTO openItemDTO){
        createOrUpdateItem(ApolloConfig.getAppid(),env, clusterName, namespace,openItemDTO);
    }
    /**
     * 创建或者修改某一行配置信息,存在则修改,不存在就创建
     *
     * @param clusterName   指定集群名称
     * @param namespace     指定名命空间
     * @param openItemDTO   openItemDTO
     */
    public static void createOrUpdateItem(String clusterName , String namespace ,OpenItemDTO openItemDTO){
        createOrUpdateItem(ApolloConfig.getAppid(),ApolloConfig.getEnvirment(), clusterName, namespace,openItemDTO);
    }

    /**
     * 创建appNamespace
     *
     * @param appNamespaceDTO   appNamespaceDTO
     * @return                  OpenAppNamespaceDTO
     */
    public static OpenAppNamespaceDTO createAppNamespace(OpenAppNamespaceDTO appNamespaceDTO){
        return client.createAppNamespace(appNamespaceDTO);
    }

    /**
     * 获取指定集群信息
     *
     * @param env           指定环境
     * @param clusterName   指定集群名称
     * @return              OpenClusterDTO
     */
    public static OpenClusterDTO getCluster(String env, String clusterName){
        return client.getCluster(ApolloConfig.getAppid(), env,clusterName);
    }

    /**
     * 获取所有app信息
     *
     * @return List<OpenAppDTO>
     */
    public static List<OpenAppDTO> getAllApps(){
        return client.getAllApps();
    }

    /**
     * 新增一条配置信息
     *
     * @param itemDTO   itemDTO
     * @return OpenItemDTO
     */
    public static OpenItemDTO createItem(String appIds, String env, String clusterName, String namespace, OpenItemDTO itemDTO) {
        return client.createItem(appIds, env, clusterName, namespace, itemDTO);
    }

    /**
     * 新增一条配置信息
     *
     * @param namespace     指定名命空间
     * @param itemDTO       itemDTO
     * @return              OpenItemDTO
     */
    public static OpenItemDTO createItem(String namespace,OpenItemDTO itemDTO){
        return createItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(), namespace, itemDTO);
    }

    /**
     * 新增一条配置信息
     *
     * @param namespace     指定名命空间
     * @param itemDTO       itemDTO
     * @return              OpenItemDTO
     */
    public static OpenItemDTO createItem(String env,String clusterName,String namespace,OpenItemDTO itemDTO){
        return createItem(ApolloConfig.getAppid(), env, clusterName, namespace, itemDTO);
    }



    /**
     * 创建集群
     *
     * @param clusterDTO  clusterDTO
     * @return            OpenClusterDTO
     */
    public static OpenClusterDTO createCluster(OpenClusterDTO clusterDTO){
        return createCluster(ApolloConfig.getEnvirment(),clusterDTO);
    }

    /**
     * 创建集群
     *
     * @param env         指定环境
     * @param clusterDTO  clusterDTO
     * @return            OpenClusterDTO
     */
    public static OpenClusterDTO createCluster(String env, OpenClusterDTO clusterDTO){
        return client.createCluster(env,clusterDTO);
    }

    /**
     * 通过id获取app
     *
     * @param appIds    id
     * @return          List<OpenAppDTO>
     */
    public static List<OpenAppDTO> getAppsByIds(List<String> appIds){
        return  client.getAppsByIds(appIds);
    }

    /**
     * 获取环境和集群信息
     *
     * @return List<OpenEnvClusterDTO>
     */
    public static List<OpenEnvClusterDTO> getEnvClusterInfo(){
        return getEnvClusterInfo(ApolloConfig.getAppid());
    }
    /**
     * 获取环境和集群信息
     *
     * @param appId     appId
     * @return          List<OpenEnvClusterDTO>
     */
    public static List<OpenEnvClusterDTO> getEnvClusterInfo(String appId){
        return client.getEnvClusterInfo(appId);
    }

    /**
     * 通过key获取指定配置
     *
     * @param key   key
     * @return      OpenItemDTO
     */
    public static OpenItemDTO getItem(String appIds, String env, String clusterName, String namespace, String key){
        return client.getItem(appIds, env, clusterName, namespace, key);
    }
    public static OpenItemDTO getItem(String key){
        return getItem(ApolloConfig.getNamespace(),key);
    }
    /**
     * 通过key获取指定配置
     *
     * @param key           key
     * @param namespace     名命空间
     * @return              OpenItemDTO
     */
    public static OpenItemDTO getItem(String namespace,String key){
        return getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), ApolloConfig.getClusterName(), namespace, key);
    }
    /**
     * 通过key获取指定配置
     *
     * @param clusterName   集群名称
     * @param key           key
     * @param namespace     名命空间
     * @return              OpenItemDTO
     */
    public static OpenItemDTO getItem(String clusterName,String namespace,String key){
        return getItem(ApolloConfig.getAppid(), ApolloConfig.getEnvirment(), clusterName, namespace, key);
    }
    /**
     * 通过key获取指定配置
     *
     * @param env           环境
     * @param clusterName   集群名称
     * @param key           key
     * @param namespace     名命空间
     * @return              OpenItemDTO
     */
    public static OpenItemDTO getItem(String env,String clusterName,String namespace,String key){
        return getItem(ApolloConfig.getAppid(), env, clusterName, namespace, key);
    }

    /**
     * 获取默认名命空间信息
     *
     * @return OpenNamespaceDTO
     */
    public static OpenNamespaceDTO getNamespace(){
        return getNamespace(ApolloConfig.getNamespace());
    }

    /**
     * 获取名命空间信息
     *
     * @param namespace     指定环境
     * @return              OpenNamespaceDTO
     */
    public static OpenNamespaceDTO getNamespace(String namespace){
        return getNamespace(ApolloConfig.getClusterName(), namespace);
    }

    public static OpenNamespaceDTO getNamespace(String clusterName,String namespace){
        return getNamespace(ApolloConfig.getEnvirment(), clusterName, namespace);
    }
    /**
     * 获取名命空间信息
     *
     * @param env           指定环境
     * @param clusterName   指定集群名称
     * @param namespace     指定名命空间
     * @return              OpenNamespaceDTO
     */
    public static OpenNamespaceDTO getNamespace(String env,String clusterName,String namespace){
        return client.getNamespace(ApolloConfig.getAppid(), env, clusterName, namespace);
    }

    /**
     * 移除某条配置
     *
     * @param key         key
     * @param operator    operator
     */
    public static void removeItem(String key,String operator){
        removeItem(ApolloConfig.getNamespace(), key,operator);
    }

    /**
     * 移除某条配置
     *
     * @param key         key
     * @param namespace   namespace
     * @param operator    operator
     */
    public static void removeItem(String namespace,String key,String operator){
        removeItem(ApolloConfig.getClusterName(), namespace, key,operator);
    }

    /**
     * 移除某条配置
     *
     * @param clusterName clusterName
     * @param key         key
     * @param namespace   namespace
     * @param operator    operator
     */
    public static void removeItem(String clusterName,String namespace,String key,String operator){
        removeItem(ApolloConfig.getEnvirment(), clusterName, namespace, key,operator);
    }


    /**
     * 移除某条配置
     *
     * @param env           环境
     * @param clusterName   集群名称
     * @param namespace     名命空间
     * @param key           key
     * @param operator      operator
     */
    public static void removeItem(String env,String clusterName,String namespace,String key,String operator){
        client.removeItem(ApolloConfig.getAppid(), env, clusterName, namespace, key,operator);
    }

    /**
     * 发布回滚
     *
     * @param env           环境
     * @param releaseId     发布id
     * @param operator      operator
     */
    public static void rollbackRelease(String env,long releaseId,String operator){
        client.rollbackRelease(env, releaseId, operator);
    }
    /**
     * 发布回滚
     *
     * @param releaseId     发布id
     * @param operator      operator
     */
    public static void rollbackRelease(long releaseId,String operator){
        rollbackRelease(ApolloConfig.getEnvirment(), releaseId, operator);
    }

    /**
     *
     * @param appId         appId
     * @param env           环境
     * @param clusterName   集群名称
     * @param namespace     名命空间
     * @param itemDTO       itemDTO
     */
    public static void updateItem(String appId,String env,String clusterName,String namespace,OpenItemDTO itemDTO){
        client.updateItem(appId, env, clusterName, namespace,itemDTO);

    }
    /**
     * 修改配置
     *
     * @param itemDTO       itemDTO
     */
    public static void updateItem(OpenItemDTO itemDTO){
        updateItem(ApolloConfig.getNamespace(), itemDTO);
    }

    /**
     * 修改配置
     * @param namespace     名命空间
     * @param itemDTO       itemDTO
     */
    public static void updateItem(String namespace,OpenItemDTO itemDTO){
        updateItem(ApolloConfig.getClusterName(), namespace, itemDTO);
    }
    /**
     * 修改配置
     * @param clusterName   集群名称
     * @param namespace     名命空间
     * @param itemDTO       itemDTO
     */
    public static void updateItem(String clusterName,String namespace,OpenItemDTO itemDTO){
        updateItem(ApolloConfig.getEnvirment(), clusterName, namespace, itemDTO);
    }

    /**
     * 修改配置
     * @param env           环境
     * @param clusterName   集群名称
     * @param namespace     名命空间
     * @param itemDTO       itemDTO
     */
    public static void updateItem(String env,String clusterName,String namespace,OpenItemDTO itemDTO){
        updateItem(ApolloConfig.getAppid(), env, clusterName, namespace, itemDTO);
    }
}
