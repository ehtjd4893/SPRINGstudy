package com.dosung.home.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.utils.Utils;

// 인증키 발급
public class GetKeyCommand {
	
	public Map<String, Object> execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String phone = request.getParameter("phone");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("phone", phone);
		resultMap.put("key", Utils.getAuthCode(6));
		
		return resultMap;
	}
}