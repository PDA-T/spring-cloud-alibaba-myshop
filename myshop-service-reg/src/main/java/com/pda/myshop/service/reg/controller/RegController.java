package com.pda.myshop.service.reg.controller;

import com.pda.myshop.commons.domain.TbUser;
import com.pda.myshop.commons.dto.AbstractBaseResult;
import com.pda.myshop.commons.service.TbUserService;
import com.pda.myshop.commons.validator.BeanValidator;
import com.pda.myshop.commons.web.AbstractBaseController;
import com.pda.myshop.service.reg.service.RegService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Date 2022/11/22 19:08
 * @Description 用户注册Controller
 * @since version-1.0
 */
@RestController
@RequestMapping(value = "reg")
public class RegController extends AbstractBaseController<TbUser> {

	// 用户
	@Autowired
	private TbUserService tbUserService;

	// 邮件
	@Autowired
	private RegService regService;

	/**
	 * @Date 2022/11/22 19:13
	 * @Description id查询用户
	 * @Param [id]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@GetMapping(value = {"{id}"})
	public String reg(@PathVariable Long id){
		TbUser tbUser = tbUserService.selectByPrimaryKey(id);
		return tbUser.getUsername();
	}

	/**
	 * @Date 2022/11/26 17:42
	 * @Description 用户注册
	 * @Param [tbUser]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	@PostMapping(value = "")
	@ApiOperation(value = "用户注册",notes = "用户名邮箱不能为空")
	public AbstractBaseResult reg(@ApiParam(name = "tbUser",value = "用户模型") TbUser tbUser){
		// 数据校验
		String message = BeanValidator.validator(tbUser);
		if (StringUtils.isNotBlank(message)){
			return error(message,null);
		}

		// 验证用户名是否重复
		if (!tbUserService.unique("username",tbUser.getUsername())){
			return error("用户名重复",null);
		}

		// 验证邮箱是否重复
		if (!tbUserService.unique("email",tbUser.getEmail())){
			return error("邮箱重复",null);
		}

		// 加密
		tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
		// 注册用户
		TbUser user = tbUserService.save(tbUser);
		if (user != null){
			regService.sendEmail(user);
			response.setStatus(HttpStatus.CREATED.value());
			return success(request.getRequestURI(),user);
		}
		return error("注册失败",null);
	}
}