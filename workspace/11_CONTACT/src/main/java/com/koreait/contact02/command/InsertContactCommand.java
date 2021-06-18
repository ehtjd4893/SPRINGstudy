package com.koreait.contact02.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class InsertContactCommand implements ContactCommand{

	@Autowired
	ContactDAO contactDAO;
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		Contact c = (Contact) map.get("contact");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		response.setContentType("text/html; charset=utf-8");
		int count = contactDAO.insert(c);
		PrintWriter out;
		try {
			out = response.getWriter();
			if (count > 0) {
				out.println("<script>");
				out.println("alert('등록 성공');");
				out.println("location.href='listPage.do';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('등록 실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
