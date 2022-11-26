package com.pda.myshop.service.consumer.item.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Date 2022/11/26 22:33
 * @Description 商品Service
 * @since version-1.0
 */
@FeignClient(value = "myshop-service-provider-item")// 调用生产者服务
public interface TbItemService {

	/**
	 * @Date 2022/11/26 22:35
	 * @Description 分页查询
	 * @Param [pageNum, pageSize]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@GetMapping(value = "/item/page/{num}/{size}")
	public String page(@PathVariable("num") Integer pageNum,@PathVariable("size") Integer pageSize);
}
