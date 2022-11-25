package com.pda.myshop.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Date 2022/11/25 21:13
 * @Description 通用响应结果
 * @since version-1.0
 */
@Data
public abstract class AbstractBaseResult {

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)// 不包含为null的属性
	protected static class Links{
		private String self;
		private String next;
		private String last;
	}

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)// 不包含为null的属性
	protected static class DataBean<T extends AbstractBaseDomain>{
		private Long id;
		private String type;
		private T attributes;
		private T relationships;
		private Links links;
	}
}