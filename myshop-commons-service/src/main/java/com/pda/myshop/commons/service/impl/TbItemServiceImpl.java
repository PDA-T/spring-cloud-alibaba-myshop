package com.pda.myshop.commons.service.impl;

import com.pda.myshop.commons.domain.TbItem;
import com.pda.myshop.commons.mapper.TbItemMapper;
import com.pda.myshop.commons.service.TbItemService;
import org.springframework.stereotype.Service;

/**
 * @Date 2022/11/26 21:29
 * @Description 商品Service实现
 * @since version-1.0
 */
@Service
public class TbItemServiceImpl extends BaseCrudServiceImpl<TbItem, TbItemMapper> implements TbItemService {
}
