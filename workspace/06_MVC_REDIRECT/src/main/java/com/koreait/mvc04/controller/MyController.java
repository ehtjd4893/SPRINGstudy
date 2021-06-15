package com.koreait.mvc04.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.mvc04.dto.Person;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public String contextPath() {
		return "index";
	}
	
	@RequestMapping("v01")
	public String v01(@RequestParam("name") String name,
					  @RequestParam("age") int age) {
		return "result";	// forward 이동 : result.jsp로 이동
	}
	
	@RequestMapping("v02")
	public String v02(Person person) {
		System.out.println(person);
		return "redirect:v03";	// redirect:매핑, request를 유지하지 않는다.
	}
	
	@RequestMapping("v03")
	public String v03() {
		return "result";
	}
	
	// redirect로 이동할 때 데이터 전달하기
	// 1. 새로운 파라미터를 추가해서 보낸다.
	// 2. RedirectAttributes 인터페이스를 이용한다. (Model 인터페이스를 구현한 인터페이스)
	//    addAttribute 는 request에 정보를 담아 처리하기 때문에 사용 x
	//	  addFlashAttribute() 메소드를 이용한다.
	
	@RequestMapping("v04")
	public String v04(Person person) {
		System.out.println(person);
		return "redirect:v05?name=" + person.getName() + "&age=" + person.getAge();
	}
	
	@RequestMapping("v05")
	public String v05(Person person) {
		// 전달은 잘 받았는데 인코딩으로 인해 한글 깨짐 현상 발생
		System.out.println(person);
		return "result";
	}
	
	@RequestMapping("v06")
	public String v06(Person person,
					  RedirectAttributes rttr) { 	// 리다이렉트 하는 곳으로 데이터를 보낼 수 있다.
		rttr.addFlashAttribute("name",person.getName());
		rttr.addFlashAttribute("age",person.getAge());
		System.out.println(person);
		return "redirect:v07";
	}
	
	@RequestMapping("v07")
	public String v07(Person person) {
		return "result";
	}
}
