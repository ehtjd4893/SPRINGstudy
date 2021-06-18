package com.koreait.contact01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact01.command.DeleteContactCommand;
import com.koreait.contact01.command.InsertContactCommand;
import com.koreait.contact01.command.SelectListCommand;
import com.koreait.contact01.command.UpdateContactCommand;
import com.koreait.contact01.command.ViewContactCommand;
import com.koreait.contact01.dto.Contact;



@Controller
public class ContactController {
	
	//private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
	private SelectListCommand selectListCommand;
	private InsertContactCommand insertContactCommand;
	private ViewContactCommand viewContactCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	@Autowired
	public void setCmd(SelectListCommand selectListCommand,
					   InsertContactCommand insertContactCommand,
					   ViewContactCommand viewContactCommand,
					   UpdateContactCommand updateContactCommand,
					   DeleteContactCommand deleteContactCommand) {
		this.selectListCommand = selectListCommand;
		this.insertContactCommand = insertContactCommand;
		this.viewContactCommand = viewContactCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	
	@GetMapping(value="/")
	public String start() {
		return "index";
	}
	
	@GetMapping(value="listPage.do")
	public String listPage(Model model) {
			
		selectListCommand.execute(model);
		return "contact/list";
	}
	
	@GetMapping(value="insertPage.do")
	public String insertPage() {		
		return "contact/insert";
	}
	
	@PostMapping(value="insert.do")
	public String insert(Contact contact, Model model) {
		model.addAttribute("contact", contact);
		insertContactCommand.execute(model);
		return listPage(model);
	}
	
	@GetMapping(value="viewContact.do")
	public String view(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		viewContactCommand.execute(model);
		return "contact/view";
	}
	
	@PostMapping(value="update.do")
	public String view(Contact contact, Model model) {
		model.addAttribute("contact",contact);
		updateContactCommand.execute(model);
		return listPage(model);
	}
	
	@GetMapping(value="delete.do")
	public String delete(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		deleteContactCommand.execute(model);
		return listPage(model);
	}
	
}
