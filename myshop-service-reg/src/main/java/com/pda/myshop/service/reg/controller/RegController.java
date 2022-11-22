package com.pda.myshop.service.reg.controller;

import com.pda.myshop.commons.domain.TbUser;
import com.pda.myshop.commons.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2022/11/22 19:08
 * @Description 用户注册Controller
 * @since version-1.0
 */
@RestController
@RequestMapping(value = "reg")
public class RegController {
	// 用户
	@Autowired
	private TbUserMapper tbUserMapper;

	/**
	 * @Date 2022/11/22 19:13
	 * @Description id查询用户
	 * @Param [id]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@GetMapping(value = {"{id}"})
	public String reg(@PathVariable Long id){
		TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
		return tbUser.getUsername();
	}
}