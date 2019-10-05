package com.campgem.modules.order.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderGoodsOrServiceStatusVo implements Serializable {
	private String name;
	
	private Integer status;
}
