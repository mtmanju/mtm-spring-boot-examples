package com.mtm.springboot.exceptions;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.mtm.springboot.error.CustomError;
import com.mtm.springboot.service.context.CustomApplicationContextAware;
import com.mtm.springboot.service.error.ErrorMessageResolverService;

/**
 * @author ManjunathMT
 * 
 */
public class CustomException extends Exception {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomException.class);

	private static final long serialVersionUID = 1L;

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

	public String getErrorMessage() {
		ErrorMessageResolverService errorMessageResolverService = CustomApplicationContextAware
				.getBean(ErrorMessageResolverService.class);
		if (null != errorMessageResolverService) {
			try {
				if (null == errorCode) {
					errorCode = CustomError.OM_1001;
				}
				return errorMessageResolverService.getErrorMessageForErrorCode(errorCode, params,
						Locale.ENGLISH.toString());
			} catch (IOException e) {
				LOGGER.error("Exception while resolving error message :- {}", e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(super.toString());
		Throwable exception = this.getCause();
		while (null != exception) {
			stringBuilder.append("\nCaused by: ").append(exception.toString());
			exception = exception.getCause();
		}
		return stringBuilder.toString();
	}

	public String getErrorCode() {
		if (null == errorCode) {
			errorCode = CustomError.OM_1001;
		}
		return errorCode.name();
	}

	public HttpStatus getHttpStatusCode() {
		if (null == errorCode) {
			errorCode = CustomError.OM_1001;
		}
		return errorCode.getStatusCode();
	}
}
