package com.dosung.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PageDTO {
	private int page;
	private int totalRecord;
	private int recordPerPage;
	private int beginRecord;
	private int endRecord;
	private int totalPage;
	private int pagePerBlock;
	private int beginPage;
	private int endPage;
}
