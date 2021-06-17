package com.koreait.board01.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.board01.command.BoardCommand;
import com.koreait.board01.command.BoardListCommand;
import com.koreait.board01.command.InsertBoardCommand;
import com.koreait.board01.command.DeleteBoardCommand;
import com.koreait.board01.command.SelectBoardByNoCommand;
import com.koreait.board01.command.UpdateBoardCommand;
import com.koreait.board01.command.UpdatePageCommand;
import com.koreait.board01.dao.BoardDAO;
import com.koreait.board01.dto.Board;

// @Controller
public class BoardController2 {
	
	// field
	private static final Logger logger = LoggerFactory.getLogger(BoardController2.class);
	//private BoardCommand command; 같은 인터페이스 사용하지 않아도 좋다.

	// root-context.xml에 정의된 bean 생성
	/*
	 * 1. 필드 이용하기 (하나씩 모두 적용해줘야 한다.
	 * @Autowired
	 * private BoardListCommand boardListCommand;
	 * @Autowired
	 * private DeleteBoardCommand deleteBoardCommand;
	 * ...
	 * */
	
	/*
	 * 2. setter 형태의 메소드 이용하기
	 * */
	private BoardListCommand boardListCommand;
	private DeleteBoardCommand deleteBoardCommand;
	private InsertBoardCommand insertBoardCommand;
	private SelectBoardByNoCommand selectBoardByNoCommand;
	private UpdatePageCommand updatePageCommand;
	private UpdateBoardCommand updateBoardCommand;
	
	@Autowired
	public void setCommand(BoardListCommand boardListCommand,
						   DeleteBoardCommand deleteBoardCommand,
						   InsertBoardCommand insertBoardCommand,
						   SelectBoardByNoCommand selectBoardByNoCommand,
						   UpdatePageCommand updatePageCommand,
						   UpdateBoardCommand updateBoardCommand) {
		this.boardListCommand = boardListCommand;
		this.deleteBoardCommand = deleteBoardCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.selectBoardByNoCommand = selectBoardByNoCommand;
		this.updatePageCommand = updatePageCommand;
		this.updateBoardCommand = updateBoardCommand;
	}
	
	@GetMapping(value="/")	//	@RequestMapping(value="/", method=RequestMethod.GET) 과 같음
	public String index() {
		logger.info("index() 호출");
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		logger.info("selectBoardList() 호출");
		boardListCommand.execute(model);
		return "board/list";	// board/list.jsp로 이동
	}
	
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		logger.info("selectBoardPage() 호출");
		return "board/insert";	// board/insert.jsp로 이동
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(HttpServletRequest request, Model model) {
		logger.info("insertBoard() 호출");
		// 모든 커맨드에는 model만 전달할 수 있다.
		// 따라서, command에 전달할 데이터들은 모두 model에 저장한다.
		model.addAttribute("request",request);
		insertBoardCommand.execute(model);
		
		return "redirect:selectBoardList.do";
	}
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		selectBoardByNoCommand.execute(model);
		return "board/view";
	}
	
	@PostMapping(value="updatePage.do")
	public String updatePage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		updatePageCommand.execute(model);
		return "board/update";
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		updateBoardCommand.execute(model);
		return "redirect:selectBoardList.do";
	}
	
	@PostMapping(value="deleteBoard.do")
	public String deleteBoard(Board board, Model model) {
		model.addAttribute("board", board);
		deleteBoardCommand.execute(model);
		
		return "redirect:selectBoardList.do";
	}
}
