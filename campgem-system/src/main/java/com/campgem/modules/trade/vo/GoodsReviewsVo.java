package com.campgem.modules.trade.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 商品留言
 * @Author: campgem
 * @Date: 2019-08-17
 * @Version: V1.0
 */
@Data
@TableName("goods_reviews")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "goods_reviews对象", description = "商品留言")
public class GoodsReviewsVo {
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	/**
	 * 用户ID
	 */
	@Excel(name = "用户ID", width = 15)
	@ApiModelProperty(value = "用户ID")
	private String uid;
	
	@ApiModelProperty("用户头像(原文件)")
	private String srcFace;
	
	@ApiModelProperty("用户昵称")
	private String nickName;
	
	@ApiModelProperty("用户身份类型")
	private Integer identity;
	
	/**
	 * 商品ID
	 */
//	@Excel(name = "商品ID", width = 15)
//	@ApiModelProperty(value = "商品ID")
//	private String goodsId;
	/**
	 * 留言内容
	 */
	@Excel(name = "留言内容", width = 15)
	@ApiModelProperty(value = "留言内容")
	private String content;
	/**
	 * 留言时间
	 */
	@Excel(name = "留言时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "留言时间")
	private Date createTime;
}
