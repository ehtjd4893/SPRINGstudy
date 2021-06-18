package com.koreait.contact02.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class SelectListCommand implements ContactCommand {
	
	@Autowired
	ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		List<Contact> list = contactDAO.selectList();
		
		model.addAttribute("list", list);
	}

}
