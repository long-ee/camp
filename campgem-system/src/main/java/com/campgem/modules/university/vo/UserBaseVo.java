package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: campgem
 */
@Data
public class UserBaseVo implements Serializable {

    /**
     * 认证账号
     */
    private String identifier;
    /**
     * 认证类型
     */
    private String identityType;
    /**
     * 认证凭证
     */
    private String certificate;
    /**
     * 用户唯一标识uid
     */
    private String uid;
    /**
     * 用户状态
     */
    private String userStatus;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 性别（1：男 2：女）
     */
    private String gender;

    private String memberType;

    private String memberId;

    private String universityId;

    private String cityId;
}
