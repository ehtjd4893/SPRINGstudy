package com.koreait.mvc03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	
	// 모든 요청(URLMapping) 처리는 메소드 단위로 한다.
	
	// 	@RequestMapping(value="/",method=RequestMethod.GET)
	// 1. GET 방식의 method는 생략할 수 있다.
	//	@RequestMapping(value="/")
	// 2. value 속성만 작성하는 경우에는 value 속성 명시를 생략할 수 있다.
	// @RequestMapping("/")
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String a() {
		// 아래 return "index"는 servlet-context.xml에 의해서 다음과 같이 처리된다.
		// return "/WEB-INF/views/index.jsp";
		return "index";	// 기본적인 이동 방식은 forward이다.
	}
}
