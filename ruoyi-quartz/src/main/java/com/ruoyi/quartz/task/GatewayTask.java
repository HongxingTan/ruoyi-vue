package com.ruoyi.quartz.task;

import com.ruoyi.domain.service.IServerInstanceInfoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *网关任务
 */
@Component("gatewayTask")
public class GatewayTask
{
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private IServerInstanceInfoService serverInstanceInfoService;

    public void gatewayMultipleParams(String applicationName)
    {
        try {
            if(Strings.isEmpty(applicationName))
                return;

            List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
            if(CollectionUtils.isEmpty(instances))
                return;
            serverInstanceInfoService.saveAndUpdateInstance(instances);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
