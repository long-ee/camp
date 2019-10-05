package com.campgem.common.api.vo;

import lombok.Data;

/**
 * 用户认证对象
 * @Author: campgem
 */
@Data
public class IdentifyInfo {

    private String identifier;

    private String identityType;
    /**
     * 用户系统ID(app、manage)
     */
    private String serviceId;

    public IdentifyInfo() {
    }

    public IdentifyInfo(String identifier, String identityType, String serviceId) {
        this.identifier = identifier;
        this.identityType = identityType;
        this.serviceId = serviceId;
    }
}
