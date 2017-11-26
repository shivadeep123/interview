package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan("com.example.demo")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringBootEmpserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmpserviceApplication.class, args);
	}
}
