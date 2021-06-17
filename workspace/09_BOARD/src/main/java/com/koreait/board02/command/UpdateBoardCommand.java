package com.koreait.board02.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.board02.dao.BoardDAO;
import com.koreait.board02.dto.Board;

public class UpdateBoardCommand implements BoardCommand {

	@Autowired BoardDAO boardDAO;
	
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		Board board = new Board();
		board.setNo(Long.parseLong(request.getParameter("no")));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		boardDAO.updateBoard(board);
	}

}
