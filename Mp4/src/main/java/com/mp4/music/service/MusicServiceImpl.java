package com.mp4.music.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mp4.music.mapper.MusicMapper;
import com.mp4.music.vo.MusicVO;



@Service
@Qualifier("msi")
public class MusicServiceImpl implements MusicService {

	@Inject
	private MusicMapper mapper;
	
	@Override
	public List<MusicVO> list() throws Exception{
		// TODO Auto-generated method stub

		return mapper.list();
		
	}
	
	@Override
	public MusicVO view(String title) thrwos Exception {
	
		log.info(title);
		
		mapper.increase(title);		// hit 증가를 위한 mapper 호출
		
		return mapper.view(title);
		
	}

}
