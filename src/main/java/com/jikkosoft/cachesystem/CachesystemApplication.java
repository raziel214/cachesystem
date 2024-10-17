package com.jikkosoft.cachesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@SpringBootApplication(scanBasePackages = "com.jikkosoft.cachesystem.sesion")
@ComponentScan(basePackages = "com.jikkosoft.cachesystem.sesion")
@EnableRedisRepositories(basePackages = {"com.jikkosoft.cachesystem.sesion"})

public class CachesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachesystemApplication.class, args);
	}

}
