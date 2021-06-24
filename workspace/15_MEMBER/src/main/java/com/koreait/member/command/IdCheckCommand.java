package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;

public class IdCheckCommand {

	public Map<String, Integer> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		
		String id=  (String) map.get("id");
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);	
		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("count", memberDAO.idCheck(id));
		return resultMap;
	}

}
