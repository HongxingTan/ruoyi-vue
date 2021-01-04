package com.ruoyi.domain.dto;

import com.ruoyi.common.annotation.Excel;

public class GwCacheConfigDto {
    /** 缓存名称 */
    private String cacheName;

    /** 缓存uri */
    private String cacheUri;

    /** 是否为通用缓存 */
    private Integer isGeneral;

    /** 缓存有效时间 */
    private Integer timeout;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheUri() {
        return cacheUri;
    }

    public void setCacheUri(String cacheUri) {
        this.cacheUri = cacheUri;
    }

    public Integer getIsGeneral() {
        return isGeneral;
    }

    public void setIsGeneral(Integer isGeneral) {
        this.isGeneral = isGeneral;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
