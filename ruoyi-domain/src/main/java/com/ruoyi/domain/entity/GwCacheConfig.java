package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 结果缓存配置对象 gw_cache_config
 * 
 * @author ruoyi
 * @date 2020-12-29
 */
public class GwCacheConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 缓存名称 */
    @Excel(name = "缓存名称")
    private String cacheName;

    /** 缓存uri */
    @Excel(name = "缓存uri")
    private String cacheUri;

    /** 是否为通用缓存 */
    @Excel(name = "是否为通用缓存")
    private Integer isGeneral;

    /** 缓存有效时间 */
    @Excel(name = "缓存有效时间")
    private Integer timeout;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCacheName(String cacheName) 
    {
        this.cacheName = cacheName;
    }

    public String getCacheName() 
    {
        return cacheName;
    }
    public void setCacheUri(String cacheUri) 
    {
        this.cacheUri = cacheUri;
    }

    public String getCacheUri() 
    {
        return cacheUri;
    }
    public void setIsGeneral(Integer isGeneral) 
    {
        this.isGeneral = isGeneral;
    }

    public Integer getIsGeneral() 
    {
        return isGeneral;
    }
    public void setTimeout(Integer timeout) 
    {
        this.timeout = timeout;
    }

    public Integer getTimeout() 
    {
        return timeout;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cacheName", getCacheName())
            .append("cacheUri", getCacheUri())
            .append("isGeneral", getIsGeneral())
            .append("timeout", getTimeout())
            .toString();
    }
}
