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

    public IdentifyInfo() {
    }

    public IdentifyInfo(String identifier, String identityType) {
        this.identifier = identifier;
        this.identityType = identityType;
    }
}
