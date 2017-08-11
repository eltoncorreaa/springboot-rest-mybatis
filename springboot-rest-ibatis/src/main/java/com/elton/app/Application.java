package com.elton.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.elton.app.mapper")
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}