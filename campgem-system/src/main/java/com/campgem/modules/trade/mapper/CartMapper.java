package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.Cart;
import com.campgem.modules.trade.vo.GoodsCartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 购物车
 * @Author: ling
 * @Date: 2019-08-16
 * @Version: V1.0
 */
public interface CartMapper extends BaseMapper<Cart> {
	
	List<GoodsCartVo> queryCartList(@Param("uid") String uid);
}
