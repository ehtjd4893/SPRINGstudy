package com.dosung.home.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDTO {
	private long boardno;
	private long replyno;
	private String writer;
	private String content;
	private Date postdate;
	private String ip;
}
