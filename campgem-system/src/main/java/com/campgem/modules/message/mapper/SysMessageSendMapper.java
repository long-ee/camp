package com.campgem.modules.message.mapper;

import java.util.List;

import com.campgem.modules.message.entity.SysMessageSend;
import com.campgem.modules.message.dto.MessageVo;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description: 用户通告阅读标记表
 * @Author: campgem
 * @Date:  2019-02-21
 * @Version: V1.0
 */
public interface SysMessageSendMapper extends BaseMapper<SysMessageSend> {
	/**
	 * 获取我的消息
	 * @param page
	 * @param messageVo
	 * @return
	 */
	public List<MessageVo> queryUserMessageList(Page page, @Param("queryObj") MessageVo messageVo);

}
