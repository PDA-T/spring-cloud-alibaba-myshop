package com.pda.myshop.service.consumer.item.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageInfo;
import com.pda.myshop.commons.domain.TbItem;
import com.pda.myshop.commons.dto.AbstractBaseResult;
import com.pda.myshop.commons.utils.MapperUtils;
import com.pda.myshop.commons.web.AbstractBaseController;
import com.pda.myshop.service.consumer.item.service.TbItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2022/11/26 22:37
 * @Description 商品消费者Controller
 * @since version-1.0
 */
@RestController
@RequestMapping(value = "/item")
public class TbItemController extends AbstractBaseController<TbItem> {

	// 商品
	@Autowired
	private TbItemService tbItemService;

	/**
	 * @Date 2022/11/26 22:41
	 * @Description 分页查询
	 * @Param [num, size]
	 * @return com.pda.myshop.commons.dto.AbstractBaseResult
	 * @since version-1.0
	 */
	@GetMapping(value = "/page/{num}/{size}")
	@ApiOperation(value = "商品分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "num",value = "页码",required = true,paramType = "path",dataType = "int"),
			@ApiImplicitParam(name = "size",value = "笔数",required = true,paramType = "path",dataType = "int")
	})
	public AbstractBaseResult page(@PathVariable("num") Integer num,@PathVariable("size") Integer size){
		String json = tbItemService.page(num, size);
		try {
			JavaType javaType = MapperUtils.getCollectionType(PageInfo.class, TbItem.class);
			PageInfo<TbItem> pageInfo = MapperUtils.getInstance().readValue(json,javaType);
			return success(request.getRequestURI(),pageInfo.getNextPage(),pageInfo.getPages(),pageInfo.getList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
