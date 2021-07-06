package com.dosung.home.commandM;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;
import com.dosung.home.dto.MemberDTO;

public class FindIdCommand {


	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		MemberDTO dto = new MemberDTO();
		dto.setPhone(phone);
		dto.setName(name);
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		MemberDTO resultDTO = dao.findId(dto);
		String id = resultDTO.getId();
		
		System.out.println(id);
		Map<String, Object> resultMap = new HashMap<>();
		if(id == null) {
			resultMap.put("status", 500);
			resultMap.put("id", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("id", id);			
		}
		return resultMap;
	}

}
