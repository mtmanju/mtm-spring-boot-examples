package com.mtm.springboot.controller.impl;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtm.springboot.controller.SampleController;
import com.mtm.springboot.domain.SampleData;
import com.mtm.springboot.error.CustomError;
import com.mtm.springboot.exceptions.CustomException;
import com.mtm.springboot.service.SampleService;

import io.swagger.annotations.Api;
import reactor.core.publisher.Mono;

@RestController
@Api(tags = "Spring Boot Examples")
public class SampleControllerImpl implements SampleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleControllerImpl.class);

	@Autowired
	@Qualifier("sampleService2")
	private SampleService exampleService;

	@Override
	public Mono<ResponseEntity<Object>> saveData(@RequestBody SampleData request) {
		LOGGER.info("SpringBootExamplesControllerImpl.saveData() -->");
		return exampleService.saveData(request).map(savedData -> new ResponseEntity<Object>(savedData, HttpStatus.OK))
				.onErrorResume(exception -> {
					LOGGER.error(exception.getMessage());
					ResponseEntity<Object> response = new ResponseEntity<>(
							new CustomException(CustomError.OM_1010).getErrorMessage(),
							new CustomException(CustomError.OM_1010).getHttpStatusCode());
					if (exception instanceof CustomException)
						response = new ResponseEntity<>(((CustomException) exception).getErrorMessage(),
								((CustomException) exception).getHttpStatusCode());
					return Mono.just(response);
				}).doOnSuccess(reponse -> LOGGER.info("<-- SpringBootExamplesControllerImpl.saveData()"));
	}

	@Override
	public Mono<ResponseEntity<Object>> getCompleteData() {
		LOGGER.info("SpringBootExamplesControllerImpl.getCompleteData() -->");
		return exampleService.getData().map(savedData -> new ResponseEntity<Object>(savedData, HttpStatus.OK))
				.onErrorResume(exception -> {
					LOGGER.error(exception.getMessage());
					ResponseEntity<Object> response = new ResponseEntity<>(
							new CustomException(CustomError.OM_1010).getErrorMessage(),
							new CustomException(CustomError.OM_1010).getHttpStatusCode());
					if (exception instanceof CustomException)
						response = new ResponseEntity<>(((CustomException) exception).getErrorMessage(),
								((CustomException) exception).getHttpStatusCode());
					return Mono.just(response);
				}).doOnSuccess(reponse -> LOGGER.info("<-- SpringBootExamplesControllerImpl.getCompleteData()"));
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

}
