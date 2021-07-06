package com.dosung.home.commandM;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;
import com.dosung.home.dto.MemberDTO;

public class FindPwCommand {


	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String phone = request.getParameter("phone");
		String id = request.getParameter("id");
		MemberDTO dto = new MemberDTO();
		dto.setPhone(phone);
		dto.setId(id);
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		MemberDTO resultDTO = dao.findPw(dto);
		String pw = resultDTO.getPw();
		
		Map<String, Object> resultMap = new HashMap<>();
		if(id == null) {
			resultMap.put("status", 500);
			resultMap.put("pw", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("pw", id);			
		}
		return resultMap;
	}

}
