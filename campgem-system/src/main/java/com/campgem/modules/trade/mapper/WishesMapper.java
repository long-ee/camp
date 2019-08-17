package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.entity.Wishes;
import com.campgem.modules.trade.vo.WishesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 心愿
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
public interface WishesMapper extends BaseMapper<Wishes> {
	
	List<WishesVo> queryWishesPageList(@Param("uid") String uid,
	                                   @Param("start") Integer start,
	                                   @Param("pageSize") Integer pageSize);
}
