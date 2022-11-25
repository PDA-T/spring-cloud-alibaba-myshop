package com.pda.myshop.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date 2022/11/25 21:00
 * @Description 请求失败
 * @since version-1.0
 */
@Data
@AllArgsConstructor// 有参构造
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)// 不为null显示
public class ErrorResult extends AbstractBaseResult{

	// 状态码
	private Integer code;

	// 标题
	private String title;

	// 详情
	private String detail;
}
