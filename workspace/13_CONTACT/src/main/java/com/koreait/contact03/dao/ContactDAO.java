package com.koreait.contact03.dao;


import java.util.List;


import com.koreait.contact03.dto.Contact;

public interface ContactDAO {

	public List<Contact> selectList();

	public int insert(final Contact c);

	public Contact selectByNo(long no);

	public int update(final Contact c);

	public int deleteByNo(long no) ;
}
