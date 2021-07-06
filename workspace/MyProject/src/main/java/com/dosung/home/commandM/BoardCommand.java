package com.dosung.home.commandM;


import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BoardCommand {

	public String execute(SqlSession sqlSession, Model model);
	
}
