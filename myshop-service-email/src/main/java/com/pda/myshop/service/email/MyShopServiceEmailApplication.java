package com.pda.myshop.service.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Date 2022/11/26 19:52
 * @Description 邮箱服务
 * @since version-1.0
 */
@SpringBootApplication(scanBasePackages = "com.pda.myshop")
@EnableDiscoveryClient// 注册进nacos
@EnableBinding({Sink.class})// 启用绑定消息订阅者
@EnableAsync// 启用异步
public class MyShopServiceEmailApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyShopServiceEmailApplication.class,args);
	}
}
