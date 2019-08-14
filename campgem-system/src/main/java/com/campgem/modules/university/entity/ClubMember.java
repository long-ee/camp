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
 * @Description: 社团会员关系
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@TableName("club_member")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="club_member对象", description="社团会员关系")
public class ClubMember {
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**社团ID*/
	@Excel(name = "社团ID", width = 15)
    @ApiModelProperty(value = "社团ID")
	private String clubId;
	/**会员ID*/
	@Excel(name = "会员ID", width = 15)
    @ApiModelProperty(value = "会员ID")
	private String memberId;
}
