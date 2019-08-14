package com.campgem.modules.message.service;

import java.util.List;

import com.campgem.modules.message.entity.SysMessageTemplate;
import com.campgem.common.system.base.service.JeecgService;

/**
 * @Description: 消息模板
 * @Author: campgem
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends JeecgService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
