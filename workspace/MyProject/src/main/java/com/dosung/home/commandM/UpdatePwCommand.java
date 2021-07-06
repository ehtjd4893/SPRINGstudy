package com.dosung.home.commandM;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;
import com.dosung.home.dto.MemberDTO;

public class UpdatePwCommand implements MemberCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String pw = request.getParameter("pw");

		MemberDTO dto = new MemberDTO();
		dto.setNo(Long.parseLong(request.getParameter("no")));
		dto.setPw(pw);
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		dao.updatePw(dto);
		MemberDTO loginUser = (MemberDTO) request.getSession().getAttribute("loginUser");
		loginUser.setPw(pw);
		return "member/myPage";
	}

}
