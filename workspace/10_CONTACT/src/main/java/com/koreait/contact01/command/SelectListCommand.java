package com.koreait.contact01.command;

import java.util.List;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;
import com.koreait.contact01.dto.Contact;

public class SelectListCommand implements ContactCommand {
	
	@Override
	public void execute(Model model) {
		List<Contact> list = ContactDAO.getInstance().selectList();
		
		model.addAttribute("list", list);
	}

}
