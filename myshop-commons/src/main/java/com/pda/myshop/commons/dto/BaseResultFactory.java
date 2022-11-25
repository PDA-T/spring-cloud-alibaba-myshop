package com.pda.myshop.commons.dto;

import java.util.List;

/**
 * @Date 2022/11/25 21:19
 * @Description 通用响应结构工厂
 * @since version-1.0
 */
public class BaseResultFactory<T extends AbstractBaseDomain> {

	// 日志级别
	private static final String LOGGER_LEVEL_DEBUG = "DEBUG";

	private static BaseResultFactory baseResultFactory;

	private BaseResultFactory(){
	}

	public static BaseResultFactory getInstance(){
		if (baseResultFactory == null){
			synchronized (BaseResultFactory.class){
				if (baseResultFactory == null){
					baseResultFactory = new BaseResultFactory();
				}
			}
		}
		return baseResultFactory;
	}

	/**
	 * @Date 2022/11/25 21:24
	 * @Description 构建单笔数据
	 * @Param [self]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	public AbstractBaseResult build(String self,T attributes){
		return new SuccessResult(self,attributes);
	}

	/**
	 * @Date 2022/11/25 21:25
	 * @Description 构建多笔数据
	 * @Param [self, next, last]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	public AbstractBaseResult build(String self, Integer next, Integer last, List<T> attributes){
		return new SuccessResult(self,next,last,attributes);
	}

	/**
	 * @Date 2022/11/25 22:11
	 * @Description 构建请求错误的响应
	 * @Param [code, title, detail, level 日志级别为debug时显示详情]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	public static AbstractBaseResult build(Integer code,String title,String detail,String level){
		if (LOGGER_LEVEL_DEBUG.equals(level)){
			return new ErrorResult(code,title,detail);
		}else {
			return new ErrorResult(code,title,null);
		}
	}
}
