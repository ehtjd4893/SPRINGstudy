package com.koreait.board02.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.board02.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		boardDAO.deletBoard(Long.parseLong(request.getParameter("no")));
	}

}
