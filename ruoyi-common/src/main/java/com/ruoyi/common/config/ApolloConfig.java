package com.ruoyi.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "apollo")
public class ApolloConfig {
    private static String portalUrl;
    private static String token;
    private static String appid;
    private static String envirment;
    private static String clusterName;
    private static String namespace;

    public static String getPortalUrl() {
        return portalUrl;
    }

    public void setPortalUrl(String portalUrl) {
        this.portalUrl = portalUrl;
    }

    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public static String getEnvirment() {
        return envirment;
    }

    public void setEnvirment(String envirment) {
        this.envirment = envirment;
    }

    public static String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public static String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
