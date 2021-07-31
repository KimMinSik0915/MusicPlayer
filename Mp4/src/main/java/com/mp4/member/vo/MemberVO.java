package com.mp4.member.vo;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private String email;
	private String tel;
	private String nickName;
	private String regDate;
	private String conDate;
	private String status;
	private int gradeNo;
	private String gradeName;
	
}
