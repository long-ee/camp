package com.campgem.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.campgem.common.api.vo.Result;
import com.campgem.common.constant.CommonConstant;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.common.util.SecurityUtils;
import com.campgem.modules.university.service.IMemberService;
import com.campgem.modules.user.entity.Address;
import com.campgem.modules.user.service.IAddressService;
import com.campgem.modules.user.vo.AddressVo;
import com.campgem.modules.user.vo.ShippingMethodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户地址管理
 * @Author: campgem
 * @Date: 2019-08-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/api/v1")
public class ProfileController {
	@Resource
	private IAddressService addressService;
	@Resource
	private IMemberService memberService;
	
	@ApiOperation(value = "用户配送方式数据", notes = "G24 运费设置")
	@GetMapping(value = "/user/shopping/methods")
	public Result<List<ShippingMethodsVo>> queryUserShoppingMethods() {
		List<ShippingMethodsVo> list = memberService.queryShoppingMethods();
		return new Result<List<ShippingMethodsVo>>().result(list);
	}
	
	@ApiOperation(value = "更新用户配送方式", notes = "G24 运费设置")
	@PutMapping(value = "/user/shopping/methods")
	public Result updateUserShoppingMethods(@RequestBody List<ShippingMethodsVo> methods) {
		for (ShippingMethodsVo vo : methods) {
			if (!CommonConstant.shippingMethodNames.contains(vo.getName())) {
				throw new JeecgBootException("未知的配送方式");
			}
		}
		boolean ok = memberService.updateUserShoppingMethods(methods);
		return ok ? Result.succ : Result.fail;
	}
	
	@ApiOperation(value = "用户地址管理", notes = "G22 地址管理 C201 选择地址")
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
