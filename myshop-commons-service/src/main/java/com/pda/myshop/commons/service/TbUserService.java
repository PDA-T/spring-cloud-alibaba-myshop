package com.pda.myshop.commons.service;

import com.pda.myshop.commons.domain.TbUser;

/**
 * @Date 2022/11/26 18:06
 * @Description 用户Service
 * @since version-1.0
 */
public interface TbUserService extends BaseCrudService<TbUser>{

	/**
	 * @Date 2022/11/26 18:12
	 * @Description id查询用户
	 * @Param [id]
	 * @return com.pda.myshop.commons.domain.TbUser
	 * @since version-1.0
	 */
	public TbUser selectByPrimaryKey(Long id);
}
