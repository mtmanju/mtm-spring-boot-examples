package com.mtm.springboot.error;

import org.springframework.http.HttpStatus;

public enum CustomError {
	
	OM_1000(HttpStatus.INTERNAL_SERVER_ERROR),
	OM_1001(HttpStatus.INTERNAL_SERVER_ERROR),
	OM_1002(HttpStatus.BAD_REQUEST),
	OM_1003(HttpStatus.BAD_REQUEST),
	OM_1004(HttpStatus.BAD_REQUEST),
	OM_1005(HttpStatus.BAD_REQUEST),
	OM_1006(HttpStatus.BAD_REQUEST),
	OM_1007(HttpStatus.BAD_REQUEST),
	OM_1008(HttpStatus.INTERNAL_SERVER_ERROR),
	OM_1009(HttpStatus.INTERNAL_SERVER_ERROR),
	OM_1010(HttpStatus.INTERNAL_SERVER_ERROR);
	
	HttpStatus statusCode;

	CustomError(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

}
