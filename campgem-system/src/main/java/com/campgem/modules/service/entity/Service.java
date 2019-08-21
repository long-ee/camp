package com.campgem.modules.service.entity;

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

/**
 * @Description: 服务
 * @Author: campgem
 * @Date: 2019-08-20
 * @Version: V1.0
 */
@Data
@TableName("service")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "service对象", description = "服务")
public class Service {
	
	/**
	 * id
	 */
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**
	 * 关联的会员ID
	 */
	@Excel(name = "关联的会员ID", width = 15)
	@ApiModelProperty(value = "关联的会员ID")
	private java.lang.String uid;
	/**
	 * 商家名
	 */
	@Excel(name = "商家名", width = 15)
	@ApiModelProperty(value = "商家名")
	private java.lang.String businessName;
	/**
	 * 商家会员类型
	 */
	@Excel(name = "商家会员类型", width = 15)
	@ApiModelProperty(value = "商家会员类型")
	private java.lang.String identity;
	/**
	 * 服务分类
	 */
	@Excel(name = "服务分类", width = 15)
	@ApiModelProperty(value = "服务分类")
	private java.lang.String categoryId;
	/**
	 * 价格
	 */
	@Excel(name = "价格", width = 15)
	@ApiModelProperty(value = "现价，仅在identity是2才有效")
	private java.math.BigDecimal salePrice;
	/**
	 * 服务名称
	 */
	@Excel(name = "服务名称", width = 15)
	@ApiModelProperty(value = "服务名称")
	private java.lang.String serviceName;
	/**
	 * 状态，0:Disable 1:Enable
	 */
	@Excel(name = "状态，0:Disable 1:Enable", width = 15)
	@ApiModelProperty(value = "状态，0:Disable 1:Enable")
	private java.lang.Integer status;
	/**
	 * 浏览次数
	 */
	@ApiModelProperty("浏览次数")
	private Integer viewCount;
	/**
	 * 售出数量
	 */
	@ApiModelProperty("售出数量")
	private Integer saleCount;
	/**
	 * 税率
	 */
	@Excel(name = "税率", width = 15)
	@ApiModelProperty(value = "税率")
	private java.math.BigDecimal taxes;
	/**
	 * 标签，以,分隔
	 */
	@Excel(name = "标签，以,分隔", width = 15)
	@ApiModelProperty(value = "标签，以,分隔")
	private java.lang.String tags;
	/**
	 * 描述
	 */
	@Excel(name = "描述", width = 15)
	@ApiModelProperty(value = "描述")
	private java.lang.Object description;
	/**
	 * 删除状态
	 */
	@Excel(name = "删除状态", width = 15)
	@ApiModelProperty(value = "删除状态")
	private java.lang.Integer delFlag;
	/**
	 * createBy
	 */
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	private java.lang.String createBy;
	/**
	 * createTime
	 */
	@Excel(name = "createTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**
	 * updateBy
	 */
	@Excel(name = "updateBy", width = 15)
	@ApiModelProperty(value = "updateBy")
	private java.lang.String updateBy;
	/**
	 * updateTime
	 */
	@Excel(name = "updateTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
}
