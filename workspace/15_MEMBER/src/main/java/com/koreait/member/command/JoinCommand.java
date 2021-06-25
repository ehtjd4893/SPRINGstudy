package com.koreait.member.command;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;

public class JoinCommand implements MemberCommand{

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String , Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		System.out.println(request == null);
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		//String encodedPw = SecurityUtils.sha256(pw);
		
		
		
		Member member = new Member();
		member.setId(id);
		member.setPw(SecurityUtils.encodeBase64(pw));
		member.setName(SecurityUtils.xxs(name));
		member.setEmail(email);
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		memberDAO.join(member);
	}

}
