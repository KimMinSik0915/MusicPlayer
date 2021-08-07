package com.mp4.music.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mp4.music.vo.MusicVO;

@Service
public interface MusicService {

	public List<MusicVO> list() throws Exception;

	public MusicVO view(String title) throws Exception;
	
}
