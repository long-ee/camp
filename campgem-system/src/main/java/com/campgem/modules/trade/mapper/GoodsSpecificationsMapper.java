package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.GoodsSpecifications;
import com.campgem.modules.trade.vo.GoodsSpecificationsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品规格
 * @Author: ling
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface GoodsSpecificationsMapper extends BaseMapper<GoodsSpecifications> {
	public List<GoodsSpecificationsVo> queryGoodsSpecifications(@Param("goodsId") String goodsId);
}
