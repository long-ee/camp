package com.campgem.modules.university.dto;

import com.campgem.common.api.dto.BaseDto;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.modules.university.entity.enums.MemberTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="用户注册数据模型",description="用户注册数据模型")
public class UserRegistrationDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户类型")
    @NotBlank(message = "用户类型不能为空")
    private String memberType;

    @ApiModelProperty(value = "用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "用户邮箱")
    @NotBlank(message = "用户邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "邮箱验证码")
    @NotBlank(message = "邮箱验证码不能为空")
    private String emailValidityCode;

    @ApiModelProperty(value = "用户手机")
    @NotBlank(message = "用户手机不能为空")
    private String mobile;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String password;

    @ApiModelProperty(value = "用户密码确认")
    @NotBlank(message = "用户密码确认不能为空")
    private String confirmPassword;

    @ApiModelProperty(value = "城市ID")
    private String cityId;
    @ApiModelProperty(value = "学校ID")
    private String universityId;
    @ApiModelProperty(value = "PayPal支付账号")
    private String payPal;
    @ApiModelProperty(value = "商家名称")
    private String businessName;
    @ApiModelProperty(value = "商家地址")
    private String address;
    @ApiModelProperty(value = "商家类型")
    private String businessType;
    @ApiModelProperty(value = "营业日期")
    private String businessDate;
    @ApiModelProperty(value = "开始营业时间")
    private String openingTime;
    @ApiModelProperty(value = "结束营业时间")
    private String closingTime;
    @ApiModelProperty(value = "用户第三方认证信息", hidden = true)
    private IdentifyInfo identifyInfo;

    /**
     * 根据用户类型校验参数
     * @return
     */
    @Override
    public void paramValidation(){
        if(StringUtils.equals(MemberTypeEnum.STUDENT.code(), this.memberType)){
            if(StringUtils.isBlank(this.universityId)){
                throw new JeecgBootException("请填写完整信息");
            }
        }else if(StringUtils.equals(MemberTypeEnum.INDIVIDUAL.code(), this.memberType)){
            if(StringUtils.isBlank(this.cityId)){
                throw new JeecgBootException("请填写完整信息");
            }
        }else if(StringUtils.equals(MemberTypeEnum.LOCAL_BUSINESS.code(), this.memberType)){
            if(StringUtils.isBlank(this.businessName) || StringUtils.isBlank(this.address) ||
               StringUtils.isBlank(this.businessType) || StringUtils.isBlank(this.businessDate) ||
               StringUtils.isBlank(this.payPal) || StringUtils.isBlank(this.cityId)
            ){
                throw new JeecgBootException("请填写完整信息");
            }
        }else if(StringUtils.equals(MemberTypeEnum.ONLINE_BUSINESS.code(), this.memberType)){
            if(StringUtils.isBlank(this.businessName) || StringUtils.isBlank(this.payPal)){
                throw new JeecgBootException("请填写完整信息");
            }
        }else {
            throw new JeecgBootException("用户类型不存在");
        }
    }

}
