package com.campgem.modules.university.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campgem.common.api.vo.IdentifyInfo;
import com.campgem.modules.user.entity.UserBase;
import com.campgem.modules.university.vo.UserBaseVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 用户基础信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
public interface UserBaseMapper extends BaseMapper<UserBase> {

    /**
     * 根据认证信息查询
     * @param identifyInfo
     * @return
     */
    UserBaseVo getUserByIdentifyInfo(@Param("queryObj") IdentifyInfo identifyInfo);

}
