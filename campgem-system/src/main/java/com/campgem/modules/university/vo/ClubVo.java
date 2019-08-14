package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ApiModel(value="社团显示数据模型", description="社团显示数据模型")
public class ClubVo {

	/**id*/
    @ApiModelProperty(value = "id")
	private String id;
	/**社团所属分类*/
    @ApiModelProperty(value = "社团所属分类")
	private String categoryId;
	/**社团所属分类名称*/
	@ApiModelProperty(value = "社团所属分类名称")
	private String categoryName;
	/**社团图标*/
    @ApiModelProperty(value = "社团图标")
	private String clubIcon;
	/**社团名称*/
    @ApiModelProperty(value = "社团名称")
	private String clubName;
	/**社团简介*/
    @ApiModelProperty(value = "社团简介")
	private String information;
	/**社团创建人*/
    @ApiModelProperty(value = "社团创建人")
	private String creatorId;
	/**社团创建人名称*/
	@ApiModelProperty(value = "社团创建人名称")
	private String creatorName;
	/**所属大学*/
    @ApiModelProperty(value = "所属大学")
	private String universityId;
	/**所属大学名称*/
	@ApiModelProperty(value = "所属大学名称")
	private String universityName;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
}
