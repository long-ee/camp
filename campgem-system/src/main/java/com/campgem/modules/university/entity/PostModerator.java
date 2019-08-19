package com.campgem.modules.university.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 版块管理员关系信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("post_moderator")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="post_moderator对象", description="版块管理员关系信息")
public class PostModerator {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**版块ID*/
	@Excel(name = "版块ID", width = 15)
    @ApiModelProperty(value = "版块ID")
	private String postId;
	/**管理员会员ID*/
	@Excel(name = "管理员会员ID", width = 15)
    @ApiModelProperty(value = "管理员会员ID")
	private String moderatorId;
	/**是否是主管理*/
	@Excel(name = "是否是主管理", width = 15)
	@ApiModelProperty(value = "是否是主管理")
	private Integer isPrimaryAdmin;
}
