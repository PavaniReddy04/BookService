package com.epam.book;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RefreshScope
@EnableEurekaClient
public class BookApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookApplication.class);

	public static void main(String[] args) {
		log.info("Started the Book application");
		SpringApplication.run(BookApplication.class, args);
	}

	@Bean
	public ModelMapper getMapperBean() {
		return new ModelMapper();
	}
}
