package com.jikkosoft.cachesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


@SpringBootApplication(scanBasePackages = "com.jikkosoft.cachesystem")
@ComponentScan(basePackages = "com.jikkosoft.cachesystem")
@EnableRedisRepositories(basePackages = {"com.jikkosoft.cachesystem"})

public class CachesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachesystemApplication.class, args);
	}

}
