package com.mtm.springboot.service;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.mtm.springboot.error.CustomError;

@Service("errorMessageResolverService")
public class ErrorMessageResolverService {
	public String resolveErrorMessageByErrorCode(CustomError errorCode, String[] params, String locale)
			throws IOException {
		if (errorCode != null) {
			Properties prop = PropertiesLoaderUtils.loadProperties(
					new ClassPathResource(String.format("errorMessage_%s.properties", locale.toLowerCase())));
			String errorMessage = prop.getProperty(errorCode.name());
			if (StringUtils.isNotBlank(errorMessage)) {
				return String.format(errorMessage, (Object[]) params);
			}
		}
		return null;
	}
}