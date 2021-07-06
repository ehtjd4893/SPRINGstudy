package com.dosung.home.commandM;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.dosung.home.dao.MemberDAO;
import com.dosung.home.dto.MemberDTO;

public class UpdateCommand implements MemberCommand {

	@Override
	public String execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		MemberDTO dto = new MemberDTO();
		dto.setEmail(request.getParameter("email"));
		dto.setAddress(request.getParameter("address"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone"));
		dto.setNo(Long.parseLong(request.getParameter("no")));
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		dao.update(dto);
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		loginUser.setEmail(dto.getEmail());
		loginUser.setAddress(dto.getAddress());
		loginUser.setName(dto.getName());
		loginUser.setPhone(dto.getPhone());
		
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('변경 완료했습니다.')");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "member/myPage";
	}

}
