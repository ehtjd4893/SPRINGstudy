package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.koreait.member.dto.Member;

public class PresentPwCheckCommand {

	public Map<String, Boolean> execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		Member member = (Member)map.get("member");
		HttpSession session = (HttpSession)map.get("session");
		
		String pw1 = ((Member)session.getAttribute("loginUser")).getPw();
		String pw2 = SecurityUtils.encodeBase64(member.getPw());
		
		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("isCorrect", pw1.equals(pw2));
		return resultMap;
		
	}

}