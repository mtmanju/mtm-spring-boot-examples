package com.mtm.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mtm.springboot.domain.SampleData;
import com.mtm.springboot.service.SampleService;

import reactor.core.publisher.Mono;

@Service
public class SampleServiceImpl implements SampleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceImpl.class);

	@Override
	public Mono<SampleData> saveData(SampleData request) {
		LOGGER.info("SampleServiceImpl.saveData() -->");
		return Mono.just(request);
	}

	@Override
	public Mono<Object> getData() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public Mono<ResponseEntity<Object>> calculateFOCharges(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}