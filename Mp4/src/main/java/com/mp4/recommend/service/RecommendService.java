package com.mp4.recommend.service;

import java.util.List;

import com.mp4.recommend.vo.RecommendVO;

public interface RecommendService {
	
	//1-1. 추천 리스트
	public List<RecommendVO> list(Long no) throws Exception;
	
	//2. 보기는 리스트에 포함
	
	//3. 노래 추가
	public int add(RecommendVO vo) throws Exception;
	
	//4. 수정
	public int update(RecommendVO vo) throws Exception;
	
	//5. 삭제
	public int delete(Long no) throws Exception;
}
