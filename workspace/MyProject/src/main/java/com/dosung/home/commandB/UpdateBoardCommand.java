package com.dosung.home.commandB;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dosung.home.dao.BoardDAO;
import com.dosung.home.dto.BoardDTO;
import com.dosung.home.utils.Utils;

public class UpdateBoardCommand {

	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		HttpServletResponse response = (HttpServletResponse) map.get("response");

		// 전달받은 data 옮겨담기
		long no = Long.parseLong(multipartRequest.getParameter("no"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String ip = Utils.getClientIpAddr(multipartRequest);
		String preFile = multipartRequest.getParameter("preFile");

		// boardDAO의 함수를 이용하기 위해 정보 전달할 dto 그릇 설정 과정
		BoardDTO dto = new BoardDTO();
		dto.setNo(no);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setIp(ip);
		dto.setImage(preFile);
		
		
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		// 업데이트 화면에서 첨부받은 file 받아옴.
		List<MultipartFile> files = multipartRequest.getFiles("files");
		
		
		for (MultipartFile file : files) {
			// 파일이 존재하고, 비어있지 않다면
			if (file != null && !file.isEmpty()) {

				// 새로운 파일이 첨부되었으니 realpath에 있는 기존의 파일을 지운다.
				if(preFile != "null" && preFile != "") {
					String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
					File f = new File(realPath, preFile);
					if(f != null && f.exists()) {
						f.delete();
					}
				}
				
				// 올릴 때 파일명
				String originalFilename = file.getOriginalFilename();
				
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
				// dto에 image파일 이름 설정
				dto.setImage(uploadFilename);
				
			}
			
			// DB에 업데이트
			boardDAO.updateBoard(dto);
		}  // for
		
		// 동작 안 함.
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
