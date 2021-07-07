package com.dosung.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dosung.home.commandB.BoardListCommand;
import com.dosung.home.commandB.WriteCommand;
import com.dosung.home.commandM.FindIdCommand;
import com.dosung.home.commandM.FindPwCommand;
import com.dosung.home.commandM.GetKeyCommand;
import com.dosung.home.commandM.IdCheckCommand;
import com.dosung.home.commandM.LoginCommand;
import com.dosung.home.commandM.MyPhoneCheckCommand;
import com.dosung.home.commandM.PhoneCheckCommand;
import com.dosung.home.commandM.ResignupCommand;
import com.dosung.home.commandM.SignoutCommand;
import com.dosung.home.commandM.SignupCommand;
import com.dosung.home.commandM.UpdateCommand;
import com.dosung.home.commandM.UpdatePwCommand;



@Controller
public class MemberController {
	
	private SqlSession sqlSession;
	private LoginCommand loginCommand;
	private GetKeyCommand getKeyCommand;
	private SignupCommand signupCommand;
	private IdCheckCommand idCheckCommand;
	private PhoneCheckCommand phoneCheckCommand;
	private SignoutCommand signoutCommand;
	private MyPhoneCheckCommand myPhoneCheckCommand;
	private ResignupCommand resignupCommand;
	private UpdatePwCommand updatePwCommand;
	private UpdateCommand updateCommand;
	private FindIdCommand findIdCommand;
	private FindPwCommand findPwCommand;
	@Autowired
	public MemberController(SqlSession sqlSession,
	 				  LoginCommand loginCommand,
	 				  GetKeyCommand getKeyCommand,
	 				  SignupCommand signupCommand,
	 				  IdCheckCommand idCheckCommand,
	 				  PhoneCheckCommand phoneCheckCommand,
	 				  SignoutCommand signoutCommand,
	 				  MyPhoneCheckCommand myPhoneCheckCommand,
	 				  ResignupCommand resignupCommand,
	 				  UpdatePwCommand updatePwCommand,
	 				  UpdateCommand updateCommand,
	 				  FindIdCommand findIdCommand,
	 				  FindPwCommand findPwCommand) {
		this.sqlSession = sqlSession;
		this.loginCommand = loginCommand;
		this.getKeyCommand = getKeyCommand;
		this.signupCommand = signupCommand;
		this.idCheckCommand = idCheckCommand;
		this.phoneCheckCommand = phoneCheckCommand;
		this.signoutCommand = signoutCommand;
		this.myPhoneCheckCommand = myPhoneCheckCommand;	
		this.resignupCommand = resignupCommand;
		this.updatePwCommand = updatePwCommand;
		this.updateCommand = updateCommand;
		this.findIdCommand = findIdCommand;
		this.findPwCommand = findPwCommand;
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
		model.addAttribute("page", "1");
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
	@GetMapping(value="logout.do")
	public String logout(HttpServletRequest request, Model model) {
		request.getSession().invalidate();
		model.addAttribute("page", "1");
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
	public String signout(@RequestParam long no, Model model) {
		model.addAttribute("no", no);
		return signoutCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="myPhoneCheck.do", 
			    produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> myPhoneCheck(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		return myPhoneCheckCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="resignup.do")
	public String resignup(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return resignupCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="updatepwPage.do")
	public String updatePwPage() {
		return "member/updatePw";
	}
	
	@PostMapping(value="updatePw.do")
	public String updatePw(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);	
		return updatePwCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="update.do")
	public String update(HttpServletRequest request, 
						 HttpServletResponse response,
						 Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		return updateCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="findIdPage.do")
	public String findIdPage() {
		return "member/findId";
	}
	
	@GetMapping(value="findId.do",
		        produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> findId(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		return findIdCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="findPwPage.do")
	public String findPwPage() {
		return "member/findPw";
	}
	@GetMapping(value="findPw.do",
	        produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> findPw(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		return findPwCommand.execute(sqlSession, model);
	}
	
	

}
