package com.ruoyi.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * 服务实例信息对象 server_instance_info
 *
 * @author ruoyi
 * @date 2020-12-25
 */
public class ServerInstanceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 实例id */
    @Excel(name = "实例id")
    private String instanceId;

    /** 应用名称 */
    @Excel(name = "应用名称")
    private String appName;

    /** 应用分组名称 */
    @Excel(name = "应用分组名称")
    private String appGroupName;

    /** ip */
    @Excel(name = "ip")
    private String ipAddr;

    /** sid */
    @Excel(name = "sid")
    private String sid;

    /** 端口 */
    @Excel(name = "端口")
    private Long port;

    /** 安全端口 */
    @Excel(name = "安全端口")
    private Long securePort;

    /** 主页URL */
    @Excel(name = "主页URL")
    private String homePageUrl;

    /** 状态页URL */
    @Excel(name = "状态页URL")
    private String statusPageUrl;

    /** 健康检测URL */
    @Excel(name = "健康检测URL")
    private String healthCheckUrl;

    /** vip地址 */
    @Excel(name = "vip地址")
    private String vipAddress;

    /** 安全vip地址 */
    @Excel(name = "安全vip地址")
    private String secureVipAddress;

    /** 国家id */
    @Excel(name = "国家id")
    private String countryId;

    /** 主机名 */
    @Excel(name = "主机名")
    private String hostName;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdatedTime;

    /** 上次异常时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上次异常时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastDirtyTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setInstanceId(String instanceId)
    {
        this.instanceId = instanceId;
    }

    public String getInstanceId()
    {
        return instanceId;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getAppName()
    {
        return appName;
    }
    public void setAppGroupName(String appGroupName)
    {
        this.appGroupName = appGroupName;
    }

    public String getAppGroupName()
    {
        return appGroupName;
    }
    public void setIpAddr(String ipAddr)
    {
        this.ipAddr = ipAddr;
    }

    public String getIpAddr()
    {
        return ipAddr;
    }
    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getSid()
    {
        return sid;
    }
    public void setPort(Long port)
    {
        this.port = port;
    }

    public Long getPort()
    {
        return port;
    }
    public void setSecurePort(Long securePort)
    {
        this.securePort = securePort;
    }

    public Long getSecurePort()
    {
        return securePort;
    }
    public void setHomePageUrl(String homePageUrl)
    {
        this.homePageUrl = homePageUrl;
    }

    public String getHomePageUrl()
    {
        return homePageUrl;
    }
    public void setStatusPageUrl(String statusPageUrl)
    {
        this.statusPageUrl = statusPageUrl;
    }

    public String getStatusPageUrl()
    {
        return statusPageUrl;
    }
    public void setHealthCheckUrl(String healthCheckUrl)
    {
        this.healthCheckUrl = healthCheckUrl;
    }

    public String getHealthCheckUrl()
    {
        return healthCheckUrl;
    }
    public void setVipAddress(String vipAddress)
    {
        this.vipAddress = vipAddress;
    }

    public String getVipAddress()
    {
        return vipAddress;
    }
    public void setSecureVipAddress(String secureVipAddress)
    {
        this.secureVipAddress = secureVipAddress;
    }

    public String getSecureVipAddress()
    {
        return secureVipAddress;
    }
    public void setCountryId(String countryId)
    {
        this.countryId = countryId;
    }

    public String getCountryId()
    {
        return countryId;
    }
    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }

    public String getHostName()
    {
        return hostName;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setLastUpdatedTime(Date lastUpdatedTime)
    {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getLastUpdatedTime()
    {
        return lastUpdatedTime;
    }
    public void setLastDirtyTime(Date lastDirtyTime)
    {
        this.lastDirtyTime = lastDirtyTime;
    }

    public Date getLastDirtyTime()
    {
        return lastDirtyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("instanceId", getInstanceId())
            .append("appName", getAppName())
            .append("appGroupName", getAppGroupName())
            .append("ipAddr", getIpAddr())
            .append("sid", getSid())
            .append("port", getPort())
            .append("securePort", getSecurePort())
            .append("homePageUrl", getHomePageUrl())
            .append("statusPageUrl", getStatusPageUrl())
            .append("healthCheckUrl", getHealthCheckUrl())
            .append("vipAddress", getVipAddress())
            .append("secureVipAddress", getSecureVipAddress())
            .append("countryId", getCountryId())
            .append("hostName", getHostName())
            .append("status", getStatus())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .append("lastDirtyTime", getLastDirtyTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
