package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.campgem.common.api.vo.Result;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.user.entity.Address;
import com.campgem.modules.user.service.IAddressService;
import com.campgem.modules.user.vo.AddressVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户地址管理
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "用户地址管理")
@RestController
@RequestMapping("/api/v1")
public class AddressController {
	@Resource
	private IAddressService addressService;
	
	@ApiOperation(value = "用户地址管理-分页列表查询", notes = "G22 地址管理 C201 选择地址")
	@GetMapping(value = "/user/address")
	public Result<List<AddressVo>> queryUserAddressList() {
		LambdaQueryWrapper<Address> query = new LambdaQueryWrapper<>();
		query.eq(Address::getUid, SecurityUtils.getCurrentUserUid());
		query.eq(Address::getDelFlag, 0);
		query.orderByDesc(Address::getCreateTime);
		List<Address> list = addressService.list(query);
		return new Result<List<AddressVo>>().result(BeanConvertUtils.copyList(list, AddressVo.class));
	}
	
	@ApiOperation(value = "用户默认地址，result空表示没有默认地址", notes = "C20 确认下单")
	@GetMapping(value = "/user/address/default")
	public Result<AddressVo> queryUserDefaultAddress() {
		LambdaQueryWrapper<Address> query = new LambdaQueryWrapper<>();
		query.eq(Address::getUid, SecurityUtils.getCurrentUserUid());
		query.eq(Address::getDelFlag, 0);
		query.eq(Address::getIsDefault, 1);
		Address address = addressService.getOne(query);
		if (address == null) {
			return new Result<AddressVo>().result(null);
		}
		return new Result<AddressVo>().result(BeanConvertUtils.copy(address, AddressVo.class));
	}
}
