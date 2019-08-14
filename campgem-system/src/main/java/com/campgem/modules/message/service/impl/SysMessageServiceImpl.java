package com.campgem.modules.message.service.impl;

import com.campgem.modules.message.entity.SysMessage;
import com.campgem.common.system.base.service.impl.JeecgServiceImpl;
import com.campgem.modules.message.mapper.SysMessageMapper;
import com.campgem.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息
 * @Author: campgem
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageServiceImpl extends JeecgServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

}
