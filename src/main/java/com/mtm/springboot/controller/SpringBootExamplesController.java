package com.mtm.springboot.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mtm.springboot.domain.SampleData;

import reactor.core.publisher.Mono;

@RequestMapping(value = "/mtm/examples")
public interface SpringBootExamplesController {

	@PostMapping()
	public Mono<ResponseEntity<Object>> saveData(@RequestBody SampleData request);

	@GetMapping()
	public Mono<ResponseEntity<Object>> getCompleteData();

	@GetMapping("/{identifier}")
	public Mono<ResponseEntity<Object>> getData(@PathParam(value = "identifier") String identifier);

	@PutMapping("/{identifier}")
	public Mono<ResponseEntity<Object>> updateData(@PathParam(value = "identifier") String identifier,
			@RequestBody SampleData request);

	@DeleteMapping("/{identifier}")
	public Mono<ResponseEntity<Object>> deleteData(@PathParam(value = "identifier") String identifier);

}
