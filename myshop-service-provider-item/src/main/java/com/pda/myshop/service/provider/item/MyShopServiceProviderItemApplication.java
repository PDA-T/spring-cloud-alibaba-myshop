package com.pda.myshop.service.provider.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Date 2022/11/26 21:17
 * @Description 商品服务提供者
 * @since version-1.0
 */
@SpringBootApplication(scanBasePackages = "com.pda.myshop")
@EnableDiscoveryClient// 注册进nacos
@MapperScan(basePackages = "com.pda.myshop.commons.mapper")// 扫描mapper
@EnableSwagger2// 启用Swagger
public class MyShopServiceProviderItemApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyShopServiceProviderItemApplication.class,args);
	}
}
