package com.pda.myshop.service.consumer.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Date 2022/11/26 21:52
 * @Description 商品服务消费者
 * @since version-1.0
 */
@SpringBootApplication(scanBasePackages = "com.pda.myshop", exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2// 启用Swagger
@EnableDiscoveryClient// 注册进nacos
@EnableFeignClients// 启用Feign客户端
public class MyShopServiceConsumerItemApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyShopServiceConsumerItemApplication.class,args);
	}
}
