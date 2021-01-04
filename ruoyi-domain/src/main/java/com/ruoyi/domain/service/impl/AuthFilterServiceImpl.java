package com.ruoyi.domain.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.domain.dto.AuthFilterDTO;
import com.ruoyi.domain.entity.AuthFilter;
import com.ruoyi.domain.mapper.AuthFilterMapper;
import com.ruoyi.domain.service.IAuthFilterService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 权限拦截器Service业务层处理
 *
 * @author guokui
 * @date 2020-12-28
 */
@Service
public class AuthFilterServiceImpl implements IAuthFilterService
{
    @Autowired
    private AuthFilterMapper authFilterMapper;

    /**
     * 查询权限拦截器
     *
     * @param id 权限拦截器ID
     * @return 权限拦截器
     */
    @Override
    public AuthFilterDTO selectAuthFilterById(Long id)
    {
        AuthFilter authFilter = authFilterMapper.selectAuthFilterById(id);
        AuthFilterDTO authFilterDTO = new AuthFilterDTO();
        if(authFilter==null)
            return authFilterDTO;
        BeanUtils.copyProperties(authFilter,authFilterDTO);
        if(!Strings.isEmpty(authFilterDTO.getIgnoreClients()))
            authFilterDTO.setIgnoreClientList(Splitter.on(",").splitToList(authFilterDTO.getIgnoreClients()));
        if(!Strings.isEmpty(authFilterDTO.getIgnoreUrls()))
            authFilterDTO.setIgnoreUrlList(Splitter.on(",").splitToList(authFilterDTO.getIgnoreUrls()));
        if(!Strings.isEmpty(authFilterDTO.getChannelKeys())){
            JSONObject jsonObject = JSONObject.parseObject(authFilterDTO.getChannelKeys());
            JSONArray array =new JSONArray();
            jsonObject.keySet().forEach(key->{
                JSONObject obj = new JSONObject();
                obj.put("key",key);
                obj.put("value",jsonObject.getString(key));
                array.add(obj);
            });
            authFilterDTO.setChannelKeyArray(array);
        }

        return authFilterDTO;
    }

    /**
     * 查询权限拦截器列表
     *
     * @param authFilter 权限拦截器
     * @return 权限拦截器
     */
    @Override
    public List<AuthFilter> selectAuthFilterList(AuthFilter authFilter)
    {
        return authFilterMapper.selectAuthFilterList(authFilter);
    }

    /**
     * 新增权限拦截器
     *
     * @param authFilter 权限拦截器
     * @return 结果
     */
    @Override
    public int insertAuthFilter(AuthFilterDTO authFilter)
    {
        if(!CollectionUtils.isEmpty(authFilter.getIgnoreClientList()))
            authFilter.setIgnoreClients(Joiner.on(",").join(authFilter.getIgnoreClientList()));
        if(!CollectionUtils.isEmpty(authFilter.getIgnoreUrlList()))
            authFilter.setIgnoreUrls(Joiner.on(",").join(authFilter.getIgnoreUrlList()));
        if(authFilter.getChannelKeyArray()!=null && authFilter.getChannelKeyArray().size()>0){
            JSONObject channelObj=new JSONObject();
            for (int i=0;i<authFilter.getChannelKeyArray().size();i++){
                JSONObject ckJson =authFilter.getChannelKeyArray().getJSONObject(i);
                channelObj.put(ckJson.getString("key"),ckJson.getString("value"));
            }
            authFilter.setChannelKeys(channelObj.toJSONString());
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        authFilter.setCreateBy(loginUser.getUsername());
        authFilter.setCreateTime(DateUtils.getNowDate());
        authFilter.setCreateId(loginUser.getUser().getUserId());
        authFilter.setUpdateBy(loginUser.getUsername());
        authFilter.setUpdateTime(DateUtils.getNowDate());
        authFilter.setUpdateId(loginUser.getUser().getUserId());
        return authFilterMapper.insertAuthFilter(authFilter);
    }

    /**
     * 修改权限拦截器
     *
     * @param authFilter 权限拦截器
     * @return 结果
     */
    @Override
    public int updateAuthFilter(AuthFilterDTO authFilter)
    {
        if(!CollectionUtils.isEmpty(authFilter.getIgnoreClientList()))
            authFilter.setIgnoreClients(Joiner.on(",").join(authFilter.getIgnoreClientList()));
        if(!CollectionUtils.isEmpty(authFilter.getIgnoreUrlList()))
            authFilter.setIgnoreUrls(Joiner.on(",").join(authFilter.getIgnoreUrlList()));
        if(authFilter.getChannelKeyArray()!=null && authFilter.getChannelKeyArray().size()>0){
            JSONObject channelObj=new JSONObject();
            for (int i=0;i<authFilter.getChannelKeyArray().size();i++){
                JSONObject ckJson =authFilter.getChannelKeyArray().getJSONObject(i);
                channelObj.put(ckJson.getString("key"),ckJson.getString("value"));
            }
            authFilter.setChannelKeys(channelObj.toJSONString());
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        authFilter.setUpdateBy(loginUser.getUsername());
        authFilter.setUpdateTime(DateUtils.getNowDate());
        authFilter.setUpdateId(loginUser.getUser().getUserId());
        return authFilterMapper.updateAuthFilter(authFilter);
    }

    /**
     * 批量删除权限拦截器
     *
     * @param ids 需要删除的权限拦截器ID
     * @return 结果
     */
    @Override
    public int deleteAuthFilterByIds(Long[] ids)
    {
        return authFilterMapper.deleteAuthFilterByIds(ids);
    }

    /**
     * 删除权限拦截器信息
     *
     * @param id 权限拦截器ID
     * @return 结果
     */
    @Override
    public int deleteAuthFilterById(Long id)
    {
        return authFilterMapper.deleteAuthFilterById(id);
    }

    @Override
    public List<Map> getAuthData(Long id) {
        List resultList= Lists.newArrayList();
        if(id==null || id==0)
            return resultList;
        AuthFilter authFilter = this.authFilterMapper.selectAuthFilterById(id);
        if(authFilter==null)
            return resultList;
        Map<String,Object> apolloMap= Maps.newHashMap();
        String apolloStr= this.covertToApollo(authFilter);
        apolloMap.put("name","Apolo");
        apolloMap.put("value",apolloStr);
        resultList.add(apolloMap);
        Map<String,Object> nacosMap= Maps.newHashMap();
        String nacosStr= this.covertToNacos(authFilter);
        nacosMap.put("name","Nacos");
        nacosMap.put("value",nacosStr);
        resultList.add(nacosMap);

        return resultList;
    }
    private String covertToNacos(AuthFilter authFilter) {
        if(authFilter==null)
            return "";
        StringBuilder sb= new StringBuilder("AuthFilter=");
        sb.append(authFilter.getEncodeKey()).append(",");
        sb.append(authFilter.getIgnoreClients().replace(",","$$")).append(",");
        sb.append(authFilter.getIgnoreUrls().replace(",","$$")).append(",");
        sb.append(authFilter.getChannelKeys().replace(",","$$"));
        return sb.toString();
    }
    private String covertToApollo(AuthFilter authFilter) {
        if(authFilter==null)
            return "";
        StringBuilder sb= new StringBuilder("AuthFilter=");
        sb.append(authFilter.getEncodeKey()).append(",");
        sb.append(authFilter.getIgnoreClients().replace(",","$$")).append(",");
        sb.append(authFilter.getIgnoreUrls().replace(",","$$")).append(",");
        sb.append(authFilter.getChannelKeys().replace(",","$$"));
        return sb.toString();
    }
}
