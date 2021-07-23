package com.mp4.music.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MusicVO {

	private String title, singer, album, image, url;
	
	private Date isuueDate;
	
	private long hti;
	 
}
