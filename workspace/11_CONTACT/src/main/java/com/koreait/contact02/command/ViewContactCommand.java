package com.koreait.contact02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class ViewContactCommand implements ContactCommand {

	@Autowired
	ContactDAO contactDAO;
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		Contact c = contactDAO.selectByNo(no);
		model.addAttribute("contact", c);
	}

}
