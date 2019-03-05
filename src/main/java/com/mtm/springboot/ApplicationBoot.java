package com.mtm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApplicationBoot {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBoot.class, args);
	}

	/**
	 * Sets the api's meta info as included in the json Resource Listing response.
	 */
	@Bean
	public Docket apiInfo() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.mtm.springboot")).build();
	}

}
