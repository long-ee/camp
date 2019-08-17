package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.GoodsEvaluation;
import com.campgem.modules.trade.vo.GoodsEvaluationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品评价
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface GoodsEvaluationMapper extends BaseMapper<GoodsEvaluation> {
	
	List<GoodsEvaluationVo> queryGoodsEvaluationPageList(@Param("goodsId") String goodsId,
	                                                     @Param("start") Integer start,
	                                                     @Param("pageSize") Integer pageSize);
}
