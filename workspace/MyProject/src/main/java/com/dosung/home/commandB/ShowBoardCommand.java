package com.dosung.home.commandB;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.commandM.MemberCommand;
import com.dosung.home.dao.BoardDAO;

public class ShowBoardCommand implements BoardCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		// 전달받은 데이터 옮겨담음
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		
		// 조회시마다 조회수 1만큼 올려주는 함수
		dao.updateHitByNo(no);
		
		// model을 통해 BoardDTO 전달
		model.addAttribute("Board", dao.selectBoardByNo(no));
		return "board/show";
	}

}
