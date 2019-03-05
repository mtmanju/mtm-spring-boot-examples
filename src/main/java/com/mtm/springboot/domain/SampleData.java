package com.mtm.springboot.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SampleData {

	private String identifier;
	private String name;
	private String description;
	private List<RestVariable> variables;

}
