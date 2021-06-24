package com.koreait.file.command;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.koreait.file.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		long no = Long.parseLong(request.getParameter("no"));
		
		String filename = request.getParameter("filename1");
		if(filename != null && filename != "") {
			String realPath = request.getServletContext().getRealPath("resources/archive");
			File file = new File(realPath, filename);
			if(file != null && file.exists()) {
				file.delete();
			}
		}		
		boardDAO.deleteBoard(no);
	}

}
