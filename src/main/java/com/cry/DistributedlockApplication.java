package com.cry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DistributedlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedlockApplication.class, args);
	}

}
