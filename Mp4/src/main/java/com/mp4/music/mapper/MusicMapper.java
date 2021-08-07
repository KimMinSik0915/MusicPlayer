package com.mp4.music.mapper;

import java.util.List;

import com.mp4.music.vo.MusicVO;

public interface MusicMapper {
	
	// 1. 리스트
	public List<MusicVO> list() throws Exception;

	// 2. 보기
	public MusicVO view(String title);
	
	// 3. 조회수 증가
	public long increase(String title);
	
	// 4. 노래 등록
	public int register(MusicVO vo);
	
	// 5. 노래 수정
	public int update(MusicVO vo);
	
	// 6. 노래 삭제
	public int delte(MusicVO vo);

	
}
