package com.campgem.modules.trade.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campgem.modules.trade.entity.GoodsEvaluation;
import com.campgem.modules.trade.vo.GoodsEvaluationVo;
import com.campgem.modules.user.dto.OrdersEvaluationDto;

/**
 * @Description: 商品评价
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface IGoodsEvaluationService extends IService<GoodsEvaluation> {
	
	IPage<GoodsEvaluationVo> queryGoodsEvaluationPageList(String goodsId, Integer pageNo, Integer pageSize);
	
	boolean evaluation(String uid, OrdersEvaluationDto dto);
}
