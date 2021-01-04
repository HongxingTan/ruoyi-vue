package com.ruoyi.domain.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.netflix.appinfo.InstanceInfo;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.domain.entity.ServerInstanceInfo;
import com.ruoyi.domain.mapper.ServerInstanceInfoMapper;
import com.ruoyi.domain.service.IServerInstanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 服务实例信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-25
 */
@Service
public class ServerInstanceInfoServiceImpl implements IServerInstanceInfoService
{
    @Autowired
    private ServerInstanceInfoMapper serverInstanceInfoMapper;

    /**
     * 查询服务实例信息
     *
     * @param id 服务实例信息ID
     * @return 服务实例信息
     */
    @Override
    public ServerInstanceInfo selectServerInstanceInfoById(Long id)
    {
        return serverInstanceInfoMapper.selectServerInstanceInfoById(id);
    }

    /**
     * 查询服务实例信息列表
     *
     * @param serverInstanceInfo 服务实例信息
     * @return 服务实例信息
     */
    @Override
    public List<ServerInstanceInfo> selectServerInstanceInfoList(ServerInstanceInfo serverInstanceInfo)
    {
        return serverInstanceInfoMapper.selectServerInstanceInfoList(serverInstanceInfo);
    }

    /**
     * 新增服务实例信息
     *
     * @param serverInstanceInfo 服务实例信息
     * @return 结果
     */
    @Override
    public int insertServerInstanceInfo(ServerInstanceInfo serverInstanceInfo)
    {
        serverInstanceInfo.setCreateTime(DateUtils.getNowDate());
        return serverInstanceInfoMapper.insertServerInstanceInfo(serverInstanceInfo);
    }

    /**
     * 修改服务实例信息
     *
     * @param serverInstanceInfo 服务实例信息
     * @return 结果
     */
    @Override
    public int updateServerInstanceInfo(ServerInstanceInfo serverInstanceInfo)
    {
        serverInstanceInfo.setUpdateTime(DateUtils.getNowDate());
        return serverInstanceInfoMapper.updateServerInstanceInfo(serverInstanceInfo);
    }

    /**
     * 批量删除服务实例信息
     *
     * @param ids 需要删除的服务实例信息ID
     * @return 结果
     */
    @Override
    public int deleteServerInstanceInfoByIds(Long[] ids)
    {
        return serverInstanceInfoMapper.deleteServerInstanceInfoByIds(ids);
    }

    /**
     * 删除服务实例信息信息
     *
     * @param id 服务实例信息ID
     * @return 结果
     */
    @Override
    public int deleteServerInstanceInfoById(Long id)
    {
        return serverInstanceInfoMapper.deleteServerInstanceInfoById(id);
    }

    @Override
    public void saveAndUpdateInstance(List<ServiceInstance> instances) {
        if(CollectionUtils.isEmpty(instances))
            return;
        List<ServerInstanceInfo> dbInfos = this.serverInstanceInfoMapper.selectServerInstanceInfoList(new ServerInstanceInfo());
        Map<String, ServerInstanceInfo> infoMap = dbInfos.stream().
                collect(Collectors.toMap(info -> String.format("%s_%s", info.getIpAddr(), info.getPort()), info -> info));

        for (ServiceInstance instance:instances) {
            EurekaDiscoveryClient.EurekaServiceInstance esInstance= (EurekaDiscoveryClient.EurekaServiceInstance) instance;
            InstanceInfo instanceInfo = esInstance.getInstanceInfo();
            String ipAddr = instanceInfo.getIPAddr();
            String key=String.format("%s_%s", ipAddr, instanceInfo.getPort());
            ServerInstanceInfo serverInstanceInfo = infoMap.containsKey(key) ? infoMap.get(key) : new ServerInstanceInfo();
            this.copy(instanceInfo,serverInstanceInfo);
            if (serverInstanceInfo.getId()!=null && serverInstanceInfo.getId()>0){
                this.serverInstanceInfoMapper.updateServerInstanceInfo(serverInstanceInfo);
            }else{
                serverInstanceInfo.setCreateTime(new Date());
                this.serverInstanceInfoMapper.insertServerInstanceInfo(serverInstanceInfo);
            }
        }
    }

