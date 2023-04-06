package com.jpm.boot_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBoot02JPARestUser {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02JPARestUser.class,
				args);//boot-strapping class of spring boot
		}

}