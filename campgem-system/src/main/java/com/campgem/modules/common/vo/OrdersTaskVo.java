package com.campgem.modules.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.List;

/**
 * @Description: 订单定时任务模型
 * @Author: ling
 * @Date: 2019-08-26
 * @Version: V1.0
 */
@Data
@ApiModel(value = "订单定时任务模型", description = "订单定时任务模型")
public class OrdersTaskVo {
	
	@TableId(type = IdType.UUID)
	@ApiModelProperty(value = "id")
	private String id;
	
	@Excel(name = "状态，0Unpaid，1Paid，2Shipping，3Offline Trading", width = 15)
	@ApiModelProperty(value = "状态，0Unpaid，1Paid，2Shipping，3Offline Trading")
	private Integer status;
	
	@ApiModelProperty("订单商品列表")
	private List<OrdersGoodsTaskVo> goods;
}
