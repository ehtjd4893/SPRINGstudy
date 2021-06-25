package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.Model;

public class EmailAuthCommand{

	@Autowired
	private JavaMailSender MailSender;
	
	public Map<String, String> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String email = request.getParameter("email");
		
		// MimeMessage 클래스
		// 이메일을 작성하는 클래스
		MimeMessage message = MailSender.createMimeMessage();
		
		String code = null;
		try {
			message.setHeader("Content-type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("forspringlec@gmail.com", "관리자"));	// 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));	// 받는 사람
			message.setSubject("인증 요청 메일입니다.");
			code =  SecurityUtils.getAuthCode(6);	// 6자리 인증코드
			message.setText("인증번호는 " + code + "입니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		MailSender.send(message);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("authCode", code);
		return resultMap;
	}

}
