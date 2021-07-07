package com.dosung.home.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dosung.home.commandB.BoardListCommand;
import com.dosung.home.commandB.DeleteBoardCommand;
import com.dosung.home.commandB.SearchBoardCommand;
import com.dosung.home.commandB.ShowBoardCommand;
import com.dosung.home.commandB.UpdateBoardCommand;
import com.dosung.home.commandB.UpdateBoardPageCommand;
import com.dosung.home.commandB.WriteCommand;
import com.dosung.home.commandR.WriteReplyCommand;
import com.dosung.home.dto.BoardDTO;

@Controller
public class BoardController {

	
	private SqlSession sqlSession;
	private WriteCommand writeCommand;
	private BoardListCommand boardListCommand;
	private ShowBoardCommand showBoardCommand;
	private UpdateBoardCommand updateBoardCommand;
	private UpdateBoardPageCommand updateBoardPageCommand;
	private DeleteBoardCommand deleteBoardCommand;
	private SearchBoardCommand searchBoardCommand;
	private WriteReplyCommand writeReplyCommand;
	@Autowired
	public BoardController(SqlSession sqlSession,
						   WriteCommand writeCommand,
	 				       BoardListCommand boardListCommand,
	 				       ShowBoardCommand showBoardCommand,
	 				       UpdateBoardPageCommand updateBoardPageCommand,
	 				       UpdateBoardCommand updateBoardCommand,
	 				       DeleteBoardCommand deleteBoardCommand,
	 				       SearchBoardCommand searchBoardCommand,
	 				       WriteReplyCommand writeReplyCommand) {
		this.sqlSession = sqlSession;	
		this.writeCommand = writeCommand;
		this.boardListCommand = boardListCommand;
		this.showBoardCommand = showBoardCommand;
		this.updateBoardPageCommand = updateBoardPageCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
		this.searchBoardCommand = searchBoardCommand;
		this.writeReplyCommand = writeReplyCommand;
	}
	
	// board/mainList로 이동하게 해주는 매핑
	@GetMapping(value= "mainList.do")
	public String back(HttpServletRequest request, Model model) {
		// paging 버튼을 눌러 page를 받아온 상태라면,
		// model을 통해 page를 담아서 전달해준다.
		// 만약 paging 버튼을 누른 상태가 아니라면 1번 화면을 보여준다.
		String page = request.getParameter("page");
		if(page != null) {
			model.addAttribute("page", page);
		} else {
			model.addAttribute("page", "1");
		}	
		return "board/mainList";
	}
	
	// 게시글 작성 화면으로 이동
	@GetMapping(value="writePage.do")
	public String writePage() {
		return "board/write";
	}
	
	// 게시글 작성화면에서 입력한 정보를 DB에 넣어주는 매핑
	@PostMapping(value="write.do")
	public String write(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model) {
		// execute 함수를 위해 필요한 것들을 model을 통해 전달
		model.addAttribute("response", response);
		model.addAttribute("multipartRequest", multipartRequest);
		
		// mainList로 돌아가 1번 페이지를 보여주도록 설정함.
		model.addAttribute("page", "1");
		return writeCommand.execute(sqlSession, model);
	}
	
	// 전체 게시글을 ajax를 통해 json형태로 반환
	@GetMapping(value="boardList.do",
			    produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> boardList(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		
		// 전체 게시글 중 첫번째 화면을 보여주도록 설정
		model.addAttribute("page", "1");
		return boardListCommand.execute(sqlSession, model);
	}
	
	// 게시글 제목 클릭시 게시글의 내용을 보여주는 매핑
	@GetMapping(value="showBoard.do")
	public String showBoard(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return showBoardCommand.execute(sqlSession, model);
	}
	
	// update 화면으로 연결해주는 매핑
	// showBoard 화면의 정보를 그대로 옮겨줌.
	@GetMapping(value="updateBoardPage.do")
	public String updateBoardPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return updateBoardPageCommand.execute(sqlSession, model);
	}
	
	// 
	@PostMapping(value="updateBoard.do")
	public String updateBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("response", response);	
		model.addAttribute("page", "1");
		updateBoardCommand.execute(sqlSession, model);
		return "board/mainList";
	}
	
	@PostMapping(value="deleteBoard.do")
	public String deleteBoard(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("page", "1");
		return deleteBoardCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="searchBoard.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> searchBoard(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		
		return searchBoardCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="searchList.do")
	public String searchList(HttpServletRequest request, Model model) {
		model.addAttribute("column", request.getParameter("column"));
		model.addAttribute("query", request.getParameter("query"));
		model.addAttribute("page", request.getParameter("page"));
		return "board/mainList";
	}
	
	/* 이 아래는 reply 관련 매핑 */
	@GetMapping(value="writeReply.do", produces="application/json; charset=utf-8")
	public Map<String, Object> writeReply(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		
		return writeReplyCommand.execute(sqlSession, model);
	}
}
