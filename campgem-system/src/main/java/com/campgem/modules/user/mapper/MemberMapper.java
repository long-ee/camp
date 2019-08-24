package com.campgem.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.service.vo.BusinessDetailVo;
import com.campgem.modules.user.entity.Member;
import com.campgem.modules.user.vo.MemberVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户扩展信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface MemberMapper extends BaseMapper<Member> {
	
	String queryShoppingMethods(@Param("uid") String uid);
	
	boolean updateUserShoppingMethods(@Param("vos") List<ShippingMethodsVo> vos, @Param("uid") String uid);
	
	BusinessDetailVo queryBusinessDetail(@Param("uid") String uid);
	
	MemberVo getMemberByUserBaseId(@Param("id") String userBaseId);
}
