package com.dosung.home.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;

public class SignoutCommand implements MemberCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		dao.signout(no);
		
		return "member/login";
	}

}
