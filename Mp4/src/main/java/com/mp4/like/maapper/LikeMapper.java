package com.mp4.like.maapper;

import java.util.List;

import com.mp4.like.vo.LikeVO;
import com.webjjang.util.PageObject;

public interface LikeMapper {
	
//	public LikeVO bookmark(LikeVO vo) throws Exception;
	
	//좋아요 눌렀을 때
	public int like(LikeVO vo) throws Exception;
	
	//좋아요 다시 눌렀을 때(좋아요 취소)
	public int unlike(LikeVO vo) throws Exception;

	//좋아요 보관함 - PageObject 추가 해야함
	public List<LikeVO> list(PageObject pageObject) throws Exception;
	
	//좋아요 보관함 전체데이터 가지고 오기
	public Long getTotalRow(PageObject pageObject) throws Exception;
	
	public LikeVO bookmark(LikeVO vo) throws Exception;
}
