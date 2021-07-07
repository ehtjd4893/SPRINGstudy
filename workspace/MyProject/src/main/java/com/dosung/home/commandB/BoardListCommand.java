package com.dosung.home.commandB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.BoardDAO;
import com.dosung.home.dto.BoardDTO;
import com.dosung.home.dto.PageDTO;
import com.dosung.home.utils.PagingUtils;


public class BoardListCommand{


	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		// page로 null값이 입력된다면 시작페이지로 지정
		int page = Integer.parseInt(opt.orElse("1"));
		
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		
		// 전체 게시물의 개수를 반환하는 함수
		int totalRecord = dao.getTotalRecord();
		
		PageDTO pageDTO = PagingUtils.getPage(totalRecord, page);
		
		String paging = PagingUtils.getPaging("mainList.do", page);
		
		// 전체 게시물을 list에 담아주는 함수.
		List<BoardDTO> list = dao.boardList(pageDTO);
		
		// json 형태로 반환해줄 Map 그릇
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("paging", paging);
		return resultMap;
	}

}
