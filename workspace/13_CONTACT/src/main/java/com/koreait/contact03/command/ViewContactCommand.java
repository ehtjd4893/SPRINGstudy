package com.koreait.contact03.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;
import com.koreait.contact03.dto.Contact;

public class ViewContactCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		Contact c = contactDAO.selectByNo(no);
		model.addAttribute("contact", c);
	}

}
