package com.mp4.recommend.mapper;

import java.util.List;

import com.mp4.recommend.vo.RecommendVO;

public interface RecommendMapper {
	
	//1-1. 추천 리스트
	public List<RecommendVO> list(Long no) throws Exception;
	
	//1-2. 전체 데이터 가지고 오기
	public Long getTotalRow(Long no) throws Exception;
	
	//2. 보기는 리스트에 포함
	
	//3. 리스트에 노래 추가
	public int add(RecommendVO vo) throws Exception;
	
	//4. 정보 수정
	public int update(RecommendVO vo) throws Exception;
	
	//5. 삭제
	public int delete(Long no) throws Exception;

}
