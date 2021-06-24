package com.koreait.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.member.command.EmailAuthCommand;
import com.koreait.member.command.IdCheckCommand;

@Controller
public class MemberController {
	
	private SqlSession sqlSession;
	private IdCheckCommand idCheckCommand;
	private EmailAuthCommand emailAuthCommand;

	
	public MemberController(SqlSession sqlSession, 
							IdCheckCommand idCheckCommand,
							EmailAuthCommand emailAuthCommand) {
		super();
		this.sqlSession = sqlSession;
		this.idCheckCommand = idCheckCommand;
		this.emailAuthCommand = emailAuthCommand;
	}
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		
		return "member/join";
	}
	
	@GetMapping(value="idCheck.do",
				produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Integer> idCheck(@RequestParam("id") String id, Model model) {
		
		model.addAttribute("id", id);
		
		return idCheckCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="verifyNum.do")
	@ResponseBody
	public Map<String, String> verifyNum(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return emailAuthCommand.execute(sqlSession, model);
	}
}