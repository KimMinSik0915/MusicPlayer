package com.mp4.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mp4.member.service.MemberService;
import com.mp4.member.vo.LoginVO;
import com.mp4.member.vo.MemberVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j

public class MemberController {

	private final String MODULE = "member";
	
	//자동DI
	@Autowired
	@Qualifier("msi")
	private MemberService service;
	

	//session 이용한 로그인
	//로그인폼
	@GetMapping("/loginForm.do")
	public String loginForm() {
		return MODULE + "/login";
	}
	
	//로그인 처리
	//아이디 넘기고 비밀번호를 session에서 받아야함
	@PostMapping("/login.do")
	public String login(LoginVO vo, HttpSession session,HttpServletResponse response)throws Exception{
		
		//넘어온 데이터 확인
		log.info("login().vo -" + vo);
		
		//아이디와 비밀번호가 DB 정보와 맞으면 로그인 처리 (session에 loginVO 객체 넣어줌)
		session.setAttribute("login", service.login(vo, response));
		
		return "redirect:" + session.getAttribute("url");
	}
	
	//로그아웃 처리 메소드 - session 그대로 두고 session 안의 login 정보 지움 
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		
		log.info("logout() - 로그아웃 처리" );
		
		//로그아웃 처리
		//1.session 완전히 삭제 
		//session.invalidate();
		//2.session 그대로 두고 session 안의 login 정보 삭제 
		session.removeAttribute("login");
		
		return "redirect:" + session.getAttribute("url");
	}
	
	//회원가입폼
	@GetMapping("/joinForm.do")
	public String joinForm() {
		return MODULE + "/join";
	}
	
	//회원가입 처리
	@PostMapping("/join.do")
	public String join(MemberVO vo,HttpServletResponse response,RedirectAttributes rttr) throws Exception {
		
		log.info("join() :" + vo);
		
		//메세지 띄우기
		rttr.addFlashAttribute("result", service.join(vo, response));
		return "redirect:/member/loginForm.do";
	}
	
	// 아이디 중복 검사 (AJAX)
	@PostMapping("/check_id.do")
	public void check_id(@RequestParam("id")String id,HttpServletResponse response)throws Exception{
		service.check_id(id, response);
	}
	
	// 이메일 중복 검사 (AJAX)
	@PostMapping("/check_email.do")
	public void check_email(@RequestParam("email")String email,HttpServletResponse response)throws Exception{
		service.check_email(email, response);
	}
	
	// 연락처 중복 검사 (AJAX)
	@PostMapping("/check_tel.do")
	public void check_tel(@RequestParam("tel")String tel,HttpServletResponse response)throws Exception{
		service.check_tel(tel, response);
	}
	
	//이용약관
	@GetMapping("/readme.do")
	public String readme() throws Exception{
		
		return MODULE + "/readme";
	}
	
	//아이디 찾기 폼
	@GetMapping("/findIdForm.do")
	public String findIdForm()throws Exception{
		
		return MODULE + "/findIdForm";
	}
	
	//아이디 찾기 처리
	@PostMapping("/findId.do")
	public String findId(HttpServletResponse response,Model model,MemberVO vo)throws Exception{
		
		model.addAttribute("vo",service.findId(response, vo));
		
		return MODULE + "/findId";
	}
	
	//비밀번호 찾기 폼
	@GetMapping("/findPwForm.do")
	public String findPwForm()throws Exception{
		
		return MODULE + "/findPwForm";
	}
	
	//비밀번호 찾기 처리
	@PostMapping("/findPw.do")
	public String findPw(HttpServletResponse response,Model model,MemberVO vo)throws Exception{
		
		model.addAttribute("vo",service.findPw(response, vo));
		log.info("비밀번호 변경 controller : " + vo);
		
		return MODULE + "/findPw";
	}
	
	//내 정보 보기
	@GetMapping("/myPage.do")
	public String myPage(HttpServletRequest request,Model model)throws Exception {
		
		HttpSession session = request.getSession();
		log.info("mypage로그인 : " + session.getAttribute("login"));
		LoginVO id = (LoginVO) session.getAttribute("login");
		
		if(id == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MemberVO vo = service.myPage(id);
		log.info("myPage.MemberVO :" + vo);
		
		model.addAttribute("vo",vo);
		
		return MODULE + "/myPage";
	}
	
	//내 정보 수정
	@GetMapping("/memberUpdateForm.do")
	public String memberUpdateForm(HttpServletRequest request,Model model)throws Exception {
			
		HttpSession session = request.getSession();
		LoginVO id = (LoginVO) session.getAttribute("login");
		
		if(id == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MemberVO vo = service.myPage(id);
		log.info("myPage.MemberVO :" + vo);
		
		model.addAttribute("vo",vo);
		
		return MODULE + "/memberUpdate";
	}
	
	@PostMapping("/memberUpdate.do")
	public String memberUpdate(MemberVO vo,Model model)throws Exception {
		
		log.info("회원정보수정 :" + vo);
		model.addAttribute("vo", service.memberUpdate(vo));
		
		return "redirect:/member/myPage.do";
	}
	
	
	//회원탈퇴
	@GetMapping("/memberWithdrawForm.do")
	public String memberWithdrawForm(HttpServletRequest request,Model model)throws Exception {
			
		HttpSession session = request.getSession();
		LoginVO id = (LoginVO) session.getAttribute("login");
		
		if(id == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MemberVO vo = service.myPage(id);
		log.info("myPage.MemberVO :" + vo);
		
		model.addAttribute("vo",vo);
		
		return MODULE + "/memberWithdrawForm";
	}
	
	
	@PostMapping("/memberWithdraw.do")
	public String memberWithdraw(MemberVO vo, Model model, HttpServletResponse response, HttpSession session)throws Exception {
		
		log.info("memberWithdrawController : " + vo);
		model.addAttribute("vo", service.memberWithdraw(vo, response, session));
		
		return MODULE + "/memberWithdraw";
	}
	
	/* 관리자 */
	
	@GetMapping("/memberList.do")
	public String memberList(Model model, @ModelAttribute PageObject pageObject) throws Exception {
		
		log.info("list().pageObject : " + pageObject + " ..........");
		model.addAttribute("list", service.memberList(pageObject));
		
		return MODULE + "/memberList";
	}
	
	@PostMapping("/gradeModify.do")
	public String gradeModify(MemberVO vo, Model model) throws Exception {
		model.addAttribute("vo", service.gradeModify(vo));
		return "redirect:/member/memberList.do";
		
	}
	
	@GetMapping("/view.do")
	public String view(Model model, String id, @ModelAttribute PageObject pageObject ) throws Exception {
		
		model.addAttribute("vo", service.view(id));
		
		return MODULE + "/view";
	}
}
