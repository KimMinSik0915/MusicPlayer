package com.mp4.recommend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mp4.recommend.mapper.RecommendMapper;
import com.mp4.recommend.vo.RecommendVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Qualifier("rsi")
@AllArgsConstructor
@Log4j
public class RecommendServiceImpl implements RecommendService {

	private RecommendMapper mapper;
	
	@Override
	public List<RecommendVO> list(Long no) throws Exception {
		// TODO Auto-generated method stub
		
		return mapper.list(no);
	}

	@Override
	public int add(RecommendVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("add().vo" + vo);
		
		return mapper.add(vo);
	}

	@Override
	public int update(RecommendVO vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(Long no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.delete(no);
	}

}
