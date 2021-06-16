package com.koreait.board01.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.board01.command.BoardCommand;
import com.koreait.board01.command.BoardListCommand;
import com.koreait.board01.command.InsertBoardCommand;
import com.koreait.board01.command.deleteBoardCommand;
import com.koreait.board01.command.selectBoardByNoCommand;
import com.koreait.board01.command.updateBoardCommand;
import com.koreait.board01.command.updatePageCommand;
import com.koreait.board01.dao.BoardDAO;
import com.koreait.board01.dto.Board;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class) ;
	private BoardCommand command;
	
	@GetMapping(value="/")	//	@RequestMapping(value="/", method=RequestMethod.GET) 과 같음
	public String index() {
		logger.info("index() 호출");
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		logger.info("selectBoardList() 호출");
		command = new BoardListCommand();
		command.execute(model);
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
		command = new InsertBoardCommand();
		command.execute(model);
		
		return "redirect:selectBoardList.do";
	}
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new selectBoardByNoCommand();
		command.execute(model);
		return "board/view";
	}
	
	@PostMapping(value="updatePage.do")
	public String updatePage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new updatePageCommand();
		command.execute(model);
		return "board/update";
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new updateBoardCommand();
		command.execute(model);
		return "redirect:selectBoardList.do";
	}
	
	@PostMapping(value="deleteBoard.do")
	public String deleteBoard(Board board, Model model) {
		model.addAttribute("board", board);
		command = new deleteBoardCommand();
		command.execute(model);
		
		return "redirect:selectBoardList.do";
	}
}
