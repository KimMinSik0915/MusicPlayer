package com.mp4.like.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mp4.like.service.LikeService;
import com.mp4.like.vo.LikeVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/like")
@Log4j
public class LikeController {
	
	private final String MODULE = "like";
	
	//자동 DI
	@Autowired
	@Qualifier("lsi")
	private LikeService service;
	
	//1. 리스트
	@GetMapping("/list.do")
	public String list(Model model, PageObject pageObject, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		log.info("list().pageObject : " + pageObject);
		
		model.addAttribute("list", service.list(pageObject));
		
		return MODULE + "/list";
	}
	
	//2-1. 좋아요
	@PostMapping(value = {"/like.do"}, 
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
			produces = {"application/text; charset=utf-8"})
	public ResponseEntity<String> like(@RequestBody LikeVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		log.info("like().vo : " + vo);
		int result = service.like(vo);
		log.info(result);
		
		return new ResponseEntity<String>
		("즐겨찾기가 완료되었습니다.", HttpStatus.OK);
	}
	
	//2-2. 좋아요 표시
	@GetMapping(value = "/view.do", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<LikeVO> view(LikeVO vo) throws Exception{
		log.info("기본 좋아요 표시.");
		
		return new ResponseEntity<LikeVO>(service.bookmark(vo), HttpStatus.OK);
	}
	
	//3. 좋아요 취소
	@PostMapping(value = {"/unlike.do"}, 
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
			produces = {"application/text; charset=utf-8"})
	public ResponseEntity<String> unlike(@RequestBody LikeVO vo,  HttpSession session, RedirectAttributes rttr) throws Exception {
		
		log.info("unlike().vo : " + vo);
//		LoginVO loginVO = (LoginVO) session.getAttribute("login");
		
		//id에 로그인한 아이디 담기
//		String id = loginVO.getId();
//		vo.setId(id);
		
//		log.info(id);
		
		int result = service.unLike(vo);
		String msg = "즐겨찾기가 리스트에서 삭제되었습니다.";
		HttpStatus status = HttpStatus.OK;
		log.info(result);
		
		if(result == 0) {
			msg = "즐겨찾기 삭제에 실패했습니다.";
		}
		log.info("unlike().msg: " + msg);
		return new ResponseEntity<String>(msg, status);
	}
	
}
