package com.koreait.mvc03.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.mvc03.configuration.BeanConfiguration;
import com.koreait.mvc03.dto.Person;

@Controller
public class MyController5 {
	// BeanConfiguration.java에 정의된 bean 가져오기
	// 1. CGLIB 디펜던시를 추가한다.
	// 2. AnnotationConfigApplicationContext 클래스를 통해서 bean 가져온다.
	
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
	@RequestMapping("f4/v01")
	public String a(Model model) {
		Person m = ctx.getBean("person1", Person.class);
		
		model.addAttribute("m",m);
		
		return "folder04/view01";
	}
	
	@RequestMapping("f4/v02")
	public String b(Model model) {
		Person m = ctx.getBean("person1", Person.class);
		Person wm = ctx.getBean("person2", Person.class);

		List<Person> list = new ArrayList<Person>();
		
		list.add(m);
		list.add(wm);
		
		model.addAttribute("p_list",list);
		
		return "folder04/view02";
	}
}
