package com.koreait.contact03.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact03.command.DeleteContactCommand;
import com.koreait.contact03.command.InsertContactCommand;
import com.koreait.contact03.command.SelectListCommand;
import com.koreait.contact03.command.UpdateContactCommand;
import com.koreait.contact03.command.ViewContactCommand;
import com.koreait.contact03.dto.Contact;



@Controller
public class ContactController {
	
	private SelectListCommand selectListCommand;
	private InsertContactCommand insertContactCommand;
	private ViewContactCommand viewContactCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	private SqlSession sqlSession;
	
	@Autowired
	public void setCommand(SelectListCommand selectListCommand, 
			 		  InsertContactCommand insertContactCommand,
					  ViewContactCommand viewContactCommand, 
					  UpdateContactCommand updateContactCommand,
					  DeleteContactCommand deleteContactCommand,
					  SqlSession sqlSession) {
		this.selectListCommand = selectListCommand;
		this.insertContactCommand = insertContactCommand;
		this.viewContactCommand = viewContactCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
		this.sqlSession = sqlSession;
	}
	
	@GetMapping(value="/")
	public String start() {
		return "index";
	}
	
	@GetMapping(value="listPage.do")
	public String listPage(Model model) {
		selectListCommand.execute(sqlSession,model);
		return "contact/list";
	}
	
	@GetMapping(value="insertPage.do")
	public String insertPage() {		
		return "contact/insert";
	}
	
	@PostMapping(value="insert.do")
	public void insert(Contact contact, HttpServletResponse response, Model model) {
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		insertContactCommand.execute(sqlSession,model);
		// return listPage(model);
	}
	
	@GetMapping(value="viewContact.do")
	public String view(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		viewContactCommand.execute(sqlSession,model);
		return "contact/view";
	}
	
	@PostMapping(value="update.do")
	public void update(Contact contact, HttpServletResponse response, Model model) {
		model.addAttribute("contact",contact);
		model.addAttribute("response",response);		
		updateContactCommand.execute(sqlSession,model);
		//return listPage(model);
	}
	
	@GetMapping(value="delete.do")
	public void delete(@RequestParam("no") long no, HttpServletResponse response ,Model model) {
		model.addAttribute("no", no);
		model.addAttribute("response", response);
		
		deleteContactCommand.execute(sqlSession,model);
		//return listPage(model);
	}
	
}
