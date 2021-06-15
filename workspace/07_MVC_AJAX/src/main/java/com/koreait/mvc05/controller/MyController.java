package com.koreait.mvc05.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.mvc05.dto.Person;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public String contextPath() {
		return "index";
	}
	
	// 1. String 반환하기
	@RequestMapping(value="v01", 
					method=RequestMethod.GET,
					produces="text/plain; charset=utf-8")
	@ResponseBody	// ajax 처리를 위한 필수 애너테이션, 반환하는 타입이 view가 아니라는 것을 알려준다.	
	public String v01(@RequestParam("name") String name,
				  @RequestParam("age") int age) {
		return name + ", " + age;	
	}	
	
	@RequestMapping(value="v02",
					method=RequestMethod.GET,
					produces="application/json; charset=utf-8")
	public Map<String, Object> v02(@RequestParam("name") String name,
					  @RequestParam("age") int age) {
		Person p = new Person();
		p.setAge(age);
		p.setName(name);
		
		Map<String, Object> map = new HashMap<>();
		map.put("person", p);
		
		return map;
	}
}
