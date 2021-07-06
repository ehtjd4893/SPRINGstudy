package com.dosung.home.dao;

import com.dosung.home.dto.MemberDTO;

public interface MemberDAO {

	public MemberDTO login(MemberDTO member);

	public int signup(MemberDTO member);

	public int idCheck(String id);

	public int phoneCheck(String phone);
	
	public int signout(long no);

	public int myPhoneCheck(MemberDTO dto);

	public void resignup(long no);

	public void updatePw(MemberDTO dto);

	public void update(MemberDTO dto);

	public MemberDTO findId(MemberDTO dto);

	public MemberDTO findPw(MemberDTO dto);


}
