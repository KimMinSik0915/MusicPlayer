package com.mp4.like.service;

import java.util.List;

import com.mp4.like.vo.LikeVO;
//import com.webjjang.util.PageObject;
import com.webjjang.util.PageObject;

public interface LikeService {

	//좋아요 리스트
	public List<LikeVO> list(PageObject pageObject) throws Exception;
	
	//좋아요 버튼
	public int like(LikeVO vo) throws Exception;
	
	//좋아요 취소
	public int unLike(LikeVO vo) throws Exception;
	
	public LikeVO bookmark(LikeVO vo) throws Exception;
	
}
