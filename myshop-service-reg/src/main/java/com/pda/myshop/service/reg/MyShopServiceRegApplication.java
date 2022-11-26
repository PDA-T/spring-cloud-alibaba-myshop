package com.pda.myshop.service.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Date 2022/11/22 18:40
 * @Description 用户注册
 * @since version-1.0
 */
@SpringBootApplication(scanBasePackages = "com.pda.myshop")
@EnableDiscoveryClient// 注册进nacos
@MapperScan(basePackages = "com.pda.myshop.commons.mapper")// 扫描mapper
public class MyShopServiceRegApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyShopServiceRegApplication.class,args);
	}
}
