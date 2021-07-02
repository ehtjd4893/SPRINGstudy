package com.dosung.home.dao;

import com.dosung.home.dto.MemberDTO;

public interface MemberDAO {

	public MemberDTO login(MemberDTO member);

	public int signup(MemberDTO member);

	public int idCheck(String id);

	public int phoneCheck(String phone);

}
