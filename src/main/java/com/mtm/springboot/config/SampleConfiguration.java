package com.mtm.springboot.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.function.client.WebClient;

import com.mtm.springboot.exceptions.CustomException;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Getter
@Slf4j
public class SampleConfiguration {

	@Value("${sample.baseurl}")
	private String baseUrl;

	@Bean("sampleWebClient")
	@Lazy
	public WebClient getSampleWebClient() throws CustomException {
		log.info("SampleConfiguration.getWebClient() -->");
		WebClient webClient = WebClient.builder().baseUrl(baseUrl)
				.defaultHeader("Authorization",
						"Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8)))
				.build();
		log.info("<-- SampleConfiguration.getWebClient()");
		return webClient;
	}
}
