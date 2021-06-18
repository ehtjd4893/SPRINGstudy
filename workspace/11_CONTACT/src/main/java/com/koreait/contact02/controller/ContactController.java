package com.koreait.contact02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact02.command.DeleteContactCommand;
import com.koreait.contact02.command.InsertContactCommand;
import com.koreait.contact02.command.SelectListCommand;
import com.koreait.contact02.command.UpdateContactCommand;
import com.koreait.contact02.command.ViewContactCommand;
import com.koreait.contact02.config.BeanConfiguration;
import com.koreait.contact02.dto.Contact;



@Controller
public class ContactController {
	
	
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);

	
	@GetMapping(value="/")
	public String start() {
		return "index";
	}
	
	@GetMapping(value="listPage.do")
	public String listPage(Model model) {
		SelectListCommand selectListCommand = (SelectListCommand)ctx.getBean("listCmd");
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
		InsertContactCommand insertContactCommand = (InsertContactCommand) ctx.getBean("insertCmd");
		insertContactCommand.execute(model);
		return listPage(model);
	}
	
	@GetMapping(value="viewContact.do")
	public String view(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		ViewContactCommand viewContactCommand = (ViewContactCommand)ctx.getBean("viewCmd");
		viewContactCommand.execute(model);
		return "contact/view";
	}
	
	@PostMapping(value="update.do")
	public String view(Contact contact, Model model) {
		model.addAttribute("contact",contact);
		UpdateContactCommand updateContactCommand = (UpdateContactCommand) ctx.getBean("updateCmd");
		updateContactCommand.execute(model);
		return listPage(model);
	}
	
	@GetMapping(value="delete.do")
	public String delete(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		DeleteContactCommand deleteContactCommand = (DeleteContactCommand) ctx.getBean("deleteCmd");
		deleteContactCommand.execute(model);
		return listPage(model);
	}
	
}
