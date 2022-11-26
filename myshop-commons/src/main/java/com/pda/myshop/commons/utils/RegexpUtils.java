package com.pda.myshop.commons.utils;

/**
 * @Date 2022/11/26 17:40
 * @Description 正则表达式工具类
 * @since version-1.0
 */
public class RegexpUtils {
	/**
	 * 验证手机号
	 */
	public static final String PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * 验证邮箱地址
	 */
	public static final String EMAIL = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";

	/**
	 * @Date 2022/11/26 17:41
	 * @Description 验证手机号
	 * @Param [phone]
	 * @return boolean
	 * @since version-1.0
	 */
	public static boolean checkPhone(String phone) {
		return phone.matches(PHONE);
	}

	/**
	 * @Date 2022/11/26 17:41
	 * @Description 验证邮箱
	 * @Param [email]
	 * @return boolean
	 * @since version-1.0
	 */
	public static boolean checkEmail(String email) {
		return email.matches(EMAIL);
	}
}