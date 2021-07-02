package com.dosung.home.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;
import com.dosung.home.dto.MemberDTO;

public class LoginCommand implements MemberCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		// model을 통해 request 받아오기
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		// request를 통해 입력된 id, pw 확인
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 받아온 id, pw와 일치하는 MemberDTO가 있는지 확인
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		MemberDTO loginUser = dao.login(member);
		
		HttpSession ses = request.getSession();
		
		String result = null;
		// 로그인 성공의 경우, Session에 Member 정보 등록
		if(loginUser != null) {
			ses.setAttribute("loginUser", loginUser);
			result = "board/mainList";
		} else if(loginUser.getStatus() == 1) {	
			// 로그인에 성공했지만 탈퇴한 상태인 경우
			// 세션에 올려주고, 계정을 다시 복구할지 묻는 페이지로 이동.
			ses.setAttribute("loginUser", loginUser);
			result = "member/resignup";
		} else {	// 로그인 실패의 경우 null값 등록
			ses.setAttribute("loginUser", null);
			result = "member/login";
		}
		return result;
	}


}
