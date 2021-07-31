package com.mp4.member.service;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mp4.member.vo.LoginVO;
import com.mp4.member.vo.MemberVO;
import com.webjjang.util.PageObject;

public interface MemberService {

	public LoginVO login(LoginVO vo, HttpServletResponse response) throws Exception;

	public int join(MemberVO vo, HttpServletResponse response)throws Exception;
	
	//아이디 중복체크(int)
	public void check_id(String id,HttpServletResponse response)throws Exception;
	
	//이메일 중복체크(int)
	public void check_email(String id,HttpServletResponse response)throws Exception;
	
	//연락처 중복체크(int)
	public void check_tel(String id,HttpServletResponse response)throws Exception;

	//ID 찾기
	public MemberVO findId(HttpServletResponse response,MemberVO vo)throws Exception;
	
	//이메일 발송
	public void sendEmail(MemberVO vo, String div)throws Exception;
	
	//비번 찾기
	public MemberVO findPw(HttpServletResponse response,MemberVO vo)throws Exception;
	

	//내 정보 보기
	public MemberVO myPage(LoginVO vo)throws Exception;
	
	//회원 정보 수정
	public int memberUpdate(MemberVO vo)throws Exception;
	
	//회원탈퇴
	public int memberWithdraw(MemberVO vo,HttpServletResponse response,HttpSession session)throws Exception;

	
	/*관리자*/
	//회원 목록
	public List<MemberVO>memberList(PageObject pageObject)throws Exception;
	
	//회원 등급 수정
	public int gradeModify(MemberVO vo) throws Exception;
	
	//회원 정보 보기
	public MemberVO view(String id) throws Exception;
	
	
	
}
