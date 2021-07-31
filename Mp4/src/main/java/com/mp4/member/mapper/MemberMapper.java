package com.mp4.member.mapper;

import java.util.List;

import com.mp4.member.vo.LoginVO;
import com.mp4.member.vo.MemberVO;
import com.webjjang.util.PageObject;

public interface MemberMapper {

	//로그인
	public LoginVO login(LoginVO vo);
	
	//회원가입
	public int join(MemberVO vo);
	
	//ajax check
	//아이디 중복검사(int)
	public int check_id(String id);
	//이메일 중복검사(int)
	public int check_email(String email);
	//연락처 중복검사(int)
	public int check_tel(String tel);
	
	//DB check
	//아이디 중복검사 
	public MemberVO checkId(String Id);
	
	//이메일 중복검사
	public MemberVO checkEmail(MemberVO vo);
	
	//비밀번호 중복검사
	public MemberVO findId(MemberVO vo);
	
	//아이디 찾기
	public MemberVO checkPw(MemberVO vo);
	
	//비밀번호 변경
	public int updatePw(MemberVO vo);
	
	//내 정보보기
	public MemberVO mypage(LoginVO vo);
	
	//회원 정보 수정
	public int memberUpdate(MemberVO vo);
	
	//회원 탈퇴
	public int memberWithdraw(MemberVO vo);
	
	/* 관리자 */
	public List<MemberVO> memberList(PageObject pageObject);
	
	public Long getTotalRow(PageObject pageObject);
	
	public int gradeModify(MemberVO vo);
	
	public MemberVO view(String id);
	
	
	
	
}
