package com.ruoyi.domain.dto;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.domain.entity.AuthFilter;
import lombok.Data;

import java.util.List;

@Data
public class AuthFilterDTO extends AuthFilter {
    private List<String> ignoreClientList;
    private List<String> ignoreUrlList;
    private JSONArray channelKeyArray;
}
