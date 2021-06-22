package com.koreait.contact03.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;
import com.koreait.contact03.dto.Contact;

public class UpdateContactCommand implements ContactCommand{

	
	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		Contact c = (Contact) map.get("contact");
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		response.setContentType("text/html; charset=utf-8");
		int count = contactDAO.update(c);
		PrintWriter out;
		try {
			out = response.getWriter();
			if (count > 0) {
				out.println("<script>");
				out.println("alert('수정 성공');");
				out.println("location.href='listPage.do';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('수정 실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
