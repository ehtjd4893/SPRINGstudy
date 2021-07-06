package com.dosung.home.commandM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.BoardDAO;
import com.dosung.home.dto.BoardDTO;

public class BoardListCommand{


	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		List<BoardDTO> list = dao.boardList();
		resultMap.put("list", list);
		System.out.println(list.toString());
		return resultMap;
	}

}
