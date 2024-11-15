package com.ringdingdong.ridingthewind.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ArticleParameterDto : 게시판 파라미터 정보", description = "게시판의 글을 얻기위한 부가적인 파라미터정보.")
public class ArticleParameterDto {

	@ApiModelProperty(value = "현재 페이지 번호")
	private int curPage;
	@ApiModelProperty(value = "페이지당 글갯수")
	private int spp;
	@ApiModelProperty(value = "페이지의 시작 글번호")
	private int start;
	@ApiModelProperty(value = "검색 조건")
	private String key;
	@ApiModelProperty(value = "검색어")
	private String word;

}

