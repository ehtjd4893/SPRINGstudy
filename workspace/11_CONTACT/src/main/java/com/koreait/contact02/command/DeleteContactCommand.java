package com.koreait.contact02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	@Autowired
	ContactDAO contactDAO;
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		contactDAO.deleteByNo(no);

	}

}
