package com.campgem.modules.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.vo.RequirementsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
public interface RequirementsMapper extends BaseMapper<Requirements> {
	
	List<RequirementsVo> queryPageList(@Param("query") RequirementsQueryDto queryDto,
	                                   @Param("start") Integer start,
	                                   @Param("pageSize") Integer pageSize);
}
