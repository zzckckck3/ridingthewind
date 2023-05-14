package com.ringdingdong.ridingthewind.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Article {

	private int articleNo;
	private String memberId;
	private String memberName;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;

}
