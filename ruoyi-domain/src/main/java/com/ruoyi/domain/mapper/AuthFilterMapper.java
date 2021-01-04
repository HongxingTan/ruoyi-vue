package com.ruoyi.domain.mapper;

import com.ruoyi.domain.entity.AuthFilter;

import java.util.List;

/**
 * 权限拦截器Mapper接口
 *
 * @author guokui
 * @date 2020-12-28
 */
public interface AuthFilterMapper
{
    /**
     * 查询权限拦截器
     *
     * @param id 权限拦截器ID
     * @return 权限拦截器
     */
    public AuthFilter selectAuthFilterById(Long id);

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
    public int insertAuthFilter(AuthFilter authFilter);

    /**
     * 修改权限拦截器
     *
     * @param authFilter 权限拦截器
     * @return 结果
     */
    public int updateAuthFilter(AuthFilter authFilter);

    /**
     * 删除权限拦截器
     *
     * @param id 权限拦截器ID
     * @return 结果
     */
    public int deleteAuthFilterById(Long id);

    /**
     * 批量删除权限拦截器
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAuthFilterByIds(Long[] ids);
}
