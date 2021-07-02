package com.dosung.home.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;

public class PhoneCheckCommand  {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String phone = request.getParameter("phone");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		int phonePass = dao.phoneCheck(phone);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("phonePass", phonePass);
		
		return resultMap;
	}

}
