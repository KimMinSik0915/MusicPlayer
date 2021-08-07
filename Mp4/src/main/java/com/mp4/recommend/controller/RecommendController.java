package com.mp4.recommend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp4.recommend.service.RecommendService;
import com.mp4.recommend.vo.RecommendVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/recommend")
@Log4j
public class RecommendController {
	
	//DI
	@Autowired
	@Qualifier("rsi")
	private RecommendService service;
	
	//1. list
	@GetMapping(value = "/list.do", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<RecommendVO>> list(Long no) throws Exception{
		
		log.info("list().no : " + no);
		
		return new ResponseEntity<>(service.list(no), HttpStatus.OK);
	}
	
	//2. add
	@PostMapping(value = "/write.do", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = {"application/text; charset=utf-8" })
	public ResponseEntity<String> add(@RequestBody RecommendVO vo, HttpSession session) throws Exception{
		
		//no, nickName 담기
		long no = vo.getRNo();
		String nickName = vo.getNickName();
		
//		LoginVO loginVO = (LoginVO) session.getAttribute("login");
		
		//id에 로그인한 아이디를 담아라
//		String id = loginVO.getId();
		
//		//현재 로그인된 아이디, 별명 확인
//		log.info("id@@@@@ : " + id);
//		log.info("nickName@@@@@ : " + nickName);
		
		vo.setRNo(no);
//		vo.setId(id);
		vo.setNickName(nickName);
		vo.setInfo(vo.getInfo());
		vo.setTitle(vo.getTitle());
		
		return new ResponseEntity<>("리스트에 추가되었습니다.", HttpStatus.OK);
	}
	
	//3. Info Update
	@PatchMapping(value = "/update.do", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {"application/text; charset=utf-8"})
	public ResponseEntity<String> update(@RequestBody RecommendVO vo) throws Exception{
		
		log.info("update().vo : " + vo);
		int result = service.update(vo);
		
		// 전달되는 데이터의 선언
		String msg = "추천 리스트정보 수정이 성공적으로 되었습니다.";
		HttpStatus status = HttpStatus.OK;
		
		if(result == 0) {
			msg = "추천 리스트정보 변경 실패.";
			status = HttpStatus.NOT_MODIFIED;
		}
		
		log.info("update().msg : " + msg);
		
		return new ResponseEntity<>(msg, status);
	}
	
	//4. delete
	@DeleteMapping(value = "/delete.do", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {"application/text; charset=utf-8"})
	public ResponseEntity<String> delete(@RequestBody Long no) throws Exception{
		
		log.info("delete().no : " + no);
		int result = service.delete(no);
		
		// 전달되는 데이터의 선언
		String msg = "추천 리스트 삭제가 성공적으로 되었습니다.";
		HttpStatus status = HttpStatus.OK;
		
		if(result == 0) {
			msg = "추천 리스트 삭제 실패 - 정보를 확인해 주세요.";
			status = HttpStatus.NOT_MODIFIED;
		}
		
		log.info("update().msg : " + msg);
		
		return new ResponseEntity<>(msg, status);
	}

}
