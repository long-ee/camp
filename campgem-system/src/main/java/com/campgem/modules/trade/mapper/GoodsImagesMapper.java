package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.GoodsImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品图片
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface GoodsImagesMapper extends BaseMapper<GoodsImages> {
	public List<GoodsImages> queryGoodsImages(@Param("goodsId") String goodsId);
}
