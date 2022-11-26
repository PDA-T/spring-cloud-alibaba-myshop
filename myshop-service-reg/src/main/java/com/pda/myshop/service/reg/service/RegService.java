package com.pda.myshop.service.reg.service;

import com.pda.myshop.commons.domain.TbUser;
import com.pda.myshop.commons.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Date 2022/11/26 20:02
 * @Description 用户注册Service
 * @since version-1.0
 */
@Service
public class RegService {

	// 配置文件内output值
	@Autowired
	private MessageChannel output;

	/**
	 * @Date 2022/11/26 20:11
	 * @Description 发送消息
	 * @Param [tbUser]
	 * @since version-1.0
	 */
	@Async// 使用异步
	public void sendEmail(TbUser tbUser){
		try {
			output.send(MessageBuilder.withPayload(MapperUtils.obj2json(tbUser)).build());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
