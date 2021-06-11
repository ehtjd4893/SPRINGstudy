package com.koreait.mvc02.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvc02.dto.Member;

@Controller  // ��Ʈ�ѷ�
public class MyController {

	// URLMapping�� ó���ϴ� �޼ҵ� ������ �����Ѵ�.
	
	// @RequestMapping(value="/")  // URLMapping�� contextPath�� ����̴�. (http://localhost:9090/mvc02/)
	// @RequestMapping(method=RequestMethod.GET)  // GET����� ��û�̴�. (���� ����)
	@RequestMapping(value="/", method=RequestMethod.GET)

	// 1. ��ȯŸ�� : String, ����View�� �̸��� ��ȯ�Ѵ�.
	// 2. �޼ҵ�� : a, �ƹ� ������ ����.
	// 3. �Ű����� : Model model, request�� �̿��ϴ� ��ü�̴�. ������ ����.
	public String a(Model model) {
		// return "index";
		// 1. DispatherServlet(servlet-context.xml)�� ���ǵ� ViewResolver�� ���ؼ� ó���ȴ�.
		//    1) prefix : "/WEB-INF/views/", return �տ� �߰��Ѵ�.
		//    2) suffix : ".jsp", return �ڿ� �߰��Ѵ�.
		// 2. forward�� �̵��ȴ�.
		return "index";  // return "/WEB-INF/views/index.jsp";
	}
	
	
}