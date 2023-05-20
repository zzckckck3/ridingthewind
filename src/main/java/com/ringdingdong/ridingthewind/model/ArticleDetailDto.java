package com.ringdingdong.ridingthewind.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ArticleDetailDto {

	private int articleNo;
	private String memberId;
	private String subject;
	private String content;
	private int hit;
	private int like;
	private String registerTime;
	private String modifyTime;
	private String nickname;
	private List<CommentDto> commentList;

}
