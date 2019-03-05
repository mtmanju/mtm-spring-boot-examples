package com.mtm.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RestVariable {
	private String name;
	private String type;
	private Object value;
	private String valueUrl;
}
