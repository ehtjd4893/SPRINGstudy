package com.dosung.home.commandM;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;
import com.dosung.home.dto.MemberDTO;

public class SignupCommand implements MemberCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setPhone("010" + request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setAddress(request.getParameter("address"));
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		

		if(dao.signup(member) > 0) {
			return "member/login";
		}
		
		return "member/signup";
	}

}
