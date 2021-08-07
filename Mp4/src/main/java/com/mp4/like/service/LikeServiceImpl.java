package com.mp4.like.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mp4.like.mapper.LikeMapper;
import com.mp4.like.vo.LikeVO;
<<<<<<< HEAD
//import com.webjjang.util.PageObject;
=======
>>>>>>> branch 'developer' of https://github.com/KimMinSik0915/MusicPlayer.git
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
@Qualifier("lsi")
public class LikeServiceImpl implements LikeService {
	
	@Inject
	private LikeMapper mapper;

<<<<<<< HEAD
	// 좋아요 리스트 ->  후에 PageObject 추가하기
//	@Override
//	public List<LikeVO> list(PageObject pageObject) throws Exception {
////		 TODO Auto-generated method stub
//		
//		log.info("Like list pageObject : " + pageObject);
//		
//		return mapper.list(pageObject);
//	}
=======
//	 좋아요 리스트 ->  후에 PageObject 추가하기
	@Override
	public List<LikeVO> list(PageObject pageObject) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("Like list pageObject : " + pageObject);

		return mapper.list(pageObject);
	}
>>>>>>> branch 'developer' of https://github.com/KimMinSik0915/MusicPlayer.git

	//좋아요 버튼 -- insert(write)
	@Override
	public int like(LikeVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("Like vo : " + vo);
		
		return mapper.like(vo);
	}

	//좋아요 취소 -- delete
	@Override
	public int unLike(LikeVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("Unkike vo : " + vo);
		
		return mapper.unlike(vo);
	}

//	@Override
//	public LikeVO bookmark(LikeVO vo) throws Exception {
//		// TODO Auto-generated method stub
//		log.info(vo);
//		
//		return mapper.bookmark(vo);
//	}

}
