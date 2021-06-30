package com.koreait.search.dto;

import lombok.Data;

@Data
public class QueryDTO {
	private String column;
	private String query;
	private int beginRecord;
	private int endRecord;
	private int top;
	private int bottom;
}
