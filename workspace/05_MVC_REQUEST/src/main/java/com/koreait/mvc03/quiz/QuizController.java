package com.koreait.mvc03.quiz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {
	@RequestMapping(value="quiz/v01")
	public String a(HttpServletRequest request, Model model) {
		String title = request.getParameter("title");
		int hit = Integer.parseInt(request.getParameter("hit"));
		
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view01";
	}
	
	@RequestMapping(value="quiz/v02")
	public String b(@RequestParam(value="title") String title,
					@RequestParam(value="hit") int hit,
					Model model) {
		
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));		
		return "quiz/view02";
	}
	
	@RequestMapping(value="quiz/v03")
	public String c(@RequestParam(value="title", required=false, defaultValue="무제") String title,
					@RequestParam(value="hit", required=false, defaultValue="0") int hit,
					Model model) {
		
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view03";
	}
	
	@RequestMapping(value="quiz/v04")
	public String d(Board board,
					Model model) {
		board.setDate(new Date());
		model.addAttribute("board", board);
		
		return "quiz/view04";
	}
	
	@RequestMapping(value="quiz/v05")
	public String e(@ModelAttribute("title") String title,
					@ModelAttribute("hit") int hit,
					Model model) {
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view05";
	}
	
	@Autowired
	@Qualifier(value="b1")
	Board b1 = new Board();
	
	@RequestMapping(value="quiz/v06")
	public String f(Model model) {
		model.addAttribute("board",b1);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view06";
	}
}
