package com.pda.myshop.commons.web;

import com.pda.myshop.commons.dto.AbstractBaseDomain;
import com.pda.myshop.commons.dto.AbstractBaseResult;
import com.pda.myshop.commons.dto.BaseResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Date 2022/11/26 18:21
 * @Description 通用控制器
 * @since version-1.0
 */
public abstract class AbstractBaseController<T extends AbstractBaseDomain> {

	private static final String LOGGING_LEVEL_MY_SHOP = "logging.level.com.pda.myshop";

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	// 动态加载配置
	@Autowired
	private ConfigurableApplicationContext applicationContext;

	/**
	 * @Date 2022/11/26 18:26
	 * @Description 初始化
	 * @Param [request, response]
	 * @return void
	 * @since version-1.0
	 */
	@ModelAttribute
	public void initReqAndRes(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
	}

	/**
	 * @Date 2022/11/26 18:27
	 * @Description 构建单笔数据
	 * @Param [self, attribute]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	protected AbstractBaseResult success(String self,T attribute){
		return BaseResultFactory.getInstance(response).build(self,attribute);
	}

	/**
	 * @Date 2022/11/26 18:28
	 * @Description 构建多笔数据
	 * @Param [self, next, last, attribute]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	protected AbstractBaseResult success(String self, Integer next, Integer last, List<T> attribute){
		return BaseResultFactory.getInstance(response).build(self,next,last,attribute);
	}

	/**
	 * @Date 2022/11/26 18:44
	 * @Description 401重载
	 * @Param [title, detail]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	protected AbstractBaseResult error(String title,String detail){
		return error(HttpStatus.UNAUTHORIZED.value(),title,detail);
	}

	/**
	 * @Date 2022/11/26 18:29
	 * @Description 构建失败数据
	 * @Param [code, title, detail]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	protected AbstractBaseResult error(Integer code,String title,String detail){
		return BaseResultFactory.getInstance(response).build(code,title,detail,applicationContext.getEnvironment().getProperty(LOGGING_LEVEL_MY_SHOP));
	}
}
