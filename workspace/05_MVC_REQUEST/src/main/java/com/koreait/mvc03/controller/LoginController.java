package com.koreait.mvc03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.mvc03.dto.Member;

@Controller
public class LoginController {
	
	@RequestMapping("loginPage.do")
	public String a() {
		
		return "member/login";
	}

	/*
	@RequestMapping("login.do")
	public String b(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "member/loginResult";
	}

	//매핑 똑같은거 쓰면 무조건 에러 뜸
	@RequestMapping("login.do")
	public String c(@RequestParam("id") String id,
					@RequestMapping("pw") String pw,
					Model model) {		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "member/loginResult";
	}
	 */
	@RequestMapping("login.do")
	public String d(Member member,
					Model model) {
		model.addAttribute("id", member.getId());
		model.addAttribute("pw", member.getPw());
		
		return "member/loginResult";
	}
}
