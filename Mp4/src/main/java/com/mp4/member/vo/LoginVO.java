package com.mp4.member.vo;

import lombok.Data;

@Data
public class LoginVO {

	// 로그인에 필요한 정보 - 화면에서 보여주기 + 처리
	private String id;
	private String pw;
	private String name;
	private int gradeNo;
}
