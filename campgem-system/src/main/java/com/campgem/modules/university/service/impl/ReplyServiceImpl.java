package com.campgem.modules.university.service.impl;

import com.campgem.modules.university.entity.Reply;
import com.campgem.modules.university.mapper.ReplyMapper;
import com.campgem.modules.university.service.IReplyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 话题回复信息
 * @Author: campgem
 * @Date:   2019-08-05
 * @Version: V1.0
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

}
