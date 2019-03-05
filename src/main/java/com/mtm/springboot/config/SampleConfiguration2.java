package com.mtm.springboot.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.function.client.WebClient;

import com.mtm.springboot.exceptions.CustomException;

import lombok.Getter;

@Configuration
@Getter
public class SampleConfiguration2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleConfiguration2.class);

	@Value("${sample.baseurl2}")
	private String baseUrl2;

	@Bean("sampleWebClient2")
	@Lazy
	public WebClient sampleWebClient() throws CustomException {
		LOGGER.info("SampleConfiguration.getWebClient() -->");
		WebClient webClient = WebClient.builder().baseUrl(baseUrl2)
				.defaultHeader("Authorization",
						"Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8)))
				.build();
		LOGGER.info("<-- SampleConfiguration.getWebClient()");
		return webClient;
	}
}
