package com.ruoyi.common.enums;

public enum ServerInstanceStatus {
    UP("UP","上线"),
    DOWN("DOWN","下线"),
    STARTING("STARTING","启动"),
    OUT_OF_SERVICE("OUT_OF_SERVICE","服务中断"),
    UNKNOWN("UNKNOWN","未知");
    private final String code;
    private final String info;

    ServerInstanceStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }

}
