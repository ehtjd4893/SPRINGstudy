package com.dosung.home.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	
	private long no;
	private String writer;
	private String title;
	private String content;
	private Date postdate;
	private Date lastmodified;
	private String ip;
	private long hit;
	private String image;
}
