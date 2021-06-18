package com.koreait.contact02.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	@Autowired
	ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		response.setContentType("text/html; charset=utf-8"); 
		int count = contactDAO.deleteByNo(no);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			if (count > 0) {
				out.println("<script>");
				out.println("alert('삭제 성공');");
				out.println("location.href='listPage.do';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
