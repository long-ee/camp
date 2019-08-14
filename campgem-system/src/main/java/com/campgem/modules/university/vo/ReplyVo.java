package com.campgem.modules.university.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 话题回复信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Data
@ApiModel(value="ReplyVo", description="话题回复显示数据模型")
public class ReplyVo {
	/**id*/
    @ApiModelProperty(value = "id")
	private String id;
	/**楼层*/
	@Excel(name = "楼层", width = 15)
    @ApiModelProperty(value = "楼层")
	private Integer floorNumber;
	/**回复内容*/
	@Excel(name = "回复内容", width = 15)
    @ApiModelProperty(value = "回复内容")
	private Object replyContent;
	/**回复时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "回复时间")
	private java.util.Date replyTime;
	/**回复者ID*/
	@ApiModelProperty(value = "回复者ID")
	private String replierId;
	/**回复者*/
	@ApiModelProperty(value = "回复者")
	private String replier;
	/**点赞数量*/
    @ApiModelProperty(value = "点赞数量")
	private Integer dislikeCount;
	/**踩一踩数量*/
    @ApiModelProperty(value = "踩一踩数量")
	private Integer likeCount;
}
