package com.pda.myshop.commons.service;

import com.pda.myshop.commons.dto.AbstractBaseDomain;

/**
 * @Date 2022/11/26 17:55
 * @Description 通用业务逻辑
 * @since version-1.0
 */
public interface BaseCrudService<T extends AbstractBaseDomain> {

	/**
	 * @Date 2022/11/26 17:56
	 * @Description 查询属性值是否唯一
	 * @Param [property, value]
	 * @return boolean true为唯一,false为不唯一
	 * @since version-1.0
	 */
	default boolean unique(String property,String value){
		return false;
	}

	/**
	 * @Date 2022/11/26 18:56
	 * @Description 保存
	 * @Param [domain]
	 * @return T
	 * @since version-1.0
	 */
	default T save(T domain){
		return null;
	}
}
