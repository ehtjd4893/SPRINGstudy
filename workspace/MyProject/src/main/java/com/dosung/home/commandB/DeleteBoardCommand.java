package com.dosung.home.commandB;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dosung.home.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) map.get("multipartRequest");
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		long no = Long.parseLong(multipartRequest.getParameter("no"));
		
		String filename = multipartRequest.getParameter("filename");
		
		// 파일이 존재한다면 삭제
		if(filename != "null" && filename != "") {
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
			File file = new File(realPath, filename);
			if(file != null && file.exists()) {
				file.delete();
			}
		}		
		
		boardDAO.deleteBoard(no);
		return "board/mainList";
	}

}
