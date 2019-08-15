package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.dto.GoodsQueryDto;
import com.campgem.modules.trade.entity.Goods;
import com.campgem.modules.trade.vo.GoodsListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 商品信息
 * @Author: ling
 * @Date: 2019-08-15
 * @Version: V1.0
 */
public interface GoodsMapper extends BaseMapper<Goods> {
	
	List<GoodsListVo> queryPageList(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("query") GoodsQueryDto query);
}
