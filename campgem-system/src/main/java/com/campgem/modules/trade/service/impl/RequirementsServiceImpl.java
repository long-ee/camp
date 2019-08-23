package com.campgem.modules.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campgem.common.exception.JeecgBootException;
import com.campgem.common.util.BeanConvertUtils;
import com.campgem.modules.trade.dto.RequirementsQueryDto;
import com.campgem.modules.trade.dto.manage.MRequirementsQueryDto;
import com.campgem.modules.trade.entity.Requirements;
import com.campgem.modules.trade.entity.RequirementsImages;
import com.campgem.modules.trade.mapper.RequirementsMapper;
import com.campgem.modules.trade.service.IRequirementsImagesService;
import com.campgem.modules.trade.service.IRequirementsService;
import com.campgem.modules.trade.vo.RequirementsDetailVo;
import com.campgem.modules.trade.vo.RequirementsVo;
import com.campgem.modules.trade.vo.manage.MRequirementsListVo;
import com.campgem.modules.trade.vo.manage.MRequirementsVo;
import com.campgem.modules.university.service.IMemberService;
import com.campgem.modules.university.vo.MemberVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 需求
 * @Author: campgem
 * @Date:   2019-08-17
 * @Version: V1.0
 */
@Service
public class RequirementsServiceImpl extends ServiceImpl<RequirementsMapper, Requirements> implements IRequirementsService {
	
	@Resource
	private IRequirementsImagesService requirementsImagesService;
	@Resource
	private IMemberService memberService;
	
	@Override
	public IPage<RequirementsVo> queryPageList(RequirementsQueryDto queryDto, Integer pageNo, Integer pageSize) {
		LambdaQueryWrapper<Requirements> query = new LambdaQueryWrapper<>();
		if (!queryDto.getCategoryId().equals("all")) {
			query.eq(Requirements::getCategoryId, queryDto.getCategoryId());
		}
		query.eq(Requirements::getDelFlag, 0);
		int count = baseMapper.selectCount(query);
		
		Integer start = (pageNo - 1) * pageSize;
		List<RequirementsVo> list = baseMapper.queryPageList(queryDto, start, pageSize);
		
		Page<RequirementsVo> page = new Page<>(pageNo, pageSize);
		page.setRecords(list);
		page.setTotal(count);
		return page;
	}
	
	@Override
	public void incrementReviewCount(String requirementId) {
		baseMapper.incrementReviewCount(requirementId);
	}
	
	@Override
	public IPage<MRequirementsListVo> queryPageList(Page<MRequirementsListVo> page, MRequirementsQueryDto queryDto) {
		return baseMapper.queryManagePageList(page, queryDto);
	}
	
	@Override
	@Transactional
	public boolean save(MRequirementsVo saveRequirements) {
		if (saveRequirements.getImages().length > 3) {
			throw new JeecgBootException("图片最多3张");
		}
		
		Requirements requirements = BeanConvertUtils.copy(saveRequirements, Requirements.class);
		
		MemberVo member = memberService.getMemberByUserBaseId(requirements.getUid());
		requirements.setSellerName(member.getBusinessName());
		
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		requirements.setId(uuid);
		save(requirements);
		
		for (RequirementsImages image : saveRequirements.getImages()) {
			image.setRequirementId(uuid);
		}
		requirementsImagesService.saveBatch(Arrays.asList(saveRequirements.getImages()));
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean update(MRequirementsVo updateRequirements) {
		if (updateRequirements.getImages().length > 3) {
			throw new JeecgBootException("图片最多3张");
		}
		
		Requirements requirements = BeanConvertUtils.copy(updateRequirements, Requirements.class);
		updateById(requirements);
		
		for (RequirementsImages image : updateRequirements.getImages()) {
			image.setRequirementId(requirements.getId());
		}
		
		// 删除原来的
		requirementsImagesService.deleteByRequirementId(requirements.getId());
		requirementsImagesService.saveBatch(Arrays.asList(updateRequirements.getImages()));
		
		return true;
	}
	
	@Override
	public RequirementsDetailVo queryRequirementDetail(String requirementId) {
		RequirementsDetailVo detail = baseMapper.queryRequirementDetail(requirementId);
		if (detail == null) {
			throw new JeecgBootException("需求不存在");
		}
		
		return detail;
	}
}
