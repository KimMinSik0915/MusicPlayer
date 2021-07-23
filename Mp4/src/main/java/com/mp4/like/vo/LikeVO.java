package com.mp4.like.vo;

import lombok.Data;

@Data
public class LikeVO {

	private Long likeNo;
	private String id;
	private String nickName;
	private String status;
	private String title, singer, album, issueDate, image, url;
	
}
