package com.koreait.integration1.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
	
	private long no;
	private String title;
	private String content;
	private String regdate;

}
