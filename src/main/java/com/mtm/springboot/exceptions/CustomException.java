package com.mtm.springboot.exceptions;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.mtm.springboot.error.CustomError;
import com.mtm.springboot.service.ErrorMessageResolverService;
import com.mtm.springboot.util.CustomApplicationContextAware;

/**
 * @author ManjunathMT
 * 
 */
public class CustomException extends Exception {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomException.class);

	private static final long serialVersionUID = 2660944572410073250L;

	private CustomError errorCode;
	private String[] params;

	public CustomException() {
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(CustomError errorCode, String... params) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		Throwable e = this.getCause();
		while (e != null) {
			sb.append("\nCaused by: ").append(e.toString());
			e = e.getCause();
		}

		return sb.toString();
	}

	public String getErrorCode() {
		if (errorCode == null) {
			errorCode = CustomError.OM_1001;
		}
		return errorCode.name();
	}

	public String getErrorMessage() {
		ErrorMessageResolverService errorMessageResolverService = CustomApplicationContextAware
				.getBean(ErrorMessageResolverService.class);
		if (errorMessageResolverService != null) {
			try {
				if (errorCode == null) {
					errorCode = CustomError.OM_1001;
				}
				return errorMessageResolverService.resolveErrorMessageByErrorCode(errorCode, params,
						Locale.ENGLISH.toString());
			} catch (IOException e) {
				LOGGER.error("Exception while resolving error message :- {}", e.getMessage());
			}
		}
		return null;
	}

	public HttpStatus getHttpStatusCode() {
		if (errorCode == null) {
			errorCode = CustomError.OM_1001;
		}
		return errorCode.getStatusCode();
	}
}
