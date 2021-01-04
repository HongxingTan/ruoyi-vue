package com.ruoyi.domain.service;

import com.ruoyi.domain.dto.AuthFilterDTO;
import com.ruoyi.domain.entity.AuthFilter;

import java.util.List;
import java.util.Map;

/**
 * 权限拦截器Service接口
 *
 * @author guokui
 * @date 2020-12-28
 */
public interface IAuthFilterService
{
    /**
     * 查询权限拦截器
     *
     * @param id 权限拦截器ID
     * @return 权限拦截器
     */
    public AuthFilterDTO selectAuthFilterById(Long id);

    /**
     * 查询权限拦截器列表
     *
     * @param authFilter 权限拦截器
     * @return 权限拦截器集合
     */
    public List<AuthFilter> selectAuthFilterList(AuthFilter authFilter);

    /**
     * 新增权限拦截器
     *
     * @param authFilter 权限拦截器
     * @return 结果
     */
    public int insertAuthFilter(AuthFilterDTO authFilter);

    /**
     * 修改权限拦截器
     *
     * @param authFilter 权限拦截器
     * @return 结果
     */
    public int updateAuthFilter(AuthFilterDTO authFilter);

    /**
     * 批量删除权限拦截器
     *
     * @param ids 需要删除的权限拦截器ID
     * @return 结果
     */
    public int deleteAuthFilterByIds(Long[] ids);

    /**
     * 删除权限拦截器信息
     *
     * @param id 权限拦截器ID
     * @return 结果
     */
    public int deleteAuthFilterById(Long id);

    List<Map> getAuthData(Long id);
}
