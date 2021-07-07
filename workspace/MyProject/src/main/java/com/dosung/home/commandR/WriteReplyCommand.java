package com.dosung.home.commandR;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.BoardDAO;

public class WriteReplyCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model){
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String reply = request.getParameter("reply");
		long boardno = Long.parseLong(request.getParameter("no"));
		String writer = request.getParameter("replyWriter");
		
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		Map<String, String> container = new HashMap<>();
		container.put("reply", reply);
		dao.writeReply(container);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message", "댓글을 입력하였습니다.");
		
		return resultMap;
	}
}
