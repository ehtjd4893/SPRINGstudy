package com.koreait.contact01.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;
import com.koreait.contact01.dto.Contact;

public class DeleteContactCommand implements ContactCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		long no = (long) map.get("no");
		ContactDAO.getInstance().deleteByNo(no);

	}

}
