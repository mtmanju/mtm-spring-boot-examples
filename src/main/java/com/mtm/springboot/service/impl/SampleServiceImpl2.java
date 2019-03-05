package com.mtm.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mtm.springboot.config.SampleConfiguration;
import com.mtm.springboot.domain.SampleData;
import com.mtm.springboot.service.SampleService;

import reactor.core.publisher.Mono;

//Primary
@Service("sampleService2")
public class SampleServiceImpl2 implements SampleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceImpl2.class);

	@Autowired
	private SampleConfiguration config;

	@Autowired
	@Qualifier("sampleWebClient")
	private WebClient webClient;

	@Override
	public Mono<SampleData> saveData(SampleData request) {
		LOGGER.info("SampleServiceImpl2.saveData() -->");

		// TODO Auto-generated method stub
		return Mono.just(SampleData.builder().description("Hello").build());
	}

	@Override
	public Mono<Object> getData() {
		LOGGER.info("SampleServiceImpl2.getData() -->");
		LOGGER.info("Base URL:" + config.getBaseUrl());

		return webClient.get().uri(uriBuilder -> uriBuilder.path("/mtm/examples/123").build()).exchange()
				.flatMap(result -> {
					LOGGER.info("<-- SampleServiceImpl2.getData()");
					return result.bodyToMono(Object.class);
				});
	}

	@Override
	public Mono<ResponseEntity<Object>> updateData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ResponseEntity<Object>> deleteData() {
		// TODO Auto-generated method stub
		return null;
	}

}