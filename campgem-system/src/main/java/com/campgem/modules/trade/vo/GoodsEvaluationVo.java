package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 商品评价
 * @Author: ling
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@ApiModel(value = "商品评价显示模型对象", description = "商品评价显示模型对象")
public class GoodsEvaluationVo implements Serializable {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty("用户头像")
	private String srcFace;
	
	@ApiModelProperty("用户昵称")
	private String nickName;
	
	@Excel(name = "评价内容", width = 15)
	@ApiModelProperty(value = "评价内容")
	private Object content;
	
	@Excel(name = "评价星级", width = 15)
	@ApiModelProperty(value = "评价星级")
	private Integer rating;
	
	@Excel(name = "评价时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "评价时间")
	private java.util.Date createTime;
}
