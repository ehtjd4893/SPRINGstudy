package com.dosung.home.commandB;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.BoardDAO;

public class UpdateBoardPageCommand implements BoardCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		model.addAttribute("Board", dao.selectBoardByNo(no));
		return "board/updateBoard";
	}

}
