package com.campgem.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.modules.user.entity.Address;
import com.campgem.modules.user.vo.OrdersAddressVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户地址管理
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
public interface AddressMapper extends BaseMapper<Address> {
	List<OrdersAddressVo> queryUserAddress(@Param("addressId") String addressId);
}
