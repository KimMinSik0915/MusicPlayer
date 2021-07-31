package com.mp4.member.service;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mp4.member.mapper.MemberMapper;
import com.mp4.member.vo.LoginVO;
import com.mp4.member.vo.MemberVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

//자동 생성
@Service
@Log4j
@Qualifier("msi")
public class MemberServiceImpl implements MemberService {

	//자동 DI - Mapper
	@Inject
	private MemberMapper mapper;
	
	@Override
	public LoginVO login(LoginVO vo, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//화면에 출력하기 위한 선언 (out.println)
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		LoginVO data = mapper.login(vo);
		
		log.info("login().vo:" + vo);
		log.info("login().data:" + data);
		
		if(data == null) {
			out.println("<script>");
			out.println("alert('가입된 정보가 없습니다.');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
		}else {
			out.println("<script>");
			out.println("location.href='/list/map.do'");
			out.println("</script>");
			out.close();
		}
		return mapper.login(vo) ;
	}

	@Override
	public int join(MemberVO vo, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if(mapper.check_id(vo.getId()) == 1) {
			out.println("<script>");
			out.println("alert('사용할 수 없는 아이디 입니다.');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
			return 0;
		}else {
			mapper.join(vo);
			return 1;
		}
	}

	@Override
	public void check_id(String id, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(mapper.check_id(id));
		out.close();
		
	}
	
	@Override
	public void check_email(String email, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(mapper.check_email(email));
		out.close();
		
	}
	
	@Override
	public void check_tel(String tel, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(mapper.check_tel(tel));
		out.close();
		
	}
	
	@Override
	public MemberVO findId(HttpServletResponse response,MemberVO vo) throws Exception {
		// TODO Auto-generated method stub

		//화면에 출력하기 위한 선언 (out.println)
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberVO id = mapper.findId(vo);
		
		if(id == null) {
			out.println("<script>");
			out.println("alert('가입된 아이디가 없습니다.');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
			return null;
		}else {
			return id;
		}
	}

	@Override
	public void sendEmail(MemberVO vo, String div) throws Exception {
		// TODO Auto-generated method stub
		//Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "stmp.gmail.com";
		String hostSMTPid = "mp4Master@gmail.com";
		String hostSMTPpw = "mp41234";
		
		//보내는 사람 Email,제목,내용
		String fromEmail = "mp4Master@gmail.com";
		String fromName = "mp4Master";
		String subject = "";
		String msg = "";
		
		if(div.equals("findPw")) {
			subject = "mp4 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg +="<p>임시 비밀번호 : ";
			msg += vo.getPw() + "</p><div>";
		}
		
		//받는 사람 Email 주소
		String mail = vo.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);
			
			email.setAuthentication(hostSMTPid, hostSMTPpw);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("메일 발송 실패 : " + e);
		}
	}

	@Override
	public MemberVO findPw(HttpServletResponse response,MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		log.info("비밀번호 변경 serviceImpl :" + vo);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//아이디가 없는 경우 
		if(mapper.checkId(vo.getId()) == null) {
			out.println("<script>");
			out.println("alert('가입된 아이디가 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			log.info("비밀번호 변경 ServiceImpl getid : " + vo.getId());

			return null;
		}
		else if(mapper.checkEmail(vo) == null) {
			log.info("비밀번호 변경 serviceImpl vo :" + vo);
			log.info("비밀번호 변경 serviceImpl getEmail :" + vo.getEmail());
			out.println("<script>");
			out.println("alert('등록되지 않은 이메일 입니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		}else {
			//임시 비밀번호 생성
			String pw = "";
			for(int i = 0; i < 12; i++) {
				pw += (char)((Math.random() * 26) + 97);
			}
			vo.setPw(pw);
			//비밀번호 변경
			mapper.updatePw(vo);
			//비밀번호 변경 이메일 전송
			sendEmail(vo, "findPw");
			log.info("비밀번호 변경 serviceImpl vo :" + vo);
			
			return vo;
		}
	}

	@Override
	public MemberVO myPage(LoginVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.mypage(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.memberUpdate(vo);
	}

	@Override
	public int memberWithdraw(MemberVO vo, HttpServletResponse response, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberVO data = mapper.checkPw(vo);
		
		log.info("memberWithdraw().vo : " + vo);
		log.info("memberWithdraw().data : " + data);
		
		if(data == null) {
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("history.go(-1)");
			out.println("</script>");
			out.close();
		}else {
			//탈퇴하면 로그아웃
			session.removeAttribute("login");
		}
		
		return mapper.memberWithdraw(vo);
	}

	
	/* 관리자 */
	
	@Override
	public List<MemberVO> memberList(PageObject pageObject) throws Exception {
		// TODO Auto-generated method stub
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("pageObject : " + pageObject);
		
		return mapper.memberList(pageObject);
	}

	@Override
	public int gradeModify(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.gradeModify(vo);
	}

	@Override
	public MemberVO view(String id) throws Exception {
		// TODO Auto-generated method stub
		return mapper.view(id);
	}

}
