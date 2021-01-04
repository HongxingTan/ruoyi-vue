package com.ruoyi.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 权限拦截器对象 auth_filter
 *
 * @author guokui
 * @date 2020-12-28
 */
public class AuthFilter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 网关解密登录前端密码 秘钥 */
    @Excel(name = "网关解密登录前端密码 秘钥")
    private String encodeKey;

    /** 网关不需要校验验证码的客户端 */
    @Excel(name = "网关不需要校验验证码的客户端")
    private String ignoreClients;

    /** 网关不需要验证的url */
    @Excel(name = "网关不需要验证的url")
    private String ignoreUrls;

    /** 渠道,key映射 */
    @Excel(name = "渠道,key映射")
    private String channelKeys;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

    /** 删除标识 */
    private Long delFlag;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createId;

    /** 修改人id */
    @Excel(name = "修改人id")
    private Long updateId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setEncodeKey(String encodeKey)
    {
        this.encodeKey = encodeKey;
    }

    public String getEncodeKey()
    {
        return encodeKey;
    }
    public void setIgnoreClients(String ignoreClients)
    {
        this.ignoreClients = ignoreClients;
    }

    public String getIgnoreClients()
    {
        return ignoreClients;
    }
    public void setIgnoreUrls(String ignoreUrls)
    {
        this.ignoreUrls = ignoreUrls;
    }

    public String getIgnoreUrls()
    {
        return ignoreUrls;
    }
    public void setChannelKeys(String channelKeys)
    {
        this.channelKeys = channelKeys;
    }

    public String getChannelKeys()
    {
        return channelKeys;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setDelFlag(Long delFlag)
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag()
    {
        return delFlag;
    }
    public void setCreateId(Long createId)
    {
        this.createId = createId;
    }

    public Long getCreateId()
    {
        return createId;
    }
    public void setUpdateId(Long updateId)
    {
        this.updateId = updateId;
    }

    public Long getUpdateId()
    {
        return updateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("encodeKey", getEncodeKey())
            .append("ignoreClients", getIgnoreClients())
            .append("ignoreUrls", getIgnoreUrls())
            .append("channelKeys", getChannelKeys())
            .append("type", getType())
            .append("delFlag", getDelFlag())
            .append("createId", getCreateId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
