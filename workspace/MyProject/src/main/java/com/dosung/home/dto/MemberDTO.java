package com.dosung.home.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberDTO {
	private long no;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Date regdate;
	private int status = 0;		// 1이면 탈퇴, 0이면 가입
}
