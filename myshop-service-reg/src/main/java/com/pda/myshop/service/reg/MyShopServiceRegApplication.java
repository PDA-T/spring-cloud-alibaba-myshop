package com.pda.myshop.service.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Date 2022/11/22 18:40
 * @Description 用户注册
 * @since version-1.0
 */
@SpringBootApplication(scanBasePackages = "com.pda.myshop")
@EnableDiscoveryClient// 注册进nacos
@MapperScan(basePackages = "com.pda.myshop.commons.mapper")// 扫描mapper
@EnableBinding({Source.class})// 启用绑定消息提供者
@EnableAsync// 启用异步
@EnableSwagger2// 启用Swagger
public class MyShopServiceRegApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyShopServiceRegApplication.class,args);
	}
}
