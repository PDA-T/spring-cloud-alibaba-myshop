package com.pda.myshop.service.provider.item.controller;

import com.github.pagehelper.PageInfo;
import com.pda.myshop.commons.domain.TbItem;
import com.pda.myshop.commons.service.TbItemService;
import com.pda.myshop.commons.web.AbstractBaseController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Date 2022/11/26 21:34
 * @Description 商品Controller
 * @since version-1.0
 */
@RestController
@RequestMapping(value = "/item")
public class TbItemController extends AbstractBaseController<TbItem> {

	// 商品
	@Autowired
	private TbItemService tbItemService;

	/**
	 * @Date 2022/11/26 21:40
	 * @Description 商品分页查询
	 * @Param [num, size]
	 * @return com.github.pagehelper.PageInfo<com.pda.myshop.commons.domain.TbItem>
	 * @since version-1.0
	 */
	@GetMapping(value = "/page/{num}/{size}")
	@ApiOperation(value = "分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "num",value = "页码",required = true,paramType = "path",dataType = "int"),
			@ApiImplicitParam(name = "size",value = "笔数",required = true,paramType = "path",dataType = "int")
	})
	public PageInfo<TbItem> page(@PathVariable("num") Integer num,
								 @PathVariable("size") Integer size){
		PageInfo<TbItem> page = tbItemService.page(null, num, size);
		return page;
	}
}
