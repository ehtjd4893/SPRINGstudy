package com.koreait.mvc01;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
	@Controller
	�ȳ�. �� Controller��. ������������ �Ϲ� �ڹ� Ŭ������.
*/
@Controller
public class HomeController {
	
	// �ֿܼ� �α׸� ����� �ΰ�(logger)
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
		@RequestMapping
		�ȳ�. �� URLMapping�� �ν��ϴ� �ֳ����̼��̾�.
	*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		// �α� �Ѹ���
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );  // View�� �����ϴ� ��
		
		return "home";  // View�� �̸�(home.jsp)
	}
	
}