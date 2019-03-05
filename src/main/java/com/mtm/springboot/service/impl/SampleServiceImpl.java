package com.mtm.springboot.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mtm.springboot.domain.SampleData;
import com.mtm.springboot.service.SampleService;

import reactor.core.publisher.Mono;

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public Mono<SampleData> saveData(SampleData request) {
		// TODO Auto-generated method stub
		return Mono.just(request);
	}

	@Override
	public Mono<ResponseEntity<Object>> getData() {
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

}