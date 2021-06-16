package com.koreait.board01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.koreait.board01.dto.Board;

public class updatePageCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		Board b = new Board();
		b.setNo(Long.parseLong(request.getParameter("no")));
		b.setTitle(request.getParameter("title"));
		b.setContent(request.getParameter("content"));
		model.addAttribute("board", b);
	}

}
