package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 社团活动日历显示数据模型
 * @Author: campgem
 * @Date:   2019-08-28
 * @Version: V1.0
 */
@Data
@ApiModel(value="社团活动日历显示数据模型", description="社团活动日历显示数据模型")
public class ClubActivityCalendarVo {

	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "活动结束日期")
	private Date startDate;
	@ApiModelProperty(value = "活动数量")
	private Integer count;

}
