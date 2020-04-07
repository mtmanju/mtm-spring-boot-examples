package com.mtm.springboot.controller.impl;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mtm.springboot.controller.SampleController;
import com.mtm.springboot.domain.SampleData;
import com.mtm.springboot.error.CustomError;
import com.mtm.springboot.exceptions.CustomException;
import com.mtm.springboot.service.SampleService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Api(tags = "Spring Boot Examples")
@Slf4j
public class SampleControllerImpl implements SampleController {

	@Autowired
	@Qualifier("sampleService2")
	private SampleService sampleService2;

	@Override
	public Mono<ResponseEntity<Object>> saveData(@RequestBody SampleData request) {
		log.info("SpringBootExamplesControllerImpl.saveData() -->");
		return sampleService2.saveData(request).map(savedData -> new ResponseEntity<Object>(savedData, HttpStatus.OK))
				.onErrorResume(exception -> {
					log.error(exception.getMessage());
					ResponseEntity<Object> response = new ResponseEntity<>(
							new CustomException(CustomError.OM_1010).getErrorMessage(),
							new CustomException(CustomError.OM_1010).getHttpStatusCode());
					if (exception instanceof CustomException)
						response = new ResponseEntity<>(((CustomException) exception).getErrorMessage(),
								((CustomException) exception).getHttpStatusCode());
					return Mono.just(response);
				}).doOnSuccess(reponse -> log.info("<-- SpringBootExamplesControllerImpl.saveData()"));
	}

	@Override
	public Mono<ResponseEntity<Object>> getCompleteData() {
		log.info("SpringBootExamplesControllerImpl.getCompleteData() -->");
		return sampleService2.getData().map(savedData -> new ResponseEntity<Object>(savedData, HttpStatus.OK))
				.onErrorResume(exception -> {
					log.error(exception.getMessage());
					ResponseEntity<Object> response = new ResponseEntity<>(
							new CustomException(CustomError.OM_1010).getErrorMessage(),
							new CustomException(CustomError.OM_1010).getHttpStatusCode());
					if (exception instanceof CustomException)
						response = new ResponseEntity<>(((CustomException) exception).getErrorMessage(),
								((CustomException) exception).getHttpStatusCode());
					return Mono.just(response);
				}).doOnSuccess(reponse -> log.info("<-- SpringBootExamplesControllerImpl.getCompleteData()"));
	}

	@Override
	public Mono<ResponseEntity<Object>> getData(@PathParam(value = "identifier") String identifier) {
		return null;
	}

	@Override
	public Mono<ResponseEntity<Object>> updateData(@PathParam(value = "identifier") String identifier,
			@RequestBody SampleData request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ResponseEntity<Object>> deleteData(@PathParam(value = "identifier") String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ResponseEntity<Object>> calculateFuturesOptionsCharges(MultipartFile file) {
		log.info("SpringBootExamplesControllerImpl.saveData() -->");
		return sampleService2.calculateFOCharges(file)
				.map(savedData -> new ResponseEntity<Object>(savedData, HttpStatus.OK)).onErrorResume(exception -> {
					log.error(exception.getMessage());
					ResponseEntity<Object> response = new ResponseEntity<>(
							new CustomException(CustomError.OM_1010).getErrorMessage(),
							new CustomException(CustomError.OM_1010).getHttpStatusCode());
					if (exception instanceof CustomException)
						response = new ResponseEntity<>(((CustomException) exception).getErrorMessage(),
								((CustomException) exception).getHttpStatusCode());
					return Mono.just(response);
				}).doOnSuccess(reponse -> log.info("<-- SpringBootExamplesControllerImpl.saveData()"));

	}

}
