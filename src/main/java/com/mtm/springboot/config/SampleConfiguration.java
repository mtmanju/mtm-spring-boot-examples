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
public class SampleConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleConfiguration.class);

	@Value("${sample.baseurl}")
	private String baseUrl;

	@Bean("sampleWebClient")
	@Lazy
	public WebClient getSampleWebClient() throws CustomException {
		LOGGER.info("SampleConfiguration.getWebClient() -->");
		WebClient webClient = WebClient.builder().baseUrl(baseUrl)
				.defaultHeader("Authorization",
						"Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8)))
				.build();
		LOGGER.info("<-- SampleConfiguration.getWebClient()");
		return webClient;
	}
}
