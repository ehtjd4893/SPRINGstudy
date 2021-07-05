package com.dosung.home.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;

public class ResignupCommand implements MemberCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		dao.resignup(Long.parseLong(request.getParameter("no")));
		request.getSession().invalidate();
		return "member/login";
	}

}
