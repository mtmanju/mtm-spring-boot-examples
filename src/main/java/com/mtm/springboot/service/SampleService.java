package com.mtm.springboot.service;

import org.springframework.http.ResponseEntity;

import com.mtm.springboot.domain.SampleData;

import reactor.core.publisher.Mono;

public interface SampleService {

	public Mono<SampleData> saveData(SampleData request);

	public Mono<ResponseEntity<Object>> getData();

	public Mono<ResponseEntity<Object>> updateData();

	public Mono<ResponseEntity<Object>> deleteData();

}
