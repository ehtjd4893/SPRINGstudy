package com.dosung.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dosung.home.command.GetKeyCommand;
import com.dosung.home.command.IdCheckCommand;
import com.dosung.home.command.LoginCommand;
import com.dosung.home.command.PhoneCheckCommand;
import com.dosung.home.command.SignupCommand;
import com.dosung.home.dto.MemberDTO;
import com.dosung.home.utils.Utils;

import net.nurigo.java_sdk.Coolsms;

@org.springframework.stereotype.Controller
public class Controller {
	
	private SqlSession sqlSession;
	private LoginCommand loginCommand;
	private GetKeyCommand getKeyCommand;
	private SignupCommand signupCommand;
	private IdCheckCommand idCheckCommand;
	private PhoneCheckCommand phoneCheckCommand;
	@Autowired
	public Controller(SqlSession sqlSession,
	 				  LoginCommand loginCommand,
	 				  GetKeyCommand getKeyCommand,
	 				  SignupCommand signupCommand,
	 				  IdCheckCommand idCheckCommand,
	 				  PhoneCheckCommand phoneCheckCommand) {
		this.sqlSession = sqlSession;
		this.loginCommand = loginCommand;
		this.getKeyCommand = getKeyCommand;
		this.signupCommand = signupCommand;
		this.idCheckCommand = idCheckCommand;
		this.phoneCheckCommand = phoneCheckCommand;
	}
	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="loginPage.do")
	public String loginPage() {
		return "member/login";
	}
	
	// ID, PW를 request에 담아온다.
	@PostMapping(value="login.do")
	public String login(HttpServletRequest request,Model model) {
		model.addAttribute("request", request);	// model을 통해 request 전달( to command )
		loginCommand.execute(sqlSession, model);
		return loginCommand.execute(sqlSession, model);
	}
	
	// 인증키 발급해주는 매핑(ajax), json형태로 반환
    @GetMapping(value = "sendSms.do", 
    		    produces="application/json; charset=utf-8")
    @ResponseBody
    public Map<String, Object> sendSms(HttpServletRequest request, Model model) throws Exception {	
    	model.addAttribute("request", request); 
		return getKeyCommand.execute(model); 	
    }
    
    // 회원가입 페이지로 이동
	@GetMapping(value="signupPage.do")
	public String signupPage() {
		return "member/signup";
	}
	
	// 실제 DB에 멤버를 추가함.
	@PostMapping(value="signup.do")
	public String signup(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return signupCommand.execute(sqlSession, model);
		
	}
	
	// 세션 비우고 mainList화면으로 이동
	@GetMapping(value= {"logout.do", "mainList.do"})
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "board/mainList";
	}
	
	// id 중복여부 체크하여 json형태로 반환, ajax매핑
	@GetMapping(value="idCheck.do", 
			    produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> idCheck(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		return idCheckCommand.execute(sqlSession, model);
	}
	

	// phone 중복여부 체크하여 json형태로 반환, ajax매핑
	@GetMapping(value="phoneCheck.do", 
			    produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> phoneCheck(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		return phoneCheckCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="myPage.do")
	public String myPage() {
		return "member/myPage";
	}
	@GetMapping(value="signout.do")
	public String signout() {
		
		return "board/mainList";
	}
}
