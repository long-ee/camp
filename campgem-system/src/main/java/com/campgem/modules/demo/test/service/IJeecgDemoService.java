package com.campgem.modules.demo.test.service;

import com.campgem.modules.demo.test.entity.JeecgDemo;
import com.campgem.common.system.base.service.JeecgService;

/**
 * @Description: jeecg 测试demo
 * @Author: campgem
 * @Date:  2018-12-29
 * @Version: V1.0
 */
public interface IJeecgDemoService extends JeecgService<JeecgDemo> {
	
	public void testTran();
	
	public JeecgDemo getByIdCacheable(String id);
}
