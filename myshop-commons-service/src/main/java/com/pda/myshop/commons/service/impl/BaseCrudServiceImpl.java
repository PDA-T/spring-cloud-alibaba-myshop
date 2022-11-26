package com.pda.myshop.commons.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pda.myshop.commons.dto.AbstractBaseDomain;
import com.pda.myshop.commons.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

/**
 * @Date 2022/11/26 17:57
 * @Description 通用业务逻辑实现
 * @since version-1.0
 */
public class BaseCrudServiceImpl<T extends AbstractBaseDomain,M extends MyMapper<T>> implements BaseCrudService<T> {

	@Autowired
	protected M mapper;

	// 实例化泛型
	private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	/**
	 * @Date 2022/11/26 18:00
	 * @Description 查询属性值是否唯一
	 * @Param [property, value]
	 * @return boolean true为唯一,false为不唯一
	 * @since version-1.0
	 */
	@Override
	public boolean unique(String property, String value) {
		// 构造器
		Example example = new Example(entityClass);
		example.createCriteria().andEqualTo(property,value);
		// 查询
		int result = mapper.selectCountByExample(example);
		if (result > 0){
			return false;
		}
		return true;
	}

	/**
	 * @Date 2022/11/26 18:56
	 * @Description 保存
	 * @Param [domain]
	 * @return T
	 * @since version-1.0
	 */
	@Override
	public T save(T domain) {
		int result = 0;
		Date currentDate = new Date();
		domain.setUpdated(currentDate);

		// 创建
		if (domain.getId() == null){
			domain.setCreated(currentDate);
			result = mapper.insertUseGeneratedKeys(domain);
		}else {
			// 更新
			result = mapper.updateByPrimaryKey(domain);
		}

		if (result > 0){
			// 成功
			return domain;
		}
		// 失败
		return null;
	}

	/**
	 * @Date 2022/11/26 21:32
	 * @Description 分页查询
	 * @Param [domain, pageNum, pageSize]
	 * @return com.github.pagehelper.PageInfo<T>
	 * @since version-1.0
	 */
	@Override
	public PageInfo<T> page(T domain, Integer pageNum, Integer pageSize) {
		// 条件构造
		Example example = new Example(entityClass);
		example.createCriteria().andEqualTo(domain);
		// 分页
		PageHelper.startPage(pageNum,pageSize);
		PageInfo<T> pageInfo = new PageInfo<T>(mapper.selectByExample(example));
		return pageInfo;
	}
}
