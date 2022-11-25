package com.pda.myshop.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date 2022/11/25 22:32
 * @Description 通用领域模型
 * @since version-1.0
 */
@Data
public abstract class AbstractBaseDomain implements Serializable {

	private Long id;
}
