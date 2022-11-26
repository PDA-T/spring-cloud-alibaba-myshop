package com.pda.myshop.commons.service.impl;

import com.pda.myshop.commons.domain.TbUser;
import com.pda.myshop.commons.mapper.TbUserMapper;
import com.pda.myshop.commons.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date 2022/11/26 18:07
 * @Description 用户Service实现
 * @since version-1.0
 */
@Service
public class TbUserServiceImpl extends BaseCrudServiceImpl<TbUser, TbUserMapper> implements TbUserService {

	// 用户
	@Autowired
	private TbUserMapper tbUserMapper;

	/**
	 * @Date 2022/11/26 18:13
	 * @Description id查询用户
	 * @Param [id]
	 * @return com.pda.myshop.commons.domain.TbUser
	 * @since version-1.0
	 */
	@Override
	public TbUser selectByPrimaryKey(Long id) {
		return tbUserMapper.selectByPrimaryKey(id);
	}
}
