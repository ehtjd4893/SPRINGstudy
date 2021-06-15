package com.koreait.mvc03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvc03.dto.Person;

@Controller
public class MyController3 {
	/*
	 * Spring의 ModelAndView 클래스
	 * 
	 * 1. 스프링2 이전에 많이 사용하던 클래스이다.
	 * 2. 현재도 많이 존재하고 있는 클래스이다.
	 * 3. 앞으로는 사용을 자제하는 것이 좋다.
	 * 4. Model과 View를 모두 처리한다.
	 *  1) Model: 응답View에게 값을 전달할 때 사용하는 클래스
	 *  2) View: 응닶View를 의미한다.
	 * */

	@RequestMapping(value="f2/v01")
	public ModelAndView a() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("folder02/view01");	// "WEB-INF/views/folder02/view01"
		mav.addObject("name", "개똥이");
		mav.addObject("age", 50);
		return mav;
	}
	
	@RequestMapping(value="f2/v02")
	public ModelAndView b() {
		ModelAndView mav = new ModelAndView();
	
		mav.setViewName("folder02/view02");
		Person p = new Person();
		p.setAge(17);
		p.setName("고길동");
		p.addHobbies("배그");
		p.addHobbies("롤");
		mav.addObject("Person", p);
		
		return mav;
	}
	
}
