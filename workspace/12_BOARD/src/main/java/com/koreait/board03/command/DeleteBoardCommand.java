package com.koreait.board03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.board03.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		long no = Long.parseLong( ((HttpServletRequest)map.get("request")).getParameter("no") );
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		boardDAO.deleteBoard(no);
		
	}

}
