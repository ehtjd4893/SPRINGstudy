package com.dosung.home.commandM;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.support.DaoSupport;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dosung.home.dao.BoardDAO;
import com.dosung.home.dto.BoardDTO;
import com.dosung.home.dto.MemberDTO;

public class WriteCommand implements BoardCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		String writer = multipartRequest.getParameter("writer");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String ip = multipartRequest.getRemoteAddr();
		BoardDTO dto = new BoardDTO();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setIp(ip);
		dto.setImage("null");

		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		List<MultipartFile> files = multipartRequest.getFiles("files");
		
		System.out.println(multipartRequest.getFiles("files"));
		for (MultipartFile file : files) {
			
			if (file != null && !file.isEmpty()) {

				// 올릴 때 파일명
				String originalFilename = file.getOriginalFilename();
				System.out.println("첨부파일명: " + originalFilename);
				
				// 서버에 저장할 파일명
				// 파일명의 중복 방지 대책이 필요
				// 파일명_올린시간.확장자
				String extension = originalFilename.substring( originalFilename.lastIndexOf(".") + 1 );
				String filename = originalFilename.substring( 0, originalFilename.lastIndexOf(".") );
				String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension;
				
				// 첨부파일을 저장할 서버 위치
				String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");  // archive 디렉터리는 없으므로 생성이 필요
				
				// archive 디렉터리 생성
				File archive = new File(realPath);
				if ( !archive.exists() ) {
					archive.mkdirs();
				}
				
				// 서버에 첨부파일 저장
				File attach = new File(archive, uploadFilename);
				try {
					file.transferTo(attach);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dto.setImage(uploadFilename);
				// DB에 데이터 저장
				
			}
			
			boardDAO.write(dto);
		}  // for
		return "board/mainList";
		
	}




}
