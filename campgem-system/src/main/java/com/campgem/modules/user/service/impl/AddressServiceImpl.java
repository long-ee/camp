package com.campgem.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.modules.user.entity.Address;
import com.campgem.modules.user.mapper.AddressMapper;
import com.campgem.modules.user.service.IAddressService;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户地址管理
 * @Author: campgem
 * @Date:   2019-08-18
 * @Version: V1.0
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