    @Override
    public int stopInfo(Long id) {
        try {
            if(id ==null || id==0)
                return 0;

            ServerInstanceInfo serverInstanceInfo = this.serverInstanceInfoMapper.selectServerInstanceInfoById(id);
            if(serverInstanceInfo==null)
                return 0;
            RestTemplate template = new RestTemplate();
            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
            paramMap.add("username", "admin");
            paramMap.add("password", "admin");
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
            ResponseEntity<Map> responseEntity = template.postForEntity("http://"+serverInstanceInfo.getIpAddr()+":"+serverInstanceInfo.getPort()+"/login", httpEntity, Map.class);
            if(Objects.equals(responseEntity.getStatusCode(), HttpStatus.OK.value())){
                return 0;
            }
            HttpHeaders rheader = responseEntity.getHeaders();
            String firstCookie = rheader.getFirst("Set-Cookie");

            String sesson = Splitter.on(";").splitToList(firstCookie).get(0);
            String svalue = Splitter.on("=").splitToList(sesson).get(1);
            RestTemplate secondtemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            header.add("Cookie",firstCookie);
            HttpEntity<Map<String, Object>> secondhttpEntity = new HttpEntity<Map<String, Object>>(Maps.newHashMap(),header);
            ResponseEntity<String> secondEntity = template.postForEntity("http://"+serverInstanceInfo.getIpAddr()+":"+serverInstanceInfo.getPort()+"/actuator/shutdown", secondhttpEntity, String.class);
            if(Objects.equals(secondEntity.getStatusCode(), HttpStatus.OK)){
                return 1;
            }
        }catch (Exception e){
           throw  new BaseException(e.getMessage());
        }
        return 0;
    }

    private void copy(InstanceInfo instanceInfo, ServerInstanceInfo serverInstanceInfo) {
        if(instanceInfo==null || serverInstanceInfo==null)
            return;
        serverInstanceInfo.setInstanceId(instanceInfo.getInstanceId());
        /** 应用名称 */
        serverInstanceInfo.setAppName(instanceInfo.getAppName());
        /** 应用分组名称 */
        serverInstanceInfo.setAppGroupName(instanceInfo.getAppGroupName());

        /** ip */
        serverInstanceInfo.setIpAddr(instanceInfo.getIPAddr());
        /** sid */
        serverInstanceInfo.setSid(instanceInfo.getSID());
        /** 端口 */
        serverInstanceInfo.setPort(Long.valueOf(instanceInfo.getPort()));
        /** 安全端口 */
        serverInstanceInfo.setSecurePort(Long.valueOf(instanceInfo.getSecurePort()));
        /** 主页URL */
        serverInstanceInfo.setHomePageUrl(instanceInfo.getHomePageUrl());
        /** 状态页URL */
        serverInstanceInfo.setStatusPageUrl(instanceInfo.getStatusPageUrl());
        /** 健康检测URL */
        serverInstanceInfo.setHealthCheckUrl(instanceInfo.getHealthCheckUrl());
        /** vip地址 */
        serverInstanceInfo.setVipAddress(instanceInfo.getVIPAddress());
        /** 安全vip地址 */
        serverInstanceInfo.setSecureVipAddress(instanceInfo.getSecureVipAddress());
        /** 国家id */
        serverInstanceInfo.setCountryId(String.valueOf(instanceInfo.getCountryId()));
        /** 主机名 */
        serverInstanceInfo.setHostName(instanceInfo.getHostName());
        /** 状态 */
        if(instanceInfo.getStatus()!=null){
            serverInstanceInfo.setStatus(instanceInfo.getStatus().toString());
        }
        /** 最后更新时间 */
        if (instanceInfo.getLastUpdatedTimestamp() > 0) {
            serverInstanceInfo.setLastUpdatedTime(new Date(instanceInfo.getLastUpdatedTimestamp()));
        }
        /** 上次异常时间 */
        if(instanceInfo.getLastDirtyTimestamp() >0){
            serverInstanceInfo.setLastDirtyTime(new Date(instanceInfo.getLastDirtyTimestamp()));
        }
        serverInstanceInfo.setUpdateTime(new Date());

    }
}
