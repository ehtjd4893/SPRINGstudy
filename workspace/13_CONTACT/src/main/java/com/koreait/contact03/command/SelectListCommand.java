package com.koreait.contact03.command;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;
import com.koreait.contact03.dto.Contact;

public class SelectListCommand implements ContactCommand {

	
	@Override
	public void execute(SqlSession sqlSession, Model model) {
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		List<Contact> list = contactDAO.selectList();
		
		model.addAttribute("list", list);
	}

}
