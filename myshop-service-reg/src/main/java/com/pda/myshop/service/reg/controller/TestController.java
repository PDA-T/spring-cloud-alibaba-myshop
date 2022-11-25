package com.pda.myshop.service.reg.controller;

import com.google.common.collect.Lists;
import com.pda.myshop.commons.domain.TbUser;
import com.pda.myshop.commons.dto.AbstractBaseResult;
import com.pda.myshop.commons.dto.BaseResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Date 2022/11/25 21:03
 * @Description 测试
 * @since version-1.0
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

	// 可动态刷新参数
	@Autowired
	private ConfigurableApplicationContext applicationContext;

	/**
	 * @Date 2022/11/25 22:04
	 * @Description 单笔数据测试
	 * @Param [request, id]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	@GetMapping(value = "/records/{id}")
	public AbstractBaseResult getById(HttpServletRequest request, @PathVariable("id") Long id){
		TbUser tbUser = new TbUser();
		tbUser.setId(1L);
		tbUser.setUsername("1");
		if (id == 1){
			return BaseResultFactory.getInstance().build(request.getRequestURI(),tbUser);
		}else {
			return BaseResultFactory.build(HttpStatus.UNAUTHORIZED.value(),"类型错误","id为1",applicationContext.getEnvironment().getProperty("logging.level.com.pda.myshop"));
		}
	}

	/**
	 * @Date 2022/11/25 22:05
	 * @Description 多笔数据测试
	 * @Param [request, id]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	@GetMapping(value = "/records")
	public AbstractBaseResult getList(HttpServletRequest request){
		TbUser tbUser1 = new TbUser();
		tbUser1.setId(1L);
		tbUser1.setUsername("1");

		TbUser tbUser2 = new TbUser();
		tbUser2.setId(2L);
		tbUser2.setUsername("2");

		List<TbUser> tbUsers = Lists.newArrayList();
		tbUsers.add(tbUser1);
		tbUsers.add(tbUser2);
		return BaseResultFactory.getInstance().build(request.getRequestURI(),2,10,tbUsers);
	}
}