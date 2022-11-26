package com.pda.myshop.commons.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date 2022/11/26 17:28
 * @Description JSR303 Validator(Hibernate Validator)工具类
 * @since version-1.0
 */
@Component
public class BeanValidator {

	@Autowired
	private Validator validatorInstance;

	private static Validator validator;

	@PostConstruct// 扫描到时自动执行
	public void init() {
		BeanValidator.validator = validatorInstance;
	}

	/**
	 * @Date 2022/11/26 17:29
	 * @Description 调用 JSR303 的 validate 方法, 验证失败时抛出 ConstraintViolationException.
	 * @Param [validator, object, groups]
	 * @return void
	 * @since version-1.0
	 */
	private static void validateWithException(Validator validator, Object object, Class<?>... groups) throws ConstraintViolationException {
		Set constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	/**
	 * @Date 2022/11/26 17:29
	 * @Description 辅助方法, 转换 ConstraintViolationException 中的 Set<ConstraintViolations> 中为 List<message>.
	 * @Param [e]
	 * @return java.util.List<java.lang.String>
	 * @since version-1.0
	 */
	private static List<String> extractMessage(ConstraintViolationException e) {
		return extractMessage(e.getConstraintViolations());
	}

	/**
	 * @Date 2022/11/26 17:29
	 * @Description 辅助方法, 转换 Set<ConstraintViolation> 为 List<message>
	 * @Param [constraintViolations]
	 * @return java.util.List<java.lang.String>
	 * @since version-1.0
	 */
	private static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {
		List<String> errorMessages = new ArrayList<>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 * @Date 2022/11/26 17:29
	 * @Description 辅助方法, 转换 ConstraintViolationException 中的 Set<ConstraintViolations> 为 Map<property, message>.
	 * @Param [e]
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 * @since version-1.0
	 */
	private static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e) {
		return extractPropertyAndMessage(e.getConstraintViolations());
	}

	/**
	 * @Date 2022/11/26 17:30
	 * @Description 辅助方法, 转换 Set<ConstraintViolation> 为 Map<property, message>.
	 * @Param [constraintViolations]
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 * @since version-1.0
	 */
	private static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {
		Map<String, String> errorMessages = new HashMap<>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 * @Date 2022/11/26 17:30
	 * @Description 辅助方法, 转换 ConstraintViolationException 中的 Set<ConstraintViolations> 为 List<propertyPath message>.
	 * @Param [e]
	 * @return java.util.List<java.lang.String>
	 * @since version-1.0
	 */
	private static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e) {
		return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");
	}

	/**
	 * @Date 2022/11/26 17:30
	 * @Description 辅助方法, 转换 Set<ConstraintViolations> 为 List<propertyPath message>.
	 * @Param [constraintViolations]
	 * @return java.util.List<java.lang.String>
	 * @since version-1.0
	 */
	private static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations) {
		return extractPropertyAndMessageAsList(constraintViolations, " ");
	}

	/**
	 * @Date 2022/11/26 17:30
	 * @Description 辅助方法, 转换 ConstraintViolationException 中的 Set<ConstraintViolations> 为 List<propertyPath + separator + message>.
	 * @Param [e, separator]
	 * @return java.util.List<java.lang.String>
	 * @since version-1.0
	 */
	private static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
		return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
	}

	/**
	 * @Date 2022/11/26 17:30
	 * @Description 辅助方法, 转换 Set<ConstraintViolation> 为 List<propertyPath + separator + message>.
	 * @Param [constraintViolations, separator]
	 * @return java.util.List<java.lang.String>
	 * @since version-1.0
	 */
	private static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations, String separator) {
		List<String> errorMessages = new ArrayList<>();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
		}
		return errorMessages;
	}

	/**
	 * @Date 2022/11/26 17:31
	 * @Description 服务端参数有效性验证
	 * @Param [object 验证的实体对象, groups 验证组]
	 * @return java.lang.String 验证成功：返回 null；验证失败：返回错误信息
	 * @since version-1.0
	 */
	public static String validator(Object object, Class<?>... groups) {
		try {
			validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = extractMessage(ex);
			list.add(0, "数据验证失败：");

			// 封装错误消息为字符串
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				String exMsg = list.get(i);
				if (i != 0) {
					sb.append(String.format("%s. %s", i, exMsg)).append(list.size() > 1 ? "<br/>" : "");
				} else {
					sb.append(exMsg).append(list.size() > 1 ? "<br/>" : "");
				}
			}

			return sb.toString();
		}

		return null;
	}
}